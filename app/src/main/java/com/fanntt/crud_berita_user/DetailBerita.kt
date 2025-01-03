package com.fanntt.crud_berita_user

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailBerita : AppCompatActivity() {

    private lateinit var txtJudul : TextView
    private lateinit var txtIsiBerita : TextView
    private lateinit var txtTanggalBerita : TextView
    private lateinit var imgBerita : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_berita)

        txtJudul=findViewById(R.id.txtJudul)
        txtIsiBerita=findViewById(R.id.txtIsiBerita)
        txtTanggalBerita=findViewById(R.id.txtTanggalBerita)

        imgBerita=findViewById(R.id.imgBerita)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //getData
        val gambar = intent.getStringExtra("gambar_berita")
        val judul = intent.getStringExtra("judul")
        val isi = intent.getStringExtra("isi_berita")
        val tanggal = intent.getStringExtra("tgl_berita")


        Glide.with(this).load("http://192.168.16.234/beritaDb/gambar_berita/"+ gambar).centerCrop().into(imgBerita)
        txtJudul.text=judul
        txtTanggalBerita.text=tanggal
        txtIsiBerita.text=isi


    }
}