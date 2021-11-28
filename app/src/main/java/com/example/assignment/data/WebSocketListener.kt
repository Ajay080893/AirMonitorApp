package com.example.assignment.data

import com.example.assignment.SocketUpdate
import com.example.assignment.data.WebServicesProvider.Companion.NORMAL_CLOSURE_STATUS
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener



class WebSocketListener : WebSocketListener() {

    val socketEventChannel: Channel<List<SocketUpdate>> = Channel(10)

    override fun onOpen(webSocket: WebSocket, response: Response) {

    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        GlobalScope.launch {
            val gson = Gson()
            val objectList = gson.fromJson(text, Array<SocketUpdate>::class.java).asList()
            socketEventChannel.send(objectList)
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {

        webSocket.close(NORMAL_CLOSURE_STATUS, null)
        socketEventChannel.close()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        socketEventChannel.close()
    }

}




