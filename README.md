
## Screenshots

<p float="left">
<img img width="200" height="400" src="https://github.com/Tonnie-Dev/CountriesPad/blob/master/media/scr_shot_flags.png"> &nbsp;&nbsp;&nbsp;&nbsp;
<img img width="200" height="400" src="https://github.com/Tonnie-Dev/CountriesPad/blob/master/media/scr_shot_%20searching.png"> &nbsp;&nbsp;&nbsp;&nbsp;   
<img img width="200" height="400" src="https://github.com/Tonnie-Dev/CountriesPad/blob/master/media/scr_shot_belize.png">   
</p>


# Countries Pad

Countries Pad display a list of countries obtained from [Rest Countries API](https://restcountries.com/v3.1/all). You can view the selected country
on a map. Additionally you can filter countries by area, population or by name.




<a href='https://play.google.com/store/apps/details?id=com.uxstate.countriespad'><img alt='Get it on Google Play' src='https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/google_play_store%20_badge.png' width="280"/></a>
## Architecture
Countries Pad is implemented using Android Clean Architecture with these highlights:


* Uses Model-View-ViewModel (MVVM) pattern with Clean Architecture (data, domain and presentation)
* It has 2 Screen destinations which use [Compose Destination](https://composedestinations.rafaelcosta.xyz/) to manage navigation


#### Overview Screen 1 
This screen is based on a LazyVerticalGrid to display country flags in pgn formart.

There is a feature to search countryby name, cioc (or cca3) code.

![App Screenshot](https://github.com/Tonnie-Dev/CountriesPad/blob/master/media/scr_shot_%20kenya.png)
![App Screenshot](https://github.com/Tonnie-Dev/CountriesPad/blob/master/media/scr_shot_%20searching.png)

#### Details Screen
This screen displays the country's Coat of Arms as well as the Country's Flag. It also
displays a map showing the country's location on the globe.


![App Screenshot](https://github.com/Tonnie-Dev/CountriesPad/blob/master/media/scr_shot_azerjabain.png)
![App Screenshot](https://github.com/Tonnie-Dev/CountriesPad/blob/master/media/scr_shot_belize.png)



## Tech Stack
Countries Pad is implemented using Android Clean Architecture, Retrofit2,
Dagger Hilt, ViewModel, Coroutines, Room, Navigation Components and some
other libraries from the [Android Jetpack](https://developer.android.com/jetpack). 
### Technologies used:

* [Android KTX](https://developer.android.com/kotlin/ktx) - helps to write more concise, idiomatic Kotlin code.
* [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern toolkit for building native Android UI
* [Material Design 3](https://m3.material.io/) - an adaptable system of guidelines, components, and tools that support the best practices of user interface design.
* [Compose Destinations](https://github.com/raamcosta/compose-destinations) - used to handle all navigations and arguments passing while hiding the complex, non-type-safe and boilerplate code
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [Dagger Hilt](https://dagger.dev/hilt/) - used for Dependency Injection.
* [Coil](https://coil-kt.github.io/coil/) - an image loading library for Android backed by Kotlin Coroutines
* [Retrofit](https://square.github.io/retrofit/) a REST Client for Android which makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.
* [Coroutines and Kotlin Flow](https://kotlinlang.org/docs/reference/coroutines-overview.html) - used to manage the local storage i.e. `writing to and reading from the database`. Coroutines help in managing background threads and reduces the need for callbacks.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Timber](https://github.com/JakeWharton/timber) - a logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [Lottie Animations](https://lottiefiles.com/) - provides Lightweight and scalable animations files
* [Maps Compose library](https://developers.google.com/maps/documentation/android-sdk/maps-compose) - for displaying Google Maps in compose
## Deployment

Countries Pad requires a minimum API level of 21. Clone the repository or download the code directly. 

You don't need an API Key for Rest Countries API




## API Reference

###
Base URL
```http
  https://restcountries.com/
```

#### Get Countries Json String

```http
  GET v3.1/all
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `none` | `none` | *No Parameter needed* |


This request returns a Json String

### Data Points

| Data Point    | Type                 |
|---------------|----------------------|
| name          | String               |
| officialName  | String               |
| List          | List<String>         |
| capital       | String               |
| region        | String               |
| subRegion     | String               |
| List          | List<String>         |
| Double        | Pair<Double, Double> |
| flagUrl       | String               |
| coatOfArmsUrl | String               |
| ciocCode      | String               |
| area          | Int                  |
| population    | Int                  |


### Parsing Json String

Create an interface with a function to iterate through the returned JSON String extract the data points.

```kotlin

interface JsonStringParser<T> {

    fun parseJson(jsonString: String): List<T>
}

```
## Contributing

Contributions to make Countries Pad better are always welcome!

If you are interested in seeing a particular feature implemented in this app, please open a new issue after which you can make a PR!

![Alt](https://repobeats.axiom.co/api/embed/84dfd3cd94832805dbcaa3569ec855d19e5c9401.svg "Repobeats analytics image")


## License

MIT License

Copyright (c) [2022] [Tonnie Dev]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

