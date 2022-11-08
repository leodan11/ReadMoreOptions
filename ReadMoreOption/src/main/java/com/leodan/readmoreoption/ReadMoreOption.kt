/*
 *  Copyright (c) 2022 Leodan.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.leodan.readmoreoption

import android.content.Context
import com.leodan.readmoreoption.base.ReadBaseMoreOption

class ReadMoreOption(context: Context,
                     textLength: Int,
                     textLengthType: Int,
                     moreLabel: String?,
                     lessLabel: String?,
                     moreLabelColor: Int,
                     lessLabelColor: Int,
                     labelUnderLine: Boolean,
                     expandAnimation: Boolean):
    ReadBaseMoreOption(context = context,
    textLength = textLength,
    textLengthType = textLengthType,
    moreLabel = moreLabel,
    lessLabel = lessLabel,
    moreLabelColor = moreLabelColor,
    lessLabelColor = lessLabelColor,
    labelUnderLine = labelUnderLine,
    expandAnimation = expandAnimation) {


    /**
     * Creates an [ReadMoreOption] with the arguments supplied to this builder.
     *
     * @property context The parent context
     * @constructor Create empty Builder.
     */
    class Builder(context: Context): ReadBaseMoreOption.Builder<ReadMoreOption>(context){
        override fun build(): ReadMoreOption {
            return ReadMoreOption(
                context = context,
                textLength = textLength,
                textLengthType = textLengthType,
                moreLabel = moreLabel,
                lessLabel = lessLabel,
                moreLabelColor = moreLabelColor,
                lessLabelColor = lessLabelColor,
                labelUnderLine = labelUnderLine,
                expandAnimation = expandAnimation
            )
        }
    }

    companion object {
        const val TYPE_LINE: Int = 1
        const val TYPE_CHARACTER: Int = 2
    }

}