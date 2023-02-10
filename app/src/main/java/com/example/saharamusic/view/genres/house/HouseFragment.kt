package com.example.saharamusic.view.genres.house

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.saharamusic.R
import com.example.saharamusic.databinding.FragmentGenreCommonViewBinding
import com.example.saharamusic.model.SongResponse
import com.example.saharamusic.utils.BaseFragment
import com.example.saharamusic.utils.UIState
import com.example.saharamusic.view.adapter.GenresAdapter

private const val TAG = "HouseFragment"

class HouseFragment : BaseFragment() {

    private val binding by lazy {
        FragmentGenreCommonViewBinding.inflate(layoutInflater)
    }

    private val genresAdapter by lazy {
        GenresAdapter {
            Log.d(TAG, "Item Clicked. Preview URL: $it")
            saharaViewModel.songUri = it
            findNavController().navigate(R.id.action_house_fragment_to_exo_player_fragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.listByGenreRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true) // this is to see if it doesn't matter what happens to the
            adapter = genresAdapter
        }
        Log.d(TAG, "onCreateView: ")

        retrieveSongs()
        binding.swipeRefreshLayout.setOnRefreshListener {
            retrieveSongs()
        }

        return binding.root
    }

    private fun retrieveSongs() {
        saharaViewModel.houseSongs.observe(viewLifecycleOwner) { state ->
            when(state){
                is UIState.LOADING -> { binding.swipeRefreshLayout.isRefreshing = true }
                is UIState.SUCCESS<SongResponse> -> {
                    Log.d(TAG, "onCreateView: ${state.response}")
                    hideSwipeAction()
                    genresAdapter.updateItems(state.response.results ?: emptyList())
                }
                is UIState.ERROR -> {
                    showError(state.error.localizedMessage) {}
                    hideSwipeAction()
                }
            }
        }
    }

    private fun hideSwipeAction() {
        if (binding.swipeRefreshLayout.isRefreshing)
            binding.swipeRefreshLayout.isRefreshing = false
    }

}