Android Drawable Generator Gradle Plugin
========================================

This Gradle plugin intends to automatically generates the missing assets for some densities. You can configure the plugin to set a reference density and a minimum density

    adg {

        refDensity "xxhdpi"
        minDensity "ldpi"

    }

This plugin relies on ImageMagick to perform the resize. You must have it installed and available in your PATH variable.

WORK IN PROGRESS
----------------



TODO
----

Handle build variants