package com.shalaga44.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalaga44.mvvm.db.UserRepository
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.viewModelScope
import com.shalaga44.mvvm.db.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserViewModel(val repository: UserRepository) : ViewModel() , Observable{
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    val users = repository.users

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name: String = inputName.value!!
        val email: String = inputEmail.value!!
        insert(User(0, name, email))
        inputName.value = null
        inputEmail.value = null

    }

    fun clearOrDelete() {
        clearAll()

    }

    fun insert(user: User): Job = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: User): Job = viewModelScope.launch {
        repository.update(user)
    }

    fun dalete(user: User): Job = viewModelScope.launch {
        repository.delete(user)
    }

    fun clearAll(): Job = viewModelScope.launch {
        repository.deleteALL()
    }

}