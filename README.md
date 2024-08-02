# Vama Coding Challenge
#### Authors
- Pablo Lamas

### Introduction
The Challenge is to create a sample app for review. Create a sample Android app that displays 
the top 100 music albums across all genres

This project implements a Kotlin-based Android application using the 
Model-View-ViewModel (MVVM) architecture pattern. 

### App Features
- Connects to a remote Album APi
- Connects to a local database 
- Stores data in Realm Database in app
- Displays list of Albums from network or database
- Select a single Album and show details of


### Prerequisites
This project assumes a base knowledge of Kotlin and Android, such as
Activities, JetPack Compose, Dependency injection, Data Binding, Retrofit, Realm

### Model
The Model consists of two parts

- Database Class
- DAO Interfaces

### ViewModel
The Model consists of two parts

- ViewModel
- Repositories

### View
The View is the UI presentation logic. It consists of

- Activities
- Presentation data class
- Composable objects


### Architecture decisions
I make the decision to not implements fragments on this app, for my knowledge and investigation 
about jetpack compose, fragments is not necessary to implement any more as this is now a 
declarative language where developer declare all views using code, and those composable views are 
now a component on its own.

This approach can change on the future when the use of jetpack compose became more common. 
So is a good practice read and search for new architecture patterns and try to keep knowledge 
updated. 


### Test recording video
You can found the TestScreenRecording.mov on this folder where i show how the app runs with and 
without internet connection.