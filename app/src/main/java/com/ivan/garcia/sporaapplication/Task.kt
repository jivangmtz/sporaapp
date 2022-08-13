package com.ivan.garcia.sporaapplication

//import io.realm.RealmObject
import io.realm.kotlin.types.RealmObject

class Task : RealmObject {
    var name: String = "new task"
    var status: String = "Open"
}