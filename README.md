# Udacity-Advance-Android-Dev

# Baking App

![alt text](https://d.top4top.net/p_669qeydy1.jpg)


#### This project shows the recipes and the preparation steps with their videos.

##### This project shows the following functionalities:

1-Recipes in RecyclerView in linear layout(vertical orientation) when the screen is portrait and a Gridview Orientation when the device is in landscape.

2-The ingredients and the steps of the recipe in a nested scrollview. This becomes a two pane UI when in landscape mode in tablet devices. It remains a single pane in phones (no matter what the orientation is).

3-When the user changes the recipe details they want to see, the widget also changes accordingly.

4-The user can navigate between recipes through previous/next buttons.

5-The user can navigate between recipe steps when in single pane UI design.

6-There is a Idling resource test for the first loading of recipe from network and a click on an item of the RecyclerView.

7-There is a full fledged UI Test generated using Espresso Test Recorder too.

---

## Project Overview

##### You will productionize an app, taking it from a functional state to a production-ready state. This will involve finding and handling error cases, adding accessibility features, allowing for localization, adding a widget, and adding a library.
---
## Why this Project?

##### As a working Android developer, you often have to create and implement apps where you are responsible for designing and planning the steps you need to take to create a production-ready app. Unlike Popular Movies where we gave you an implementation guide, it will be up to you to figure things out for the Baking App.
---

## General App Usage


 App should display recipes from provided network resource.
 App should allow navigation between individual recipes and recipe steps.
 App uses RecyclerView and can handle recipe steps that include videos or images.
 App conforms to common standards found in the Android Nanodegree General Project Guidelines.
Components and Libraries

 Application uses Master Detail Flow to display recipe steps and navigation between them.
 Application uses Exoplayer to display videos.
 Application properly initializes and releases video assets when appropriate.
 Application should properly retrieve media assets from the provided network links. It should properly handle network requests.
 Application makes use of Espresso to test aspects of the UI.
 Application sensibly utilizes a third-party library to enhance the app's features. That could be helper library to interface with Content Providers if you choose to store the recipes, a UI binding library to avoid writing findViewById a bunch of times, or something similar.
