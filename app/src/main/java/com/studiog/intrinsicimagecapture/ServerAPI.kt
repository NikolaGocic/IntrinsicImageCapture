package com.studiog.intrinsicimagecapture

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ServerAPI {

    @Multipart
    @POST("/process")
    fun processImages(@Part F: MultipartBody.Part, @Part D: MultipartBody.Part, @Part("name") name: RequestBody): Call<ResponseBody?>?

}