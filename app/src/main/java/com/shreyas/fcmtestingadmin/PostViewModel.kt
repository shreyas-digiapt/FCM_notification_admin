package com.shreyas.fcmtestingadmin

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.*
import com.shreyas.fcmtestingadmin.Noti.NotiResponse
import com.shreyas.fcmtestingadmin.Noti.NotifiactionModel

class PostViewModel(application:Application) : AndroidViewModel(application) {


    private val postRepo = PostRepo(application)

    fun postNoti(model: NotifiactionModel): NotiResponse? {
        return postRepo.postNoti(model)
    }
}

