package com.fanntt.crud_berita_user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fanntt.crud_berita_user.R
import com.fanntt.crud_berita_user.model.ModelBerita
import com.fanntt.crud_berita_user.model.ModelUser

class UserAdapter(
    private val onClick : (ModelUser) -> Unit
) : ListAdapter<ModelUser,UserAdapter.UserViewHolder>(UserCallback) {
    class UserViewHolder (itemView: View, val onClick: (ModelUser) -> Unit): RecyclerView.ViewHolder(itemView)
    {

        private val txtusername : TextView = itemView.findViewById(R.id.username)
        private val txtemail : TextView = itemView.findViewById(R.id.email)
        private val txtfullname : TextView = itemView.findViewById(R.id.fullname)
        private val txttgldaftar : TextView = itemView.findViewById(R.id.tgl_daftar)

        //cek produk saat ini
        private var currentUser : ModelUser? = null

        init {
            itemView.setOnClickListener(){
                currentUser?.let {
                    onClick(it)
                }
            }
        }
        fun bind(user: ModelUser){
            currentUser = user
            //set data ke widget
            txtusername.text = user.username
            txtemail.text = user.email
            txtfullname.text = user.fullname
            txttgldaftar.text = user.tgl_daftar
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_user,parent,false)
        return UserViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
}

object UserCallback : DiffUtil.ItemCallback<ModelUser>(){
    override fun areItemsTheSame(oldItem: ModelUser, newItem: ModelUser): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelUser, newItem: ModelUser): Boolean {
        return oldItem == newItem
    }
}

