package com.fanntt.crud_berita_user.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fanntt.crud_berita_user.DetailBerita
import com.fanntt.crud_berita_user.MainActivity
import com.fanntt.crud_berita_user.R
import com.fanntt.crud_berita_user.model.ModelLogin
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_login_screen)

        val etusername = findViewById<TextView>(R.id.etusername)
        val etpassword = findViewById<TextView>(R.id.etpassword)
        val txttoregister = findViewById<TextView>(R.id.txttoregister)
        val btnlogin = findViewById<TextView>(R.id.btnLogin)

        txttoregister.setOnClickListener() {
            val intent = Intent(this@LoginScreenActivity, RegisterScreenActivity::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener() {
            val username = etusername.text.toString()
            val password = etpassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "username atau password tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                ApiClient.retrofit.loginUser(username, password).enqueue(object :
                    Callback<ModelLogin> {

                    override fun onResponse(
                        call: Call<ModelLogin>,
                        response: Response<ModelLogin>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse != null && response.isSuccessful) {
//                                Login Berhasil
                                Toast.makeText(
                                    this@LoginScreenActivity, "Login Berhasil", Toast.LENGTH_SHORT
                                ).show()
                                val toMainActivity =
                                    Intent(this@LoginScreenActivity, MainActivity::class.java)
                                startActivity(toMainActivity)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginScreenActivity, "Login Gagal", Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            val errorMessage =
                                response.errorBody()?.toString() ?: "Terjadi Kesalahan"
                            Log.e("Login Error", errorMessage)
                            Toast.makeText(this@LoginScreenActivity, errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ModelLogin>, t: Throwable) {
                        Toast.makeText(
                            this@LoginScreenActivity, t.message, Toast.LENGTH_SHORT
                        ).show()
                    }

                })

            }


            }
        }
}