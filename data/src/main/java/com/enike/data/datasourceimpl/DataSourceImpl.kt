package com.enike.data.datasourceimpl

import com.enike.data.api.FlunaceApi
import com.enike.data.datasource.DataSource

class DataSourceImpl(private val Apis: FlunaceApi) : DataSource {

    override suspend fun verifyPhoneNumber(phone: String) = Apis.verifyPhoneNumber(phone)



}