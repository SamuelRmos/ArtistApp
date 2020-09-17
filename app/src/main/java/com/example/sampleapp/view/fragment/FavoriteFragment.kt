package com.example.sampleapp.view.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FavoriteFragmentBinding
import com.example.sampleapp.view.activity.MainActivity
import com.example.sampleapp.view.adapter.FavoriteAdapter
import com.example.sampleapp.viewmodel.FavoriteViewModel
import com.example.sampleapp.viewmodel.ViewModelFactory

class FavoriteFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteViewModel = ViewModelProvider(this, ViewModelFactory())
                .get(FavoriteViewModel::class.java)
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
        adapter = FavoriteAdapter(arrayListOf())
        binding.recyclerView.adapter = adapter

        favoriteViewModel.artistFavorites().observe(viewLifecycleOwner, {
            adapter.updateList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = SearchView((context as MainActivity)
                .supportActionBar?.themedContext ?: context)

        menu.findItem(R.id.search).apply {
            actionView = searchView
        }

        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        return false
    }
}