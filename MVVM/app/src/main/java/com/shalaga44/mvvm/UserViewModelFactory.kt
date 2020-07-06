package com.shalaga44.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shalaga44.mvvm.db.UserRepository
import java.lang.IllegalArgumentException

class UserViewModelFactory (val repository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(UserViewModel::class.java)){
        return  UserViewModel(repository ) as T
    }
        throw  IllegalArgumentException("Unknown View Model class")
    }
}