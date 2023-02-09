package com.example.saharamusic.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.saharamusic.R
import com.example.saharamusic.databinding.FragmentExoPlayerBinding
import com.example.saharamusic.utils.BaseFragment

private const val TAG = "ExoPlayerFragment"

class ExoPlayerFragment (): BaseFragment() {

    private var songUri = ""
    private var player : ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playBackPosition = 0L

    private val binding by lazy {
        FragmentExoPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getSongUri()
        initializePlayer()
    }

    private fun getSongUri() {
        songUri = saharaViewModel.songUri
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }



    private fun initializePlayer() {
        player = ExoPlayer.Builder(this.requireContext())
            .build()
            .also {
                binding.videoView.player = it
                if (songUri.isNotEmpty()) {
                    val mediaItem = MediaItem.fromUri(songUri.toUri())
                    it.setMediaItem(mediaItem)

                    it.playWhenReady = playWhenReady
                    it.seekTo(currentItem, playBackPosition)
                    it.prepare()
                } else {
                    Log.d(TAG, "initializePlayer: songUri value is an empty String")
                }
            }
    }

    private fun releasePlayer() {
        player?.let {
            playWhenReady = it.playWhenReady
            currentItem = it.currentMediaItemIndex
            playBackPosition = it.currentPosition
            it.release()
        }
        player = null
    }

}