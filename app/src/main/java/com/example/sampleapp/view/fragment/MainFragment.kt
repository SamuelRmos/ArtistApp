package com.example.sampleapp.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapp.App
import com.example.sampleapp.R
import com.example.sampleapp.databinding.MainFragmentBinding
import com.example.sampleapp.model.Artist
import com.example.sampleapp.repository.ArtistDataRepository
import com.example.sampleapp.util.LiveDataResult
import com.example.sampleapp.view.activity.MainActivity
import com.example.sampleapp.view.adapter.ArtistAdapter
import com.example.sampleapp.viewmodel.MainViewModel
import com.example.sampleapp.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: MainFragmentBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: ArtistAdapter

    @Inject
    lateinit var artistDataRepository: ArtistDataRepository

    private val mDataObserver = Observer<LiveDataResult<MutableList<Artist>>> {
        when (it.status) {
            LiveDataResult.STATUS.ERROR -> Toast
                    .makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()

            LiveDataResult.STATUS.SUCCESS -> setRecyclerView(it.data!!)

            LiveDataResult.STATUS.LOADING -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = App.appComponent
        appComponent.inject(this)

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
        adapter = ArtistAdapter(requireActivity(), arrayListOf(), artistDataRepository)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.fetchArtist().observe(viewLifecycleOwner, mDataObserver)
    }

    private fun setRecyclerView(list: MutableList<Artist>) {
        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            adapter.updateList(list)
        }
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


    override fun onQueryTextChange(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }
}