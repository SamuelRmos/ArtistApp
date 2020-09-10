package com.example.sampleapp.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.sampleapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("bindNavigation")
fun bindNavigation(view: ViewPager2, navigationView: BottomNavigationView) {
    view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            view.adapter?.notifyDataSetChanged()
            navigationView.menu.getItem(position).isChecked = true
        }
    })

    navigationView.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.action_one -> view.currentItem = 0
            R.id.action_two -> view.currentItem = 1
        }
        true
    }
}