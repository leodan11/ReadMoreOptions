# Read More Options
[![](https://jitpack.io/v/leodan11/ReadMoreOptions.svg)](https://jitpack.io/#leodan11/ReadMoreOptions)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=24)
[![LICENSE](https://img.shields.io/github/license/leodan11/ReadMoreOptions)](https://www.apache.org/licenses/LICENSE-2.0.html)


Convert your TextView in ExpandableTextView with added options ReadMore/ReadLess.


# Demo
![alt text](https://github.com/devendroid/ReadMoreOption/raw/master/assets/rmo-1.0.0.gif)

# Credits

This is just an updated version of [ReadMoreOptions](https://github.com/devendroid/ReadMoreOption) and applying some of the active pull requests in it. 
Credits go completely to its creator and the people who has contributed with those pull requests.

# Installation

<details>
  <summary>Gradle</summary>

- Step 1. Add the JitPack repository to your build file

  Add it in your root build.gradle at the end of repositories:

  ```gradle
  allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
      }
  }
  ```

- Step 2. Add the dependency
  
  ```gradle
  dependencies {
    implementation 'com.github.leodan11:ReadMoreOptions:{latest version}'
  }
  ```
  
</details>

<details>
    <summary>Kotlin</summary>

  - Step 1. Add the JitPack repository to your build file.

    Add it in your root build.gradle at the end of repositories:

    ```kotlin
    repositories {
        ...
        maven(url = "https://jitpack.io")
    }
    ```

- Step 2. Add the dependency
  
    ```kotlin
    dependencies {
      implementation("com.github.leodan11:ReadMoreOptions:${latest version}")
    }
    ```
  
</details>


# Usage

```kotlin
val readMoreOption = ReadMoreOption.Builder(this)
            .textLength(3)
            .labelUnderLine(true)
            //.moreLabelColor(Color.BLUE) Optional
            //.moreLabel(getString(R.string.text_value_read_more)) Optional
            //.lessLabelColor(Color.RED) Optional
            //.lessLabel(getString(R.string.text_value_read_less)) Optional
            .textLengthType(ReadMoreOption.TYPE_LINE) // Or .textLengthType(ReadMoreOption.TYPE_CHARACTER)
            .expandAnimation(true)
            .build()

readMoreOption.addReadMoreTo(textViewExample, R.string.text_value) //Or addReadMoreTo(textViewExample, "Example Text")
```
