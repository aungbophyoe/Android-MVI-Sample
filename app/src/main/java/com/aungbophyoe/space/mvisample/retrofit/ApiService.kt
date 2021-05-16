package com.aungbophyoe.space.mvisample.retrofit

import com.aungbophyoe.space.mvisample.model.Photo
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos() : List<Photo>
}