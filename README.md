Android Drawable Generator Gradle Plugin
========================================

This Gradle plugin intends to automatically generates the missing assets for some densities. You can configure the plugin to set a reference density and a minimum density

    adg {

        refDensity "xxhdpi"
        minDensity "ldpi"
        forceOverwrite true

    }

This plugin relies on ImageMagick to perform the resize. You must have it installed and available in your PATH variable.
Make sure that Android Studio has the right PATH variable set.

WORK IN PROGRESS
----------------



TODO
----

Handle build variants
Provide binary on Maven Central
Handle qualifier values