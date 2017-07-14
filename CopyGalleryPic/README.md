Title: Android Model-View-Presenter (MVP) Design Pattern
Date: 2017-7-13 1:06PM
Authors: ptyagi
Category: Development
Tags: Android, Android Design Pattern, MVP, Tips & Tricks, Model-View-Presenter
Summary: A Quick tour to Model-View-Presenter (MVP) Android Design Pattern.

# Android Model-View-Presenter (MVP) Design Pattern

## Quick Introduction
There's enough material available on internet about what a
Model-View-Presenter (MVP) design pattern is, and why it should be used.
The motivation behind utilizing this design pattern is to separate UI related code from business
logic. **Presenter** is a facilitator between **View** (UI related code) and **Model** (real data objects).
For example, if a TextView widget wants to display a `Person`'s name (data object model), then View will
send a request to Presenter to fetch Data object's information. Presenter in turn will fetch Model class (`Person` data model)
information and pass it on back to View. That way View would never have to directly interact with Model classes.
So, what's the benefit of this separation ? Any guesses ? Yes, you got it ... Clean code !
Such clean code separates UI related code base from model classes, and makes it super simple to unit tests without
running instrumentation tests.

Alright, I'll be using a simple example to demonstrate the real working of MVP pattern. In this example,
user can pick an image from Gallery, and display it in the main UI (fragment). Along with displaying
picture in ImageView widget, it also shows FilePath below the ImageView.

### Selecting picture from Gallery
This sample app does following 4 things using MVP pattern:

* Allows user to pick a picture from Gallery and shows in an ImageView
* Displays File's path in TextView under ImageView
* Give user option to copy chosen image into app's data directory
* Give user option to rename picture (copied into app's data directory)

### Show me code
There're two Interfaces dedicated to View and Presenter related contracts:
* [ContractView](https://github.com/ptyagicodecamp/android-recipes/blob/develop/CopyGalleryPic/app/src/main/java/org/pcc/copygallerypic/view/ContractView.java#L12:L22) Interface.
```
public interface ContractView {
    //displays image in Imageview widget
    void showImagePreview(@NonNull Uri uri);

    //Displays image file information in textview
    void showImageInfo(String infoSting);

    //That's how a presenter is assigned to a view
    void setUserActionListener(ContractUserActionListener userActionListener);

}
```
* [ContractUserActionListener](https://github.com/ptyagicodecamp/android-recipes/blob/develop/CopyGalleryPic/app/src/main/java/org/pcc/copygallerypic/presenter/ContractUserActionListener.java#L12:L25) Interface
```
public interface ContractUserActionListener {

    //An action taken by user to load a image from gallery into ImageView widget
    void loadImage(Context context, Uri uri) throws IOException;

    //User initiates action to display image file information in textView UI
    void loadImageInfo();

    //User action to copy an image from gallery in to app's picture directory (app's data directory)
    void copyImageIntoAppDir(Context context) throws IOException;

    //User can rename a image file (in app's data directory)
    void renameImage(Context context, String newName);
}
```

Activity or Fragment class implements `ContractView` while Presenter would implements
`ContractUserActionListener`.

In this sample, UI class `ImageViewerFragment` is implementing `ContractView`.
```
public class ImageViewerFragment extends Fragment implements ContractView {
  ...
}
```

And, Presenter class `ImagePresenter` implements `ContractUserActionListener`:
```
public class ImagePresenter implements ContractUserActionListener {
  ...
}
```
`ImageViewerFragment` holds a reference to `ImagePresenter`, so that it can pass
requests to query data from models. At the same time, `ImagePresenter` holds a reference
back to `ImageViewerFragment` to pass back on the results.

That concludes the theory part.

Here are few screenshots from the sample app:

**Start state:**
This is how sample app looks just after start up. The FAB '+' is clicked to select image from gallery.
![Alt launch screen](../images/android-mvp/start.png)

**Picture loaded from Gallery state:**
![Alt Picture loaded from gallery](../images/android-mvp/picture_loaded_from_gallery.png)

**Image copied to App's data dir state:**
![Alt Image copied to App's data dir state](../images/android-mvp/image_copied_to_datadir.png)

**Renaming image - Enter new name:**
![Alt Renaming image](../images/android-mvp/rename_1.png)

**Renaming image - Final:**
![Alt Renaming image](../images/android-mvp/rename_2.png)


###Source Code
Source is available at [Github](https://github.com/ptyagicodecamp/android-recipes/tree/develop/CopyGalleryPic)

Happy Exploring !
