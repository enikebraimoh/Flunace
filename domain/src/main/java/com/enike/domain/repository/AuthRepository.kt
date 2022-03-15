package com.enike.domain.repository

import com.enike.domain.model.VerifyPhoneNumberResponse

interface AuthRepository {

    suspend fun verifyPhoneNumber(phoneNumber: String): VerifyPhoneNumberResponse

}