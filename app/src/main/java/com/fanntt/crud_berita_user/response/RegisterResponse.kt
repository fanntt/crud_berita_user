package com.fanntt.crud_berita_user.response

import com.fanntt.crud_berita_user.model.ModelUser


data class RegisterResponse(
    val succes  : Boolean,
    val message: String,
    val data : List<ModelUser>
)
