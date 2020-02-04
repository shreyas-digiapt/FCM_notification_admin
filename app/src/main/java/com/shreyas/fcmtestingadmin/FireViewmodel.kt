package com.shreyas.fcmtestingadmin

import android.util.Log
import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class FireViewmodel : ViewModel() {

    private val _users_live_data = MutableLiveData<List<Model>>()
    val _users : LiveData<List<Model>>
        get() = _users_live_data

    fun fetchUsers(): LiveData<MutableList<Model>> {
        val mutableList  = MutableLiveData<MutableList<Model>>()

        FireRepo.getUsers().observeForever {
                mutableList.value = it
            _users_live_data.value = it
            }
        Log.d("test5", "sads:viremodel ${mutableList.value?.size}")
        return mutableList
    }

//    val getModel:LiveData<MutableList<Model>> = Transformations
//        .switchMap(_model) { model->
//            Log.d("test5", "sads: ${model}")
//            FireRepo.getUsers()
//        }

    fun cancelJob() {
        FireRepo.cancelJobs()
    }
}