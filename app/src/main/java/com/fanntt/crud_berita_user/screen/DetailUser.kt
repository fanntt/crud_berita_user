package com.fanntt.crud_berita_user.screen

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.fanntt.crud_berita_user.R

class DetailUser : AppCompatActivity() {

    private lateinit var txtDetailUsername : TextView
    private lateinit var txtDetailEmail : TextView
    private lateinit var txtDetailFullname : TextView
    private lateinit var txtDetailTglDaftar : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_user)

        txtDetailUsername=findViewById(R.id.txtDusername)
        txtDetailEmail=findViewById(R.id.txtDemail)
        txtDetailFullname=findViewById(R.id.txtDfullname)
        txtDetailTglDaftar=findViewById(R.id.txtDtgl_daftar)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //getData
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val fullname = intent.getStringExtra("fullname")
        val tanggalDaftar = intent.getStringExtra("tgl_daftar")



        txtDetailUsername.text=username
        txtDetailEmail.text=email
        txtDetailFullname.text=fullname
        txtDetailTglDaftar.text=tanggalDaftar

    }
}