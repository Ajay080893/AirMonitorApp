package com.example.assignment.db.dbservice


import com.example.assignment.db.dbmodel.CityList
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults


interface CityListInterface {

    fun addOrUpdateCity(realm: Realm, track: RealmList<CityList>) : Boolean

    fun getCityList(realm: Realm) : RealmResults<CityList>
}