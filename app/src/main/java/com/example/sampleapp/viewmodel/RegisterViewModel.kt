package com.example.sampleapp.viewmodel

import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.sampleapp.App
import com.example.sampleapp.databinding.RegisterFragmentBinding
import com.example.sampleapp.repository.UserRepository
import com.example.sampleapp.util.Messages
import com.example.sampleapp.view.activity.LoginActivity
import com.example.sampleapp.view.activity.MainActivity
import com.example.sampleapp.view.fragment.DatePickerFragment

class RegisterViewModel(private val userRepository: UserRepository, private val app: App) :
    ViewModel() {

    fun clickButton(binding: RegisterFragmentBinding): View.OnClickListener {

        return View.OnClickListener {
            binding.let {
                val name = it.etName.text.toString()
                val email = it.etEmailRegister.text.toString()
                val password = it.etPasswordRegister.text.toString()
                val confirmPassword = it.etConfirmPassword.text.toString()
                val birth = it.etBirth.text.toString()
                validationFields(name, email, password, confirmPassword, birth)
            }
        }
    }

    private fun validationFields(
        name: String, email: String, password: String,
        confirmPassword: String, birth: String
    ) {
        when {
            name.isBlank() || email.isBlank() || password.isBlank()
                    || confirmPassword.isBlank() || birth.isBlank() -> {
                Toast.makeText(
                    app.applicationContext, Messages.emptyField, Toast.LENGTH_SHORT
                ).show()
            }
            password != confirmPassword -> {
                Toast.makeText(
                    app.applicationContext, Messages.passwordNotEqual, Toast.LENGTH_SHORT
                ).show()
            }
            password.length < 5 && confirmPassword.length < 5 -> {
                Toast.makeText(
                    app.applicationContext, Messages.passwordWeak, Toast.LENGTH_SHORT
                ).show()
            }
            !email.contains("@") -> {
                Toast.makeText(
                    app.applicationContext, Messages.emailValidate, Toast.LENGTH_SHORT
                ).show()
            }
            else -> writeUser(name, email, password, birth)
        }
    }

    private fun writeUser(name: String, email: String, password: String, birth: String) {
        val id = java.util.UUID.randomUUID().toString()
        userRepository.insertUser(id, name, email, password, birth)
        MainActivity.startActivityMain(app)
        LoginActivity().finish()
    }

    fun clickListener(
        fragmentManager: FragmentManager, binding: RegisterFragmentBinding
    ): View.OnClickListener {
        return View.OnClickListener {
            val dialogFragment = DatePickerFragment(binding)
            dialogFragment.show(fragmentManager, "datePicker")
        }
    }
}