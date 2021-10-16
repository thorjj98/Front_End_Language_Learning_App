package com.example.urimandtongueim.model

class User (var id: String, var username: String, var firstName: String, var lastName: String, var hashedPassword: String) {

    override fun equals(other: Any?)
            = (other is User)
            && id == other.id
            && username == other.username
            && firstName == other.firstName
            && lastName == other.lastName
            && hashedPassword == other.hashedPassword

}