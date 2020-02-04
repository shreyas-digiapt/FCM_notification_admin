package com.shreyas.fcmtestingadmin.Noti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotiNotifaction(
    @SerializedName("title")
    @Expose
    private var title:String,

    @SerializedName("body")
    @Expose
    private var body:String
)