package com.imaec.socketchatex.socket

import com.imaec.socketchatex.ChatDTO
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

class ClientThread(private val callback: (ChatDTO) -> Unit) : Thread() {

    private var dto = ChatDTO("", "EMPTY")

    override fun run() {
        super.run()

        // Server IP 주소, 여기서는 WIFI IP 주소
        val host = "localhost"
//        val host = "192.168.35.157"
        val port = 5001

        try {
            val socket = Socket(host, port)
            val outputStream = ObjectOutputStream(socket.getOutputStream()) // 서버로 내보낼 데이터
            outputStream.writeObject(dto)
            outputStream.flush()

            val inputStream = ObjectInputStream(socket.getInputStream()) // 서버로부터 들어오는 데이터 처리
            val input = inputStream.readObject() as ChatDTO

            callback(input) // 들어온 데이터 콜백
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setChat(dto: ChatDTO) {
        this.dto = dto
    }
}