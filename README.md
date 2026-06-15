# NutriGuide - Android Java Nutrition App

NutriGuide is a Java-based Android application built for the Android Development course project requirements. It lets users search foods or packaged products and view nutrition facts fetched from the free OpenFoodFacts API.

## Features

- Two screens:
  - `SearchActivity`: user input, validation, API-backed search results.
  - `DetailActivity`: nutrition details for the selected food.
- Free external API integration: OpenFoodFacts JSON search endpoint.
- Responsive layouts using `ConstraintLayout`.
- MVC-style structure:
  - **Model:** `FoodItem`
  - **View:** Android Activity and RecyclerView adapter classes in `view`
  - **Controller:** `NutritionController`
  - **Data/API:** `NutritionApiClient`, `OpenFoodFactsApiClient`, `NutritionJsonParser`
- Error handling for invalid input, no results, network failures, and malformed API responses.
- Unit tests for validation, JSON parsing, and controller behavior.
- Basic Espresso UI tests for input validation and detail screen rendering.

## Project Structure

```text
app/src/main/java/com/example/nutritionapp/
  controller/       Search flow coordination
  model/            Food data model
  network/          API client and JSON parser
  util/             Input validation
  view/             Activities and RecyclerView adapter
```

## API

The app uses:

```text
https://world.openfoodfacts.org/cgi/search.pl
```

No API key is required. The app requests product names, brands, serving sizes, and nutrition values per 100 g.

## Running

Open the project in Android Studio and run the `app` module on an emulator or Android device with internet access.

## Tests

From Android Studio or an Android/Gradle-enabled environment:

```bash
./gradlew test
./gradlew connectedAndroidTest
```

The current source includes focused JVM unit tests and Android UI tests aligned with the course requirements.