package com.example.assignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.MainInteractor
import com.example.assignment.SocketUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch


class MainViewModel constructor(
        private val interactor: MainInteractor
):
        ViewModel() {

    private val _airMonitorData = MutableLiveData<List<SocketUpdate>>()
    val airMonitorData: LiveData<List<SocketUpdate>>
        get() = _airMonitorData


        fun subscribeToSocketEvents() {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    interactor.startSocket().consumeEach {
                        _airMonitorData.postValue(it)

                    }
                } catch (ex: java.lang.Exception) {
                    onSocketError(ex)
                }
            }
        }

        private fun onSocketError(ex: Throwable) {
            println("Error occurred : ${ex.message}")
        }

        override fun onCleared() {
            interactor.stopSocket()
            super.onCleared()
        }

    }

