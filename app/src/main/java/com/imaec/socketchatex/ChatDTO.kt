package com.imaec.socketchatex

import java.io.Serializable

data class ChatDTO(
    var id: String = "",
    var message: String = ""
) : Serializable