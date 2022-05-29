package com.example.rekapresensionline.views.Profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rekapresensionline.R
import com.example.rekapresensionline.databinding.FragmentProfilBinding
import com.example.rekapresensionline.databinding.FragmentProfilBinding.*
import com.example.rekapresensionline.views.login.LoginActivity
import com.example.rekapresensionline.views.main.MainActivity
import com.example.rekapresensionline.views.ubahpass.UbahPasswordActivity
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ProfilFragment : Fragment() {

    private var binding: FragmentProfilBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        binding?.btnChangePassword?.setOnClickListener {
            context?.startActivity<UbahPasswordActivity>()
        }

        binding?.btnUbahBahasa?.setOnClickListener {
            context?.toast("Change Language")
        }

        binding?.btnLogout?.setOnClickListener {
            context?.startActivity<LoginActivity>()
            (activity as MainActivity).finishAffinity()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}