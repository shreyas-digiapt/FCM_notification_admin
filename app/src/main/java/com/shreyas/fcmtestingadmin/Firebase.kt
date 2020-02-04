package com.shreyas.fcmtestingadmin

import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

object Firebase : LiveData<Model>() {

    override fun onActive() {
        super.onActive()

        FirebaseFirestore.getInstance().collection("users").document().get().addOnSuccessListener { document->
            try {
                if (document.exists()) {
                    value = document.get("token") as Model?
                } else {
//                    value = Model("Empty")
                }
            }catch (e:Exception) {
//                value = Model(e.message!!)
            }
        }
    }
}