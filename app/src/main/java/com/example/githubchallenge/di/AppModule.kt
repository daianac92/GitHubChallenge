package com.example.githubchallenge.di

import com.example.githubchallenge.BuildConfig
import com.example.githubchallenge.service.repository.GitHubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class AppModule {

    @Provides
    @Singleton
    fun initRetrofit(
        httpBuilder: OkHttpClient.Builder,
        gsonProvider: GsonConverterFactory,
    ): Retrofit {
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(logging)
        }
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(httpBuilder.build())
            .addConverterFactory(gsonProvider)
            .build()
    }

    @Provides
    fun httpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    fun gsonProvider(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun initService(retrofit: Retrofit): GitHubService = retrofit.create(GitHubService::class.java)
}
