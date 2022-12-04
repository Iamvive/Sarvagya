package com.sarvagya.android.ui.home.videos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.FragmentVideosBinding

class VideosFragment : Fragment() {

    private lateinit var videosBinding: FragmentVideosBinding
    private val videosAdapter by lazy { VideosAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        videosBinding = FragmentVideosBinding.inflate(layoutInflater)
        setUpVideosList()
        return videosBinding.root
    }

    private fun setUpVideosList() {
        videosBinding.videosRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = videosAdapter
        }
    }
}