# Clean architecture by Leva

This simple application shows the list of pokemon with their details
##### If the next icon appears in the list, it means that the item has already been downloaded and can also be used offline
![N|Solid](https://raw.githubusercontent.com/antolevq/applyJobAssignment/master/presentation/src/main/res/drawable/opened.png) 

##### This icon is used for all those items not yet downloaded. If it is opened without internet connection, an error view is shown.
![N|Solid](https://raw.githubusercontent.com/antolevq/applyJobAssignment/master/presentation/src/main/res/drawable/closed.png) 

## Multi module application
The application consists of 3 modules:

  - Data: To find information
  - Domain: Business logic
  - Presentation: To show on device
  
## Used dependency

Here the list of al the dependency of the application:
  - Glide
  - Coroutine
  - Koin
  - Lottie
  - Retrofit
  - Gson
  - Room
  - Robolectric
  
  ## Architecture
Clean architecture + MVVM
  
  ## Test coverage
The only integrated Unit test, is located in the Data module and was used to test the correct relationships between the tables (oneToMany & oneToOne)
