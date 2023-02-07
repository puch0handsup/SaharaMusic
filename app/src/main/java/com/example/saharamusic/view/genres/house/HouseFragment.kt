package com.example.saharamusic.view.genres.house

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saharamusic.R
import com.example.saharamusic.databinding.FragmentGenreCommonViewBinding
import com.example.saharamusic.utils.BaseFragment
import javax.inject.Inject

private const val TAG = "HouseFragment"

class HouseFragment : BaseFragment() {

    private val binding by lazy {
        FragmentGenreCommonViewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Log.d(TAG, "onCreateView: ")



        return binding.root
    }

}