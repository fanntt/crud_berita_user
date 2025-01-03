package com.fanntt.crud_berita_user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fanntt.crud_berita_user.adapter.BeritaAdapter
import com.fanntt.crud_berita_user.model.ModelBerita
import com.fanntt.crud_berita_user.response.ResponseBerita
import com.fanntt.crud_berita_user.screen.LoginScreenActivity
import com.fanntt.crud_berita_user.screen.RegisterScreenActivity
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call : Call<ResponseBerita>
    private lateinit var beritaAdapter: BeritaAdapter
    private lateinit var buttonListUser : Button
    private lateinit var buttonLogin : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        swipeRefreshLayout=findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.rv_berita)
        buttonListUser = findViewById(R.id.btnTambahUser)
        buttonLogin = findViewById(R.id.btnlogin)

        beritaAdapter= BeritaAdapter { modelBerita : ModelBerita -> beritaOnClick(modelBerita)  }
        recyclerView.adapter = beritaAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext , LinearLayoutManager.VERTICAL,false
        )
        // swipe refresh
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()

        buttonListUser.setOnClickListener(){
            val intent = Intent(this@MainActivity,
                RegisterScreenActivity::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener(){
            val intent = Intent(this@MainActivity,
                LoginScreenActivity::class.java)
            startActivity(intent)
        }


        }
    private fun beritaOnClick(modelProduk: ModelBerita) {
        val intent = Intent(this, DetailBerita::class.java)
        intent.putExtra("gambar_berita",modelProduk.gambar_berita)
        intent.putExtra("judul",modelProduk.judul)
        intent.putExtra("isi_berita",modelProduk.isi_berita)
        intent.putExtra("tgl_berita",modelProduk.tgl_berita)
        startActivity(intent)
    }
    private fun getData() {
        // proses untuk dapatkan respons
        swipeRefreshLayout.isRefreshing = true
        call = ApiClient.retrofit.getAllBerita()
        call.enqueue(object : Callback<ResponseBerita> {
            override fun onResponse(
                call: Call<ResponseBerita>,
                response: retrofit2.Response<ResponseBerita>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    beritaAdapter.submitList(response.body()?.data)
                    beritaAdapter.notifyDataSetChanged()
                }

            }
            override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(
                    applicationContext,
                    t.localizedMessage, Toast.LENGTH_SHORT
                ).show()

            }
        })

    }

}