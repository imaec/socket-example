package com.imaec.socketchatex

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.imaec.socketchatex.socket.ServerThread

class ServerService : Service() {

    override fun onCreate() {
        super.onCreate()

        // ServerSocket 실행
        val thread = ServerThread()
        thread.start()
    }

    override fun onBind(intent: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }
}