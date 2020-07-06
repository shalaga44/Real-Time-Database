package com.shalaga44.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shalaga44.mvvm.databinding.ListItemsBinding
import com.shalaga44.mvvm.db.User

class RecyclerviewAdapter (val usersList: List<User>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemsBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.list_items,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

}
class MyViewHolder(val binding: ListItemsBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(user: User){
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email
    }
}