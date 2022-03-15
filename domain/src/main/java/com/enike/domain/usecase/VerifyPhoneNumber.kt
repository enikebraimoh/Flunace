package com.enike.domain.usecase

import com.enike.domain.repository.AuthRepository


class VerifyPhoneNumber(val authRepo : AuthRepository) {
    suspend operator fun invoke( phoneNumber: String) = authRepo.verifyPhoneNumber(phoneNumber = phoneNumber)
}