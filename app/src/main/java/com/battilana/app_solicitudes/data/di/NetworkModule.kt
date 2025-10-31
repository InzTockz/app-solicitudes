package com.battilana.app_solicitudes.data.di

import android.content.Context
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.remote.ApiService
import com.battilana.app_solicitudes.data.repository.AuthRepositoryImpl
import com.battilana.app_solicitudes.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Repository: la capa que usa el API
    @Provides
    fun provideAuthRepository(api: ApiService): AuthRepository{
        return AuthRepositoryImpl(api)
    }

    //ApiService: interfaz con los endpoints
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    //Retrofit configuraod con el convertidor JSON moderno
    @Provides
    fun provideRetrofit(json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.139:8080/api/v1/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    //Parcear el JSON de Kotlin.Serialization
    @Provides
//    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context, json: Json): UserPreferences{
        return UserPreferences(context, json)
    }
}