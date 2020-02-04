package com.shreyas.fcmtestingadmin.Noti

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotiResponse(
    @SerializedName("multicast_id")
    @Expose
    var multicastId:Long,

    @SerializedName("success")
    @Expose
    var success:Long,

    @SerializedName("failure")
    @Expose
    var failure:Long,

    @SerializedName("canonical_ids")
    @Expose
    var canonicalIds:Long,

    @SerializedName("results")
    @Expose
    var results:List<Result>? = null
)