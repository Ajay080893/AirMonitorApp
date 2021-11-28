package com.example.assignment

import com.example.assignment.data.WebServicesProvider
import kotlinx.coroutines.channels.Channel



class MainRepository constructor(private val webServicesProvider: WebServicesProvider) {

    fun startSocket(): Channel<List<SocketUpdate>> =
        webServicesProvider.startSocket()

    fun closeSocket() {
        webServicesProvider.stopSocket()
    }
}
class MainInteractor constructor(private val repository: MainRepository) {

    fun stopSocket() {
        repository.closeSocket()
    }
    fun startSocket(): Channel<List<SocketUpdate>> = repository.startSocket()

}