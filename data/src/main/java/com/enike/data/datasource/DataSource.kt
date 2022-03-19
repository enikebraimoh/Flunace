package com.enike.data.datasource

import com.enike.domain.model.VerifyPhoneNumberResponse

interface DataSource {

    suspend fun verifyPhoneNumber(phone : String) : VerifyPhoneNumberResponse



}