package com.shreyas.fcmtestingadmin.Noti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotifiactionModel(
    @SerializedName("to")
    @Expose
    private var to: String,

    @SerializedName("collapse_key")
    @Expose
    private var collapse_key: String,

    @SerializedName("notification")
    @Expose
    private var notification: NotiNotifaction,

    @SerializedName("data")
    @Expose
    private var data: NotiData
)