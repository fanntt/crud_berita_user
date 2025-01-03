package com.fanntt.crud_berita_user.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fanntt.crud_berita_user.DetailBerita
import com.fanntt.crud_berita_user.R
import com.fanntt.crud_berita_user.adapter.BeritaAdapter
import com.fanntt.crud_berita_user.adapter.UserAdapter
import com.fanntt.crud_berita_user.model.ModelBerita
import com.fanntt.crud_berita_user.model.ModelUser
import com.fanntt.crud_berita_user.response.RegisterResponse
import com.fanntt.crud_berita_user.response.ResponseBerita
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback

class ListUser : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private lateinit var callUser : Call<RegisterResponse>
    private lateinit var userAdapter: UserAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_user)

        rvUser = findViewById(R.id.rv_user)
        swipeRefreshLayout = findViewById(R.id.main)
        userAdapter= UserAdapter { modelUser : ModelUser -> userOnclick(modelUser)  }
        rvUser.adapter = userAdapter
        rvUser.layoutManager = LinearLayoutManager(
            applicationContext , LinearLayoutManager.VERTICAL,false
        )
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()
    }


    private fun userOnclick(modelUser: ModelUser) {
        val intent = Intent(this, DetailUser::class.java)
        intent.putExtra("username",modelUser.username)
        intent.putExtra("email",modelUser.email)
        intent.putExtra("fullname",modelUser.fullname)
        intent.putExtra("tgl_daftar",modelUser.tgl_daftar)
        startActivity(intent)
    }
    private fun getData() {
        // proses untuk dapatkan respons
        swipeRefreshLayout.isRefreshing = true
        callUser = ApiClient.retrofit.getAllUsers()
        callUser.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: retrofit2.Response<RegisterResponse>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    userAdapter.submitList(response.body()?.data)
                    userAdapter.notifyDataSetChanged()
                }

            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(
                    applicationContext,
                    t.localizedMessage, Toast.LENGTH_SHORT
                ).show()

            }
        })

    }
}