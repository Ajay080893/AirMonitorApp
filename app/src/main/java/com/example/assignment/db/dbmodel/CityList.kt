package com.example.assignment.db.dbmodel

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class CityList (
    open var timeMillis: Long? = null,
    @field:SerializedName("city")
    @PrimaryKey  open var city: String? = null,
    @field:SerializedName("aqi")
    open var aqi: String? = null
): RealmObject() {
}




