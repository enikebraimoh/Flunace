package com.enike.domain.model

import com.google.gson.annotations.SerializedName

data class VerifyPhoneNumberResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Any
)
