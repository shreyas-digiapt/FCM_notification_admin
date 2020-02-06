package com.shreyas.fcmtestingadmin.repositary

import android.app.Application
import android.util.Log
import com.shreyas.fcmtestingadmin.Noti.NotiResponse
import com.shreyas.fcmtestingadmin.Noti.NotifiactionModel
import com.shreyas.fcmtestingadmin.service.RetrofirBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepo(application: Application) {


    fun postNoti(model: NotifiactionModel): NotiResponse? {

        var res:NotiResponse? = null

       RetrofirBuilder.apiService.sendNoti(model).enqueue(object : Callback<NotiResponse>{
           override fun onFailure(call: Call<NotiResponse>, t: Throwable) {
               Log.d("pop9","message ${t.message}")
           }

           override fun onResponse(call: Call<NotiResponse>, response: Response<NotiResponse>) {
               Log.d("pop9","succss ${response.isSuccessful()}")
               Log.d("pop9","ody ${response.body()}")
               res = response.body() as NotiResponse

               Log.d("pop99","NotiResponse ${res!!.success}")
           }

       })
        return res
    }
}