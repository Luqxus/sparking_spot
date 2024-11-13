package com.example.springbank.screens


sealed class  Screens(val route: String) {
    data object Home: Screens("home")
}