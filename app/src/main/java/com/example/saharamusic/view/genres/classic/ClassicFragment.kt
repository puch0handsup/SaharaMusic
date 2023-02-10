package com.example.saharamusic.view.genres.classic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saharamusic.R
import com.example.saharamusic.databinding.FragmentGenreCommonViewBinding
import com.example.saharamusic.model.SongResponse
import com.example.saharamusic.utils.BaseFragment
import com.example.saharamusic.utils.UIState
import com.example.saharamusic.view.adapter.GenresAdapter


class ClassicFragment : BaseFragment() {

    val binding by lazy {
        FragmentGenreCommonViewBinding.inflate(layoutInflater)
    }

    val genresAdapter by lazy {
        GenresAdapter {
            saharaViewModel.songUri = it
            findNavController().navigate(R.id.action_classic_fragment_to_exo_player_fragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.listByGenreRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true) // this is to see if it doesn't matter what happens to the
            adapter = genresAdapter
        }

        retrieveSongs()
        binding.swipeRefreshLayout.setOnRefreshListener {
            retrieveSongs()
        }

        return binding.root
    }

    private fun retrieveSongs() {
        saharaViewModel.classicSongs.observe(viewLifecycleOwner) { state ->
            when(state){
                is UIState.LOADING -> { binding.swipeRefreshLayout.isRefreshing = true }
                is UIState.SUCCESS<SongResponse> -> {
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