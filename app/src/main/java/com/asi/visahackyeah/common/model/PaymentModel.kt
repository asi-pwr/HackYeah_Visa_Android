package com.asi.visahackyeah.common.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaymentModel
constructor(@SerializedName("name") @Expose var name: String?,
            @SerializedName("title") @Expose var title: String?,
            @SerializedName("value") @Expose var value: Int?,
            @SerializedName("date") @Expose var date: Long)