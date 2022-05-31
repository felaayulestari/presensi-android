package com.example.rekapresensionline.networking

import com.example.rekapresensionline.views.networking.RetrofitClient

object ApiServices {
    fun getPresensiServices(): PresensiApiServices{
        return RetrofitClient
            .getClient()
            .create(PresensiApiServices::class.java)
    }
}