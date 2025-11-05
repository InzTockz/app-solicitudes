package com.battilana.app_solicitudes.data.di

import android.content.Context
import com.battilana.app_solicitudes.data.local.UserPreferences
import com.battilana.app_solicitudes.data.remote.ApiService
import com.battilana.app_solicitudes.data.remote.AuthInterceptor
import com.battilana.app_solicitudes.data.repository.AuthRepositoryImpl
import com.battilana.app_solicitudes.data.repository.SapRepositoryImpl
import com.battilana.app_solicitudes.data.repository.UsuarioRepositoryImpl
import com.battilana.app_solicitudes.data.repository.UsuarioSapRepositoryImpl
import com.battilana.app_solicitudes.domain.repository.AuthRepository
import com.battilana.app_solicitudes.domain.repository.SapRepository
import com.battilana.app_solicitudes.domain.repository.UserRepository
import com.battilana.app_solicitudes.domain.repository.UsuarioSapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Auth Repository
    @Provides
    fun provideAuthRepository(api: ApiService): AuthRepository{
        return AuthRepositoryImpl(api)
    }

    //User Repository
    @Provides
    fun provideUserRepository(api: ApiService): UserRepository{
        return UsuarioRepositoryImpl(api)
    }

    @Provides
    fun provideUsuarioSapRepository(api: ApiService) : UsuarioSapRepository{
        return UsuarioSapRepositoryImpl(api)
    }

    @Provides
    fun provideSapRepository(api: ApiService) : SapRepository{
        return SapRepositoryImpl(api)
    }

    //ApiService: interfaz con los endpoints
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    //Configuracion base Retrofit
    @Provides
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            //.baseUrl("http://10.0.2.2:8080/api/v1/")
            .baseUrl("http://192.168.1.139:8080/api/v1/")
            .client(client)
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

    @Provides
    fun provideAuthInterceptor(userPreferences: UserPreferences): AuthInterceptor{
        return AuthInterceptor(userPreferences)
    }

    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }
}