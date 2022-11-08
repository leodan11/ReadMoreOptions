package com.leodan.sample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.leodan.readmoreoption.ReadMoreOption

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewExample: TextView = findViewById(R.id.example)

        val readMoreOption = ReadMoreOption.Builder(this)
            .textLength(3)
            .labelUnderLine(true)
            .textLengthType(ReadMoreOption.TYPE_LINE)
            .expandAnimation(true)
            .build()

        readMoreOption.addReadMoreTo(textViewExample, R.string.text_value)


    }
}