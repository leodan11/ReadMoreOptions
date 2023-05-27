package com.leodan.readmoreoption.base

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RestrictTo
import androidx.annotation.StringRes
import com.leodan.readmoreoption.R
import com.leodan.readmoreoption.ReadMoreOption

@RestrictTo(RestrictTo.Scope.LIBRARY)
abstract class ReadBaseMoreOption(protected open val context: Context,
                                  protected open val textLength: Int = 0,
                                  protected open val textLengthType: Int = 0,
                                  protected open val moreLabel: String? = null,
                                  protected open val lessLabel: String? = null,
                                  protected open val moreLabelColor: Int = 0,
                                  protected open val lessLabelColor: Int = 0,
                                  protected open val labelUnderLine: Boolean = false,
                                  protected open val expandAnimation: Boolean = false) {


    /**
     * Set the text that is displayed in the textview.
     *
     * @param textView The view where the text will be displayed.
     * @param text The text to display.
     */
    fun addReadMoreTo(textView: TextView, @StringRes text: Int){
        addReadMoreTo(textView = textView, text = context.getString(text))
    }

    /**
     * Set the text that is displayed in the textview.
     *
     * @param textView The view where the text will be displayed.
     * @param text The text to display.
     */
    fun addReadMoreTo(textView: TextView, text: CharSequence) {
        if (textLengthType == ReadMoreOption.TYPE_CHARACTER) {
            if (text.length <= textLength) {
                textView.text = text
                return
            }
        } else {
            // If TYPE_LINE
            textView.setLines(textLength)
            textView.text = text
        }
        textView.post(Runnable {
            var textLengthNew = textLength
            if (textLengthType == ReadMoreOption.TYPE_LINE) {
                if (textView.layout.lineCount <= textLength) {
                    textView.text = text
                    return@Runnable
                }
                val lp = textView.layoutParams as ViewGroup.MarginLayoutParams
                val subString = text.toString().substring(
                    textView.layout.getLineStart(0),
                    textView.layout.getLineEnd(textLength - 1)
                )
                textLengthNew = subString.length - (moreLabel!!.length + 4 + lp.rightMargin / 6)
            }
            val spannableStringBuilder = SpannableStringBuilder(text.subSequence(0, textLengthNew))
                .append("…")
                .append(" ")
                .append(moreLabel)
            val ss = SpannableString.valueOf(spannableStringBuilder)
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    addReadLess(textView, text)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = labelUnderLine
                    ds.color = moreLabelColor
                }
            }
            ss.setSpan(
                clickableSpan,
                ss.length - moreLabel!!.length,
                ss.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            if (expandAnimation) {
                val layoutTransition = LayoutTransition()
                layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
                (textView.parent as ViewGroup).layoutTransition = layoutTransition
            }
            textView.text = ss
            textView.movementMethod = LinkMovementMethod.getInstance()
        })
    }

    private fun addReadLess(textView: TextView, text: CharSequence) {
        textView.maxLines = Int.MAX_VALUE
        val spannableStringBuilder = SpannableStringBuilder(text)
            .append(" ")
            .append(lessLabel)
        val ss = SpannableString.valueOf(spannableStringBuilder)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                Handler(Looper.getMainLooper()).post { addReadMoreTo(textView, text) }
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = labelUnderLine
                ds.color = lessLabelColor
            }
        }
        ss.setSpan(
            clickableSpan,
            ss.length - lessLabel!!.length,
            ss.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = ss
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * Creates an [ReadMoreOption] with the arguments supplied to this builder.
     *
     * @property context The parent context
     * @constructor Create empty Builder.
     */
    abstract class Builder<D: ReadBaseMoreOption>(protected val context: Context){

        // Optional
        protected open var textLength = 100
        protected open var textLengthType = ReadMoreOption.TYPE_CHARACTER
        protected open var moreLabel = context.getString(R.string.text_value_read_more)
        protected open var lessLabel = context.getString(R.string.text_value_read_less)
        protected open var moreLabelColor = getColorDefault()
        protected open var lessLabelColor = getColorDefault()
        protected open var labelUnderLine = false
        protected open var expandAnimation = false

        /**
         * Set the maximum size in the textview.
         *
         * @param length The maximum size.
         */
        fun textLength(length: Int) = apply { textLength = length }

        /**
         * Set the type of the size of the textview based on the size of the text. By lines or by number of characters.
         * Use [ReadMoreOption.TYPE_LINE] or [ReadMoreOption.TYPE_CHARACTER].
         *
         * @param type The display type.
         */
        fun textLengthType(type: Int) = apply { textLengthType = type }

        /**
         * Set the label text to action read more.
         *
         * @param more The text to display.
         */
        fun moreLabel(more: String) = apply { moreLabel = more }

        /**
         * Set the label text to action read less.
         *
         * @param less The text to display.
         */
        fun lessLabel(less: String) = apply { lessLabel = less }

        /**
         * Set the label text color to read more action.
         *
         * @param moreColor Label color. Eg: [Color.RED]
         */
        fun moreLabelColor(moreColor: Int) = apply { moreLabelColor = moreColor }

        /**
         * Set the label text color to read less action.
         *
         * @param lessColor Label color. Eg: [Color.BLUE]
         */
        fun lessLabelColor(lessColor: Int) = apply { lessLabelColor = lessColor }

        /**
         * Set the underline to the text.
         *
         * @param ul A [Boolean] value.
         */
        fun labelUnderLine(ul: Boolean) = apply { labelUnderLine = ul }

        /**
         * Set animation to textview.
         *
         * @param anim A [Boolean] value.
         */
        fun expandAnimation(anim: Boolean) = apply { expandAnimation = anim }

        /**
         * Get color default
         *
         * @return int Color Primary default theme
         */
        private fun getColorDefault(): Int{
            val typedValue = TypedValue()
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary, typedValue, true)
            return typedValue.data
        }

        /**
         * Creates an [ReadMoreOption] with the arguments supplied to this builder.
         *
         */
        abstract fun build(): D

    }

}