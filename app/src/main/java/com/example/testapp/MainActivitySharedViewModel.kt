package com.example.testapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.enum.Destinations

class MainActivitySharedViewModel : ViewModel() {

    private val _link : MutableLiveData<String> = MutableLiveData("")
    val link : LiveData<String> get() = _link

    private val _changeSplash : MutableLiveData<String> = MutableLiveData(Destinations.SPLASH.name)
    val changeSplash : LiveData<String> get() = _changeSplash

    fun setChangeSplash(destinations: Destinations){
        _changeSplash.postValue(destinations.name)
    }

    fun setLink(string: String){
        _link.postValue(string)
    }

}