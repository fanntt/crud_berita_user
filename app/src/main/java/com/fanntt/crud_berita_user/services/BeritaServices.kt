package com.fanntt.crud_berita_user.services

import com.fanntt.crud_berita_user.model.ModelLogin
import com.fanntt.crud_berita_user.response.RegisterResponse
import com.fanntt.crud_berita_user.response.ResponseBerita
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface BeritaServices {

    @GET("getBerita.php")
    fun getAllBerita(): Call<ResponseBerita>

    @GET("listmahasiswa.php")
    fun getAllUsers(): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(
        @Field("username") username : String,
        @Field("password") password : String,
        @Field("fullname") fullname : String,
        @Field("email") email : String,
    ):Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("username") username : String,
        @Field("password") password : String
    ):Call<ModelLogin>

}