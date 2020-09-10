package com.example.sampleapp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.view.adapter.MainPagerAdapter
import com.example.sampleapp.view.transition.DepthPageTransformer

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.viewpager.adapter = MainPagerAdapter(this)
        binding.viewpager.setPageTransformer(DepthPageTransformer())

        binding.apply {
            navigation = mainBottomNavigation
        }
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        fun startActivityMain(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
