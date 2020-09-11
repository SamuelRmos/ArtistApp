package com.example.sampleapp.view.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.R
import com.example.sampleapp.databinding.MainFragmentBinding
import com.example.sampleapp.view.activity.MainActivity
import com.example.sampleapp.view.adapter.ArtistAdapter
import com.example.sampleapp.viewmodel.MainViewModel
import com.example.sampleapp.viewmodel.ViewModelFactory

class MainFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: MainFragmentBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(
                this,
                ViewModelFactory()).get(MainViewModel::class.java)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = MainFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        adapter = ArtistAdapter(requireActivity(), arrayListOf())
        setRecyclerView()
        return binding.root
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = adapter
        mainViewModel.artistLiveData.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)
        val searchView = SearchView((context as MainActivity)
                .supportActionBar?.themedContext ?: context)

        menu.findItem(R.id.search).apply {
            setShowAsAction(
                    MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
                            or MenuItem.SHOW_AS_ACTION_IF_ROOM
            )

            actionView = searchView
        }

        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?) = false


    override fun onQueryTextChange(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }
}