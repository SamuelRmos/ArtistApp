package com.example.sampleapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.databinding.RegisterFragmentBinding
import com.example.sampleapp.viewmodel.RegisterViewModel
import com.example.sampleapp.viewmodel.ViewModelFactory

class RegisterFragment : Fragment() {

    lateinit var binding: RegisterFragmentBinding
    lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory())
            .get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFieldBirth(viewModel.clickListener(childFragmentManager, binding))
        bindButton(viewModel.clickButton(binding))
    }

    private fun bindFieldBirth(clickListener: View.OnClickListener) {
        binding.apply {
            click = clickListener
        }
    }

    private fun bindButton(clickListener: View.OnClickListener) {
        binding.apply {
            clickButton = clickListener
        }
    }
}