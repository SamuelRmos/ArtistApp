package com.example.sampleapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.databinding.FavoriteFragmentBinding
import com.example.sampleapp.view.adapter.FavoriteAdapter
import com.example.sampleapp.viewmodel.FavoriteViewModel
import com.example.sampleapp.viewmodel.ViewModelFactory

class FavoriteFragment : Fragment() {

    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteViewModel =
            ViewModelProvider(this, ViewModelFactory()).get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {
        val adapter = FavoriteAdapter()
        binding.recyclerView.adapter = adapter
        favoriteViewModel.favoriteLiveData.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
        })
    }
}