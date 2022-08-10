package com.ivan.garcia.core.realm

import io.realm.RealmObject

class User : RealmObject {
    var name: String = ""
    var age: Int = 0
    var height: Float = 0f
    var wight: Float = 0f
}