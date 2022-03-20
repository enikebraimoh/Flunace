package com.enike.domain.usecase

import com.enike.domain.model.VerifyPhoneNumberResponse
import com.enike.domain.repository.AuthRepository
import com.enike.domain.util.DataState
import com.enike.domain.util.convertErrorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class VerifyPhoneNumber(val authRepo: AuthRepository) {
    operator fun invoke(phoneNumber: String): Flow<DataState<VerifyPhoneNumberResponse>> =
        flow {
            try {
                val response = authRepo.verifyPhoneNumber(phoneNumber = phoneNumber)
                emit(DataState.Success(response))
            } catch (e: Exception) {
                when (e) {
                    is IOException -> emit(DataState.Error(e))
                    is HttpException -> {
                        val status = e.code()
                        val res = convertErrorBody(e)
                        emit(DataState.GenericError(status, res))
                    }
                }

            }

        }
}