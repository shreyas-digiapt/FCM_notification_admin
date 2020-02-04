package com.shreyas.fcmtestingadmin.Noti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("message_id")
    @Expose
    var messageId:String
)