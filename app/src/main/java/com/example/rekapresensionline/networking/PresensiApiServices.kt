package com.example.rekapresensionline.networking

import com.example.rekapresensionline.model.ForgotPasswordResponse
import com.example.rekapresensionline.model.HistoriResponse
import com.example.rekapresensionline.model.LoginResponse
import com.example.rekapresensionline.model.PresensiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PresensiApiServices {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/login")
    fun loginRequest(@Body body: String): Call<LoginResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("auth/password/forgot")
    fun forgotPasswordRequest(@Body body: String): Call<ForgotPasswordResponse>

    @Multipart
    @Headers("Accept: application/json")
    @POST("attendance")
    fun attend(@Header("Authorization") token: String,
               @PartMap params: HashMap<String, RequestBody>,
               @Part photo: MultipartBody.Part
    ): Call<PresensiResponse>

    @Headers("Accept: application/json")
    @GET("attendance/history")
    fun getHistoryAttendance(@Header("Authorization") token: String,
                             @Query("from") fromDate: String,
                             @Query("to") toDate: String
    ): Call<HistoriResponse>
}
