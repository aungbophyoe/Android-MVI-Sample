package com.aungbophyoe.space.mvisample.repository

import com.aungbophyoe.space.mvisample.model.Photo
import com.aungbophyoe.space.mvisample.retrofit.ApiService
import com.aungbophyoe.space.mvisample.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.Exception

class MainRepository
constructor(
    private val apiService: ApiService
){
    suspend fun getPhotos() : Flow<DataState<List<Photo>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val data = apiService.getPhotos()
            emit(DataState.Success(data))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}