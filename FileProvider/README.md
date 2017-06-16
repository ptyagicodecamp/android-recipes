Title: Accessing Pictures using FileProvider
Date: 2017-4-13 12:02PM
Authors: ptyagi
Category: Development
Tags: Android, FileProvider
Summary: This post shows usage of FileProvider in Android App to retrieve the image file for Camera intent usage.


# Accessing Pictures using FileProvider

FileProvider is the backbone which helps in secure sharing of files across file-system on Android device.

I’m using example of taking picture using camera on Android device to understand how FileProvider works. This Project demos does two things:
<br>
1. Takes Picture: <a href="https://github.com/ptyagicodecamp/ptyagicodecamp.github.io/tree/master/images/fileprovider/s1.png">FAB to take picture</a>
<br>
2. Displays picture taken: <a href="https://github.com/ptyagicodecamp/ptyagicodecamp.github.io/tree/master/images/fileprovider/s2.png">Displaying recent picture</a>

#### Scenario
User Clicks FAB to take picture. Picture got displayed in ImageView.
we want to know what went behind the scenes from clicking the FAB to displaying a picture.
How/where the image file got created ? What made it available to Camera Intent ?
Yes, you guessed it ! FileProvider made newly created blank image file available to Camera Intent to store pixels/binary data of new pictures in it.


#### Internals
What happens when user clicks on FAB to initiate taking picture ?
First of all a temporary file get created under picture directory for app. The storage location
will be something like this: `/storage/emulated/0/Android/data/org.pcc.fileprovider/files/Pictures`

This file is queried using [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html)
and passed to the camera intent.

Code to query recently created image file in internal picture directory for app:
```
Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
Uri imgUri = FileProvider.getUriForFile(this,
                    "org.pcc.fileprovider", imageFile.getFile());
takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
startActivityForResult(takePictureIntent, REQUEST_CODE_IMAGE_CAPTURE);
```

#### Configuring FileProvider in Android Studio Project

It's a two step process. First you've to declare `<provider>` tag in your app's
`AndroidManifest.xml`. Second, you will need to create `file_paths.xml` inside `res/xml` folder.

##### AndroidManifest.xml

`<provider>` tag has to be declared inside `<application>` tag.
```
<application
        ...>
        <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="org.pcc.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
       ....
</application>        
```

##### file_paths.xml
Create a file `file_paths.xml` inside `res/xml` folder of your app. This file
will have these contents in it:
```
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path name="my_images" path="Android/data/org.pcc.fileprovider/files/Pictures" />
</paths>
```

That's it !

Checkout [source code](https://github.com/ptyagicodecamp/android-recipes/tree/develop/FileProvider) in github to see `FileProviderDemo` in action.
