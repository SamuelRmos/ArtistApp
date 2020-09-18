package com.example.sampleapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sampleapp.view.fragment.FavoriteFragment
import com.example.sampleapp.view.fragment.MainFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            else -> FavoriteFragment()
        }
    }

    override fun getItemCount(): Int = 2
}