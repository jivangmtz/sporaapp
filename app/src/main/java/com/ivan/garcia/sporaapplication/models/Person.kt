package com.ivan.garcia.sporaapplication.models

class Person (private val name: String, private val lastName: String, val age: Int) {

    override fun toString() : String{
        return "$name $lastName"
    }
}