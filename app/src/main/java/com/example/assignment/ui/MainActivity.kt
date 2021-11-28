package com.example.assignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.*
import com.example.assignment.data.WebServicesProvider
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.db.dbmodel.CityList
import com.example.assignment.db.dbserviceimpl.CityListService
import io.realm.Realm
import io.realm.RealmList


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AirMonitorAdapter
    private lateinit var realm: Realm
    var cityList = mutableListOf<SocketUpdate>()
    private lateinit var cityListService: CityListService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = MainRepository(WebServicesProvider())
        val mainRepository = MainInteractor(retrofitService)


        realm = Realm.getDefaultInstance()
        cityListService = CityListService()
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)
        viewModel.subscribeToSocketEvents()

          viewModel.airMonitorData.observe(this, Observer {
              val data = it
              var realList = RealmList<CityList>()
              for(item in data){
                  realList.add(CityList(System.currentTimeMillis(),item.city,item.aqi))
              }
              cityListService.addOrUpdateCity(realm,realList)
              var list=cityListService.getCityList(realm)
               cityList.clear()
              for(item in list){
               cityList.add(SocketUpdate(item.city!!,item.aqi!!,item.timeMillis.toString()))
              }
              setAdapter(cityList)
          })
    }
    private fun adapterOnClick(data: SocketUpdate) {

    }
    fun setAdapter(list:List<SocketUpdate>){

        adapter = AirMonitorAdapter {data ->adapterOnClick(data)  }
        binding.list.adapter = adapter
        adapter.submitList(list.toMutableList())
    }
}