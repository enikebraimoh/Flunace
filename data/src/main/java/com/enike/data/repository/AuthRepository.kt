package com.enike.data.repository

import com.enike.domain.model.VerifyPhoneNumberResponse
import com.enike.domain.repository.AuthRepository

class AuthRepository : AuthRepository {

    override suspend fun verifyPhoneNumber(phoneNumber: String): VerifyPhoneNumberResponse {



    }

}