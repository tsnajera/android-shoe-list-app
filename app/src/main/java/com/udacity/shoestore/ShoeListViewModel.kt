package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _shoeDetailComplete = MutableLiveData<Boolean>()
    val shoeDetailComplete: LiveData<Boolean>
        get() = _shoeDetailComplete

    init {
        clearShoeList()
        clearShoe()
    }

    fun clearShoe(){
        _shoe.value = Shoe("", 0.0, "", "")
    }

    fun shoeDetailComplete(){
        _shoeDetailComplete.value = true
    }

    fun shoeDetailIncomplete(){
        _shoeDetailComplete.value = false
    }

    fun addShoeToShoeList() {
        _shoeList.value?.add(_shoe.value!!)
        shoeDetailComplete()
    }

    fun clearShoeList(){
        _shoeList.value = arrayListOf()
        shoeDetailIncomplete()
    }

    override fun onCleared() {
        super.onCleared()
    }

}