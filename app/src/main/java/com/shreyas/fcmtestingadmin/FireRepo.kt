package com.shreyas.fcmtestingadmin

import android.util.Log
import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object FireRepo {

    var job: CompletableJob? = null

    fun getUsers():LiveData<MutableList<Model>>{
        val mutableList = MutableLiveData<MutableList<Model>>()

        FirebaseFirestore.getInstance().collection("users").get().addOnSuccessListener { documentSnapshot ->
            val listData = mutableListOf<Model>()
            Log.d("test5", "sads:test ${documentSnapshot.size()}")
                for (document in documentSnapshot) {
                    val token = document.getString("token")
                    val name = document.id
                    val model = Model(name, token!!)
                    listData.add(model)
                }
            mutableList.value = listData
        }
        return mutableList
    }

//    fun getUsers(): LiveData<MutableList<Model>> {
//        Log.d("test5", "sads: ")
//        job = Job()
//
//        return object : LiveData<MutableList<Model>>() {
//            override fun onActive() {
//                super.onActive()
//                val mutableList = MutableLiveData<MutableList<Model>>()
//                Log.d("test5", "sads: ${mutableList.value}")
//                job?.let { theJob ->
//                    CoroutineScope(IO + theJob).launch {
////                        val firebase =
//                        withContext(Main) {
//                            FirebaseFirestore.getInstance().collection("users").get()
//                                .addOnSuccessListener { refs ->
//                                    val listData = mutableListOf<Model>()
//                                    Log.d("test5", "sads: ${refs.size()}")
//                                    for (ref in refs) {
//                                        val token = ref.getString("token")
//                                        val name = ref.id
//                                        Log.d("test5", "sads: ${name}")
//                                        val model = Model(name, token!!)
//                                        listData.add(model)
//                                    }
//                                    Log.d("test5", "sads: ${listData.size}")
//                                    mutableList.value = listData
//                                }
//                        }
//                    }
//                }
//            }
//        }
//    }

    fun cancelJobs() {
        job?.cancel()
    }

}