Title: Adding Menu Items in Navigation Drawer Dynamically
Date: 2017-4-19 12:00AM
Authors: ptyagi
Category: Development
Tags: Android, Material Design, UI/UX
Summary: Quick code recipe to demonstrate the dynamic addition of menu items in Navigation Drawer.

# Adding Menu Items in Navigation Drawer Dynamically

## Introduction
Android Studio provides support to add Navigation Drawer Activity from IDE itself. With such Activity,
it automatically generates menu items for navigation drawer in form of a xml file resource located
inside `res/menu` directory. By default, this xml file is named `activity_main_drawer.xml`, and contains
menu items statically like this:
```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <group android:checkableBehavior="single">
        <item
            android:id="@+id/nav_camera"
            android:icon="@drawable/ic_menu_camera"
            android:title="Import" />
        <item
            android:id="@+id/nav_gallery"
            android:icon="@drawable/ic_menu_gallery"
            android:title="Gallery" />
        <item
            android:id="@+id/nav_slideshow"
            android:icon="@drawable/ic_menu_slideshow"
            android:title="Slideshow" />
        <item
            android:id="@+id/nav_manage"
            android:icon="@drawable/ic_menu_manage"
            android:title="Tools" />
    </group>

    <item android:title="Communicate">
        <menu>
            <item
                android:id="@+id/nav_share"
                android:icon="@drawable/ic_menu_share"
                android:title="Share" />
            <item
                android:id="@+id/nav_send"
                android:icon="@drawable/ic_menu_send"
                android:title="Send" />
        </menu>
    </item>

</menu>
```

What if you want to generate or add more menu items dynamically ? If you're like me, then you would
not want to have support to add menu items without making changes in static file. One reason for doing so
is to be able to read new menu items data from remote servers and generate UI on the fly. In other words,
automating UI generation !

Actually, its pretty simple to do so !

#### Show me code
Here’s code that’s responsible for generating new menu items on the fly.

```
private void addMenuItemInNavMenuDrawer() {
    NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

    Menu menu = navView.getMenu();
    Menu submenu = menu.addSubMenu("New Super SubMenu");

    submenu.add("Super Item1");
    submenu.add("Super Item2");
    submenu.add("Super Item3");

    navView.invalidate();
}
```

Call `addMenuItemInNavMenuDrawer` in `onCreate()` method before launching your app.
```
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ....

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
        addMenuItemInNavMenuDrawer();
    }
```

And that's pretty much it !

##### Before Adding New Submenu
[Here's](https://github.com/ptyagicodecamp/ptyagicodecamp.github.io/blob/master/images/navdrawermenu/before_adding_menus.png)
how Navigation drawer looks like before adding new sub menu.

##### After Adding New Submenu
[Here's](https://github.com/ptyagicodecamp/ptyagicodecamp.github.io/blob/master/images/navdrawermenu/after_adding_menus.png) Navigation Drawer after adding brand new submenu.

You can checkout source code at [github here](https://github.com/ptyagicodecamp/android-recipes/tree/develop/NavDrawerDynamicMenu)