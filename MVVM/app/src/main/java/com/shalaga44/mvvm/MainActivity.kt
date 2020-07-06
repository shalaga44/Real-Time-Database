package com.shalaga44.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shalaga44.mvvm.databinding.ActivityMainBinding
import com.shalaga44.mvvm.db.User
import com.shalaga44.mvvm.db.UserDAO
import com.shalaga44.mvvm.db.UserDatabase
import com.shalaga44.mvvm.db.UserRepository
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val TAG  = "BlaBlaBla"
    private lateinit var _binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private var usersList : List<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao: UserDAO = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        _binding.viewModel = userViewModel
        _binding.lifecycleOwner = this
       initRecyclerView()

    }
    private fun initRecyclerView (){
        _binding.userRecyclerview.layoutManager = LinearLayoutManager(this)
        displayUsersList()

    }
    private fun displayUsersList(){
        userViewModel.users.observe(this, Observer {
           Log.d(TAG, it.toString())
//            usersList = it
//            _binding.userRecyclerview.adapter?.notifyDataSetChanged()
            _binding.userRecyclerview.adapter = RecyclerviewAdapter(it, {selectedItem:User -> listItemClickListener(selectedItem)})

        })
    }
    private fun listItemClickListener(user: User){
        Toast.makeText(this, "selected user is ${user.name}", Toast.LENGTH_SHORT).show()
        userViewModel.initUpdateOrDelete(user)
    }
}