package com.example.rekapresensionline.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.rekapresensionline.R

object MyDialog {

    private var dialogBuilder: AlertDialog? = null

    fun dynamicDialog(context: Context?, title: String, message: String){
        dialogBuilder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .show()
    }

    @SuppressLint("InflateParams")
    fun showProgressDialog(context: Context?){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_progres, null)
        dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialogBuilder?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogBuilder?.show()
    }

    fun hideDialog(){
        if (dialogBuilder != null){
            dialogBuilder?.dismiss()
        }
    }

}