
# Anime-Assessment


## Overview
This Android application retrieves and displays a list of popular or top-rated anime using the Jikan API. The project is built using Kotlin and XML, incorporating various libraries for efficient data handling and user interface design. This README outlines the project's components, functionality, and development approach.

## Dependencies
To build this application, the following dependencies are included in the build.gradle file:

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.airbnb.android:lottie:6.4.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
}


## Components Used

- Retrofit: For making API calls to the Jikan API.
- Picasso: For loading and displaying anime images.
- ViewModel: To manage data and business logic, following the MVVM architecture pattern.
- RecyclerView: To display the list of anime, utilizing an adapter for efficient data binding.
- Adapter: Custom adapter designed to inflate and bind anime data to each RecyclerView item.
- Iframe Video Player: To play anime trailers within the app.
- Lottie: For displaying loaders and animations, enhancing the user experience.
- Coroutine: For handling asynchronous operations, such as API calls, efficiently.

## Project Flow

1. Launch : The application starts, and the user is presented with the Anime List Page.
2. API Call: The ViewModel initiates an API call using Retrofit to fetch the list of top-rated anime from the Jikan API.
3. Loading Indicator: While the data is being fetched, a loader (using Lottie) is displayed to indicate the loading process.
4. Error Handling: If the API call fails, an error message is displayed to the user, and the application handles the error gracefully.
5. Data Binding: Once the data is received, it is processed and passed to the adapter, which then binds the data to the RecyclerView, displaying the list of anime.
6. Item Click: When an anime item is clicked, the application navigates to the Anime Detail Page.
7. Detail Page: The Anime Detail Page fetches detailed information about the selected anime, including its trailer, and displays it using an iframe video player.

## Features

 1. Anime List Page
- Description The home screen displays a list of popular or top-rated anime series.
- Details displayed
  - Title
  - Number of Episodes
  - Rating (e.g., MyAnimeList score)
  - Poster Image
- API Endpoint: 
  - To fetch the top anime, the following API endpoint is used:
  
  GET https://api.jikan.moe/v4/top/anime
  

 2. Anime Detail Page
- Description: When an anime is clicked on the Anime List Page, the user is taken to the Anime Detail Page, which shows more detailed information about the selected anime.
- Details displayed:
  - Video Player to play the trailer (if available); if not, display the poster image.
  - Title
  - Plot/Synopsis
  - Genre(s)
  - Main Cast
  - Number of Episodes
  - Rating
- API Endpoint: The specific endpoint for fetching anime details can be structured as follows:
 
  GET https://api.jikan.moe/v4/anime/{id}
  
  Replace {id} with the specific anime ID to retrieve detailed information.

## Development Approach
The project is developed with a focus on the MVVM (Model-View-ViewModel) architecture, which separates the application logic into three interconnected components. This approach enhances the maintainability, testability, and scalability of the application.

- Model: Represents the data and the business logic of the application, which in this case involves fetching and processing anime data from the Jikan API.
- View: The user interface layer, responsible for displaying the data to the user. In this project, it includes activities and fragments designed with XML.
- ViewModel: Acts as an intermediary between the Model and the View, exposing the data as observables and providing methods to modify the data. It handles the business logic and interacts with the repository to fetch data.

## Installation

To run this project, clone the repository to your local machine and open it in Android Studio. Make sure you have the necessary environment set up, including the Android SDK and any other tools required for Android development. Once cloned, you can build and run the application in an emulator or physical device.

### Required Permissions
Don't forget to add the necessary permissions in the AndroidManifest.xml file to access the internet:


<uses-permission android:name="android.permission.INTERNET"/>


## Usage
1. Launch the application.
2. The Anime List Page displays a list of top-rated anime.
3. Click on any anime to view detailed information on the Anime Detail Page.

## Conclusion
This application demonstrates a well-structured approach to developing an Android application using Kotlin and XML, incorporating various libraries for efficient data handling and user interface design. It serves as a solid foundation for further development and customization, showcasing popular anime series to users.
