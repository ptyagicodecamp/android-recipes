FileProvider Demo

This Project demos does two things:
* Takes Picture
* Displays picture taken

#### Scenario
User Clicks FAB to take picture. Picture got displayed in ImageView.

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

Checkout source code in github to see `FileProviderDemo` in action. 
