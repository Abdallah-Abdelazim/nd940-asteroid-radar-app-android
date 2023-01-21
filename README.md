# Asteroid Radar

Asteroid Radar is an Android app to view the asteroids detected by NASA that pass near Earth. Second
project
in [Udacity Advanced Android Kotlin Development Nanodegree (nd940)](https://www.udacity.com/course/android-kotlin-developer-nanodegree--nd940)
.

## Building & running the app

Before building the app you need to generate & add an **API key** for the Nasa APIs.

1. Go to [api.nasa.gov](https://api.nasa.gov/) to generate your API key.
2. In the root of your project, create a new file named `secrets.properties`.
3. Embed your API key in the below line & append that line to the `secrets.properties` file created
   in the previous step.

  ```groovy
  NASA_API_KEY = "ENTER_YOUR_API_KEY_HERE_BETWEEN_THE_QUOTES"     
  ```
