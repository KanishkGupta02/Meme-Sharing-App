# Meme Sharing App
 A meme sharing app built using Kotlin integrated with Volley and Gradle Library that uses Reddit-meme api
 Created a meme-sharing app that uses Volley Library(for networking calls as it’s faster) for making HTTP requests. Added its dependency in gradle:app & added the Internet permission in the manifest file.
Made a load function in MainActivity for making requests and fetching the response and importing the required libraries.
Made a JsonRequest for fetching the “url”.
To add the fetched url in the ImageView, the Glide library has been used. Added the following 
repositories in gradle: Project section

repositories {
  google()
  mavenCentral()
}

& the following dependencies in app section

dependencies {
  implementation 'com.github.bumptech.glide:glide:4.14.2'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'
}

Also, added the following in gradle: settings (as it was throwing error during sync)

dependencyResolutionManagement {
   repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
   repositories {
       google()
       mavenCentral()
       maven { url 'https://jitpack.io' }
   }
}

Afterward added the following plugin in the gradle: app section 

id 'kotlin-android-extensions' (because image id in the xml section could not be accessed in the , hence to import the library plugin was required)

Added a singleton class:
Singleton Design Pattern is basically limiting our class so that whoever is using that class can only create 1 instance from that class.
As the application makes constant use of the network, it’s probably most efficient to set up a single instance of RequestQueue that will last the lifetime of your app. That’s why we used singleton design pattern.
