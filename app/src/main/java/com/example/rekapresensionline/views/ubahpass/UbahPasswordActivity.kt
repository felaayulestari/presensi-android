package com.example.rekapresensionline.views.ubahpass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import com.example.rekapresensionline.R
import com.example.rekapresensionline.databinding.ActivityUbahPasswordBinding
import com.example.rekapresensionline.dialog.MyDialog
import com.example.rekapresensionline.hawkstorage.HawkStorage
import com.example.rekapresensionline.model.LoginResponse
import com.example.rekapresensionline.model.UbahPasswordResponse
import com.example.rekapresensionline.networking.ApiServices
import com.example.rekapresensionline.views.networking.RetrofitClient
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class UbahPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUbahPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun onClick() {
        binding.tbChangePassword.setNavigationOnClickListener {
            finish()
        }

        binding.btnChangePassword.setOnClickListener {
            val oldPass = binding.etOldPassword.text.toString()
            val newPass = binding.etNewPassword.text.toString()
            val confirmNewPass = binding.etConfirmNewPassword.text.toString()
            if (checkValidation(oldPass, newPass, confirmNewPass)) {
                changePassToServer(oldPass, newPass, confirmNewPass)
            }
        }
    }

    private fun changePassToServer(oldPass: String, newPass: String, confirmNewPass: String) {
        val token = HawkStorage.instance(this).getToken()
        val changePassRequest = UbahPasswordRequest(
            passwordOld = oldPass,
            password = newPass,
            passwordConfirmation = confirmNewPass
        )
        val changePassRequestString = Gson().toJson(changePassRequest)
        MyDialog.showProgressDialog(this)
        ApiServices.getPresensiServices()
            .changePassword("Bearer $token", changePassRequestString)
            .enqueue(object : Callback<UbahPasswordResponse> {
                override fun onResponse(
                    call: Call<UbahPasswordResponse>,
                    response: Response<UbahPasswordResponse>
                ) {
                    MyDialog.hideDialog()
                    if (response.isSuccessful){
                        MyDialog.dynamicDialog(
                            this@UbahPasswordActivity,
                            getString(R.string.success),
                            getString(R.string.your_password_has_been_update)
                        )
                        Handler(Looper.getMainLooper()).postDelayed({
                            MyDialog.hideDialog()
                            finish()
                        },2000)
                    }else{
                        val errorConverter: Converter<ResponseBody, UbahPasswordResponse> =
                            RetrofitClient
                                .getClient()
                                .responseBodyConverter(
                                    LoginResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                        var errorResponse: UbahPasswordResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
                                MyDialog.dynamicDialog(this@UbahPasswordActivity, getString(R.string.failed), errorResponse?.message.toString())
                            }
                        }catch (e: IOException){
                            Log.e(TAG, "Error: ${e.message}")
                        }
                    }
                }

                override fun onFailure(call: Call<UbahPasswordResponse>, t: Throwable) {
                    MyDialog.hideDialog()
                    MyDialog.dynamicDialog(this@UbahPasswordActivity, getString(R.string.alert), "Error: ${t.message}")
                }

            })
    }

    private fun checkValidation(oldPass: String, newPass: String, confirmNewPass: String): Boolean {
        when {
            oldPass.isEmpty() -> {
                binding.etOldPassword.error = getString(R.string.please_field_your_password)
                binding.etOldPassword.requestFocus()
            }
            newPass.isEmpty() -> {
                binding.etNewPassword.error = getString(R.string.please_field_your_password)
                binding.etNewPassword.requestFocus()
            }
            confirmNewPass.isEmpty() -> {
                binding.etConfirmNewPassword.error = getString(R.string.please_field_your_password)
                binding.etConfirmNewPassword.requestFocus()
            }
            newPass != confirmNewPass -> {
                binding.etNewPassword.error = getString(R.string.your_password_didnt_match)
                binding.etNewPassword.requestFocus()
                binding.etConfirmNewPassword.error = getString(R.string.your_password_didnt_match)
                binding.etConfirmNewPassword.requestFocus()
            }
            else -> {
                binding.etNewPassword.error = null
                binding.etConfirmNewPassword.error = null
                return true
            }
        }
        return false
    }

    private fun init() {
        setSupportActionBar(binding.tbChangePassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}