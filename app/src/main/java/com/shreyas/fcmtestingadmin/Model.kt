package com.shreyas.fcmtestingadmin

import com.google.gson.annotations.SerializedName

data class Model(
    var name:String,
    @SerializedName("token")
    var token:String
)