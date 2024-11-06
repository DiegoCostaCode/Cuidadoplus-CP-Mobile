package com.example.cp1_mobile.model

data class User(
    var name: String,
    var lastName: String,
    var email: String,
    var password: String,
    var dayOfBirth: String,
    var medicines: List<Medicine>) {
    constructor() : this("", "", "", "", "", emptyList())
}