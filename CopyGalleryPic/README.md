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
