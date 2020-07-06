package com.shalaga44.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalaga44.mvvm.db.UserRepository
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.shalaga44.mvvm.db.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserViewModel(val repository: UserRepository) : ViewModel(), Observable {
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    val users = repository.users
    var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: User

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get()  = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!

            update(userToUpdateOrDelete)
        } else {
            val name: String = inputName.value!!
            val email: String = inputEmail.value!!
            insert(User(0, name, email))
            inputName.value = null
            inputEmail.value = null
        }

    }

    fun clearOrDelete() {
        if (isUpdateOrDelete) {
            delete(userToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun initUpdateOrDelete(user: User) {
        userToUpdateOrDelete=user
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        saveOrUpdateButtonText.value = "Update"
        clearOrDeleteButtonText.value = "Delete"
    }

    fun insert(user: User): Job = viewModelScope.launch {
        repository.insert(user)
        statusMessage.value = Event("User inserted Successfully")
    }

    fun update(user: User): Job = viewModelScope.launch {
        repository.update(user)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
        statusMessage.value = Event("User Updated Successfully")

    }

    fun delete(user: User): Job = viewModelScope.launch {
        repository.delete(user)
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
        statusMessage.value = Event("User deleted Successfully")

    }

    fun clearAll(): Job = viewModelScope.launch {
        repository.deleteALL()
        statusMessage.value = Event("All Users deleted Successfully")

    }

}