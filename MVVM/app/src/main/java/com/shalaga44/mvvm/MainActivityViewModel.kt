package com.shalaga44.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shalaga44.mvvm.db.UserRepository
import androidx.databinding.Bindable
class MainActivityViewModel(val repository: UserRepository) : ViewModel() {
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

    fun saveOrUpdate(){

    }
    fun clearOrDelete(){

    }
}