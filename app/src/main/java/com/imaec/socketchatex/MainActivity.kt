package com.imaec.socketchatex

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.imaec.socketchatex.socket.ClientThread
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Server Socket을 실행할 ServerService 시작
        startService(Intent(this, ServerService::class.java))
    }

    @SuppressLint("SetTextI18n")
    fun onClick(view: View) {
        if (view.id == R.id.button) {
            val thread = ClientThread { dto -> // 서버로부터 들어온 데이터 콜백
                // 데이터 처리
                runOnUiThread {
                    text_view.text = "${text_view.text}\n${dto.id} : ${dto.message}"
                    edit_text.setText("")
                }
            }.apply {
                setChat(ChatDTO("hs", edit_text.text.toString()))
            }
            thread.start()
        }
    }
}