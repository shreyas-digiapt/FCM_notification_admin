package com.shreyas.fcmtestingadmin.Noti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotiData(

    @SerializedName("Text")
    @Expose
    private var text:String,

    @SerializedName("Command")
    @Expose
    private var command:String

)