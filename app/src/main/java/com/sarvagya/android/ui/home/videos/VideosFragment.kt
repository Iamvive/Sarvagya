package com.sarvagya.android.ui.home.videos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.sarvagya.android.databinding.FragmentVideosBinding

class VideosFragment(private val listener: VideoAdapterOnClickListener) : Fragment() {

    private lateinit var videosBinding: FragmentVideosBinding
    private val videosAdapter by lazy { VideosAdapter(listener) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        videosBinding = FragmentVideosBinding.inflate(layoutInflater)
        setUpVideosList()
        return videosBinding.root
    }

    private fun setUpVideosList() {
        videosBinding.videosRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(MaterialDividerItemDecoration(this.context, RecyclerView.VERTICAL))
            adapter = videosAdapter
        }
    }

}
