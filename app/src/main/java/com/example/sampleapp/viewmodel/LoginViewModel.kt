package com.example.sampleapp.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.sampleapp.view.fragment.LoginFragmentDirections

class LoginViewModel: ViewModel() {

    fun createOnClickListener(): View.OnClickListener{
        return View.OnClickListener {
            it.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }

}