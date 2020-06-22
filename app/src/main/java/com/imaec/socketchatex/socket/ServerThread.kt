package com.imaec.socketchatex.socket

import com.imaec.socketchatex.ChatDTO
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

class ServerThread : Thread() {

    override fun run() {
        super.run()

        val port = 5001
        try {
            // ServerSocket 실행
            val server = ServerSocket(port)
            while (true) {
                val socket: Socket = server.accept() // server 대기상태. 클라이언트 접속 시 소켓 객체 리턴

                val inputStream = ObjectInputStream(socket.getInputStream()) // 클라이언트로부터 들어오는 데이터 처리
                val input = (inputStream.readObject() as ChatDTO).apply { // 들어온 데이터
                    message = "$message from Server" // 들어온 데이터 가공, ServerSocket을 거쳐갔다는 표시를 위해 삽입
                }

                val outputStream = ObjectOutputStream(socket.getOutputStream()) // 클라이언트로 내보낼 데이터
                outputStream.writeObject(input)
                outputStream.flush()

                socket.close() // 연결을 유지할 필요 없으면 끊어줌.
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}