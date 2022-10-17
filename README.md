# TMDBflix

An android app built using [Jetpack Compose](https://developer.android.com/jetpack/compose) and [Paging 3](https://developer.android.com/jetpack/androidx/releases/paging) that consumes [TMDB API](https://developers.themoviedb.org/3/getting-started/introduction) to display the current trending, on TV, top rated, and popular tv-shows.
# Screenshots
 <img src="https://user-images.githubusercontent.com/41413741/196089793-03edaa9b-3dd2-4c2f-9401-bd520f4b2863.jpg" width="250" />  <img src="https://user-images.githubusercontent.com/41413741/196089710-a38c04e0-7a85-49d5-b1e9-9dc4dd58603d.jpg" width="250" /> <img src="https://user-images.githubusercontent.com/41413741/196089796-6b646133-5da7-4682-9650-87c1f7df775a.jpg" width="250" /> 
 ---
 <img src="https://user-images.githubusercontent.com/41413741/196089831-95bdd673-56a0-4670-b266-52e67c839083.jpg" width="250" /> <img src="https://user-images.githubusercontent.com/41413741/196092090-b9e53514-d34e-43c6-a530-7f8b157e98e1.jpg" width="250" /> <img src="https://user-images.githubusercontent.com/41413741/196089838-b947798c-3f8a-4ce2-8001-bb4ee7d90891.jpg" width="250" /> 
 
---
# Tech Stack

- [Clean Architecture](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011) - Separation of code in different modules or sections with specific responsibilities making it easier for maintenance and further modification.
- [Hilt](https://dagger.dev/hilt/) - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [Jetpack Components](https://developer.android.com/jetpack)
    - [Jetpack Compose](https://developer.android.com/jetpack/compose)- Modern toolkit for building native UI.
    - [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite used for offline data caching.
    - [View Model](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjw-rOaBhA9EiwAUkLV4uTWtmhSLWBc9oaYTl_gJJsgJiF-w2indn-p5PnLtnXKs-9elvGQlxoC1jkQAvD_BwE&gclsrc=aw.ds) -ViewModel is a class that is responsible for preparing and managing the data for an Activity or a Fragment . It also handles the communication of the Activity / Fragment with the rest of the application (e.g. calling the business logic classes).
    - [Animation](https://developer.android.com/jetpack/compose/animation) - Jetpack Compose provides powerful and extensible APIs that make it easy to implement various animations in your app's UI
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
- [Compose Destinations](https://composedestinations.rafaelcosta.xyz/) - A KSP library that processes annotations and generates code that uses Official Jetpack Compose Navigation under the hood.
- [Accompanist - System UI Controller](https://github.com/google/accompanist/blob/main/systemuicontroller) - A library that provides easy-to-use utilities for recoloring the Android system bars from Jetpack Compose.
- [CoilImage Loader](https://github.com/coil-kt/coil) - An image loading library for Android backed by Kotlin Coroutines. 
- [Compose Pagination](https://developer.android.com/jetpack/androidx/releases/paging) - The Paging Library makes it easier for you to load data gradually and gracefully within your app.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client 
and supports coroutines out of the box.
- [GSON](https://github.com/square/gson) - JSON Parser,used to parse 
requests on the data layer for Entities and understands Kotlin non-nullable 
and default parameters.
- [OkHttp Logging Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flows](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
