package com.battilana.app_solicitudes.data.remote

import com.battilana.app_solicitudes.data.local.UserPreferences
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val userPreferences: UserPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //OBtenemos el token guardado del DataStore (de forma bloqueante)
        val token = runBlocking {
            userPreferences.userSession.firstOrNull()?.token
        }

        val requestBuilder = chain.request().newBuilder()

        token.let {
            requestBuilder.addHeader("Authorization", "$it")
        }

        return chain.proceed(requestBuilder.build())
    }


}