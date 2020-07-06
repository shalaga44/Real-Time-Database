package com.shalaga44.mvvm

import android.util.Patterns
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
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (inputName.value == null){
            statusMessage.value = Event("Please enter user name")

        }else if (inputEmail.value == null){
            statusMessage.value = Event("Please enter user  email")

        }else if ( !Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
            statusMessage.value = Event("Please enter a valid user  email")

        }else {
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




    }

    fun clearOrDelete() {
        if (isUpdateOrDelete) {
            delete(userToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun initUpdateOrDelete(user: User) {
        userToUpdateOrDelete = user
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        saveOrUpdateButtonText.value = "Update"
        clearOrDeleteButtonText.value = "Delete"
    }

    fun insert(user: User): Job = viewModelScope.launch {
        val newRowId = repository.insert(user)
        if (newRowId > 0) {
            statusMessage.value = Event("User inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")

        }
    }

    fun update(user: User): Job = viewModelScope.launch {
        val noOfRows = repository.update(user)
        if (noOfRows > 0) {
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")

        }

    }

    fun delete(user: User): Job = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(user)
        if (noOfRowsDeleted > 0 ){
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
        statusMessage.value = Event("$noOfRowsDeleted  deleted Successfully")}
        else {
            statusMessage.value = Event("Error Occurred")

        }

    }

    fun clearAll(): Job = viewModelScope.launch {
       val noOfRowsDeleted =  repository.deleteALL()
        if (noOfRowsDeleted > 0 ){
            statusMessage.value = Event("$noOfRowsDeleted Users deleted Successfully")

        }else {
            statusMessage.value = Event("Error Occurred")

        }

    }

}