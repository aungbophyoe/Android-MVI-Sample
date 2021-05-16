package com.aungbophyoe.space.mvisample.di

import com.aungbophyoe.space.mvisample.repository.MainRepository
import com.aungbophyoe.space.mvisample.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        apiService : ApiService
    ): MainRepository{
        return MainRepository(apiService)
    }
}