package com.example.assignment.db.dbserviceimpl


import com.example.assignment.db.dbmodel.CityList
import com.example.assignment.db.dbservice.CityListInterface
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults


class CityListService : CityListInterface {
    /**
     * Methods for add list of data in DB
     */
    override fun addOrUpdateCity(realm: Realm, city: RealmList<CityList>): Boolean {
        try {

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(city)
            realm.commitTransaction()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    /**
     * Method for get all list of data
     */
    override fun getCityList(realm: Realm): RealmResults<CityList> {
        var data=realm.where(CityList::class.java).findAll()
        return data
    }

}