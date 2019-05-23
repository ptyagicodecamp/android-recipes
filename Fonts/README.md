Title: How to start using custom fonts in an Android App under 5 minutes
Date: 2017-07-26 10:15PM
Authors: ptyagi
Category: Development
Tags: android, fonts, dev, 5-minutes-series
Summary: This post is part of the "under 5 minutes series". In this post I'll talk about how did I set up custom fonts to render text in TextView in an Android app.

This post is part of the "under 5 minutes series". In this post I'll talk about how did I set up custom fonts to render text in TextView in an Android app.

### Introduction
In this recipe, I'll show you how did I use a custom font to write text "Hello Fonts !" in `TextView` widget.

### Download and setup custom Font
First, decide on the font that you're planning to use. Download it.
I'm referring this sample font as "dot2dot.ttf".

Second, add this custom font in resource directory of android app.
![Alt font_1.png]({attach}../images/font/font_1.png)

Clicking on font will open it in side window.
![Alt font_2]({attach}../images/font/font_2.png)

### Creating Font family (XML resource file)
Now, create the font family or a xml resource file to access this custom font from code.
Right click on `res/font` folder and create a xml file:
![Alt fontfamily]({attach}../images/font/font_family.png)

![Alt fontfamily2]({attach}../images/font/fontfamily2.png)


### Using the Font Family in TextView
Here's how font resource created in previous step can be accessed from `TextView` widget:
```
 <TextView
        ...

        android:text="Hello Fonts !"
        android:fontFamily="@font/dot2dot"
        android:textSize="36sp"

        ...
       />
```

That's pretty much all you've to do to start using a custom Font in your Android App.

This is how the sample app's output look like:
![Alt output sample app](../images/font/output.png)


Checkout the source code on [Github](https://github.com/ptyagicodecamp/android-recipes/tree/develop/Fonts)

Checkout this article on [my blog](https://ptyagicodecamp.github.io/how-to-start-using-custom-fonts-in-an-android-app-under-5-minutes.html)

Keep Exploring !

***Note***: This feature of Fonts in XML is got introduced in Android O.

***Reference*** : [Documentation](https://developer.android.com/preview/features/working-with-fonts.html)
