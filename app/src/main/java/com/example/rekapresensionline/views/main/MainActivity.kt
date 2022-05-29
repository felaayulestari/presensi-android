package com.example.rekapresensionline.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rekapresensionline.R
import com.example.rekapresensionline.databinding.ActivityMainBinding
import com.example.rekapresensionline.views.Histori.HistoriFragment
import com.example.rekapresensionline.views.Presensi.PresensiFragment
import com.example.rekapresensionline.views.Profil.ProfilFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.btmNavigationMain.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_histori -> {
                    openFragment(HistoriFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.action_presensi -> {
                    openFragment(PresensiFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.action_profil -> {
                    openFragment(ProfilFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        openHomeFragment()
    }

    private fun openHomeFragment() {
        binding.btmNavigationMain.selectedItemId = R.id.action_presensi
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}