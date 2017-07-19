Title: How did I set up multiple flavors for an Android App under 5 minutes
Date: 2017-07-18 3:50PM
Authors: ptyagi
Category: Development
Tags: android, gradle, flavors, build-variants, dev, 5-minutes-series
Summary: This post is part of the "under 5 minutes series". In this post I'll talk about how did I set up flavors for an Android app.

Let's start with understanding few key terms.

## Understanding differences between "Build Types", "Flavor" and "Build Variant"

***Build Types:
Build Type refers to build and packaging settings like signing configuration for a project.

***Flavor:
A flavor is used to specify custom features, minimum and target API levels, device and API requirements like layout, drawable and custom code (for example, if production code is slightly different than development code).

***Build Variant:
The combination of Build Type and Flavor is known as Build Variant.

And, now time to dig deeper !

## Android Apps: Flavors

## Motivation
The motivation behind ***Android Flavors*** is ***code reuse*** and ***code maintainability***.
Time to time, we might want to publish different versions of the same app with slight changes in
assets or functionality. For example, in free version of the app, you may want to show ads while in
paid version, you may decide not to show ads and integrate in-app purchase or upfront price rather.
That's where ***Android Flavors*** help us to achieve this flexibility.

## Setting up Flavors in `build.gradle`

```
android {
    flavorDimensions "version"

    productFlavors {
        freeVersion {
            dimension "version"

        }

        paidVersion {
            dimension "version"
        }
    }
}
```

These newly created flavors appears in "Build Variants" tab of Android Studio:

![Alt Build Variants](../images/flavors/build_variants_1.png)

Note 1: You'll have to choose the configuration from "Build Variants" drop down menu before runnind the app on device/emulator.

Note 2: [Starting Android Studion 3.0](https://developer.android.com/studio/build/gradle-plugin-3-0-0-migration.html?utm_source=android-studio#variant_aware),
 `flavorDimension` attribute needs to be added.

## Application Id
Each flavor can have its own application Id. An 'applicationId' makes a app in Play Store unique.
In this example, the free version will be published on Play Store with packageId/applicationId as `com.pcc.flavors.free`
and paid one will have `com.pcc.flavors.paid`.

Note: I'll be showing `build.gradle` with all the configuration until this point.

```
android {
    flavorDimensions "version"

    productFlavors {
        freeVersion {
            dimension "version"

            applicationId "com.pcc.flavors.free"

        }

        paidVersion {
            dimension "version"

            applicationId "com.pcc.flavors.paid"
        }
    }
}
```

## Configuring App's name
Each flavor can have its own name as well. For example, free version has app name shown to users
in Play Store as "Free Great App" and paid version has its name displayed in Play Store as "Paid Great App".
Let's see how this can be configured in `build.gradle`.

```
android {
    flavorDimensions "version"

    productFlavors {
        freeVersion {
            //select the dimension of flavor
            dimension "version"

            //configure applicationId for app published to Play store
            applicationId "com.pcc.flavors.free"

            //Configure this flavor specific app name published in Play Store
            resValue "string", "flavored_app_name", "Free Great App"

        }

        paidVersion {
            dimension "version"

            applicationId "com.pcc.flavors.paid"
            resValue "string", "flavored_app_name", "Paid Great App"
        }
    }
}
```
Don't forget to use `flavored_app_name` string resource from `AndroidManifest.xml` to defined app's name:
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="org.pcc.flavors">

    <application
        android:label="@string/flavored_app_name"
    </application>

</manifest>
```
## Flavor specific Icons for apps
Each differently named/flavored app may want to have its own icon. Lets see how would we achieve this.
First we have to create a flavor specific directory at the same level of `main`. Basically, we need to mimic
the `main` directory for each flavor to support fully functional flavors (when there's code changes are involved).
These directories must be named same as its flavor definition names. In our example, these two additional
directories are : `freeVersion` and `paidVersion`.

![Alt Flavored Icons](../images/flavors/flavored_icons.png)

Make sure that you've the icon's name same as referenced from `AndroidManifest.xml`
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="org.pcc.flavors">

    <application

        android:icon="@mipmap/ic_launcher"

    </application>

</manifest>
```

## Finally, flavor some code !
It's very similar to flavoring icons for specific app. Remember that you've the same directory/package
structure across all the flavors. That's how the changes are overridden.
For demonstration purposes, I'll update `TextView` widget text differently for each app. Free version's
`TextView` will read "Hello Free Great App !", and Paid version will read : "Hello Paid App !".
It involves two steps:
1. Make sure that you've the same code directory structure.
2. Write your custom code !

Note: There's one gotcha though. You can't have the same class defined in `src/main/java` and `src/<flavorNmae>/java` directories. Gradle will throw Duplicate class error while building. Either you can have one class in `src/main/java` or in other flavors. I've `MainActivity2` in `src/freeVersion/java` and `src/paidVersion/java`, but not in `src/main/java`. `AndroidManifest.xml` has reference to `MainActivity2`, so it picks `MainActivity2` for a selected build variant's flavor.

Project structure would look like this:
![Alt flavored code](../images/flavors/flavor_code.png)

Checkout the demo app in [Github](https://github.com/ptyagicodecamp/android-recipes/tree/develop/Flavors)

Checkout this article on [my blog](https://ptyagicodecamp.github.io/how-did-i-set-up-multiple-flavors-for-an-android-app-under-5-minutes.html)