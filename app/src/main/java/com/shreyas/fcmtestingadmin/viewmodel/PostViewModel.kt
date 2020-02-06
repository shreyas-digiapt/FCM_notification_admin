package com.shreyas.fcmtestingadmin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.shreyas.fcmtestingadmin.Noti.NotiResponse
import com.shreyas.fcmtestingadmin.Noti.NotifiactionModel
import com.shreyas.fcmtestingadmin.repositary.PostRepo

class PostViewModel(application:Application) : AndroidViewModel(application) {


    private val postRepo =
        PostRepo(application)

    fun postNoti(model: NotifiactionModel): NotiResponse? {
        return postRepo.postNoti(model)
    }
}

