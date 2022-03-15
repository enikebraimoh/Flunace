package com.enike.domain.repository

import com.enike.domain.model.VerifyPhoneNumberResponse
import com.enike.domain.usecase.VerifyPhoneNumber

interface AuthRepository {

    suspend fun verifyPhoneNumber(phoneNumber: VerifyPhoneNumber) : VerifyPhoneNumberResponse

}