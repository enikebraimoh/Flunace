package com.enike.data.api

import com.enike.domain.model.VerifyPhoneNumberResponse
import retrofit2.http.GET

interface FlunaceApi {

    @GET
    suspend fun verifyPhoneNumber( phoneNumber : String ): VerifyPhoneNumberResponse

}