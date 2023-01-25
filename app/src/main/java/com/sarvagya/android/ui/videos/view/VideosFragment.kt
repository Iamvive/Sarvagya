package com.sarvagya.android.ui.videos.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.sarvagya.android.R
import com.sarvagya.android.databinding.FragmentVideosBinding
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.home.HomeActivity
import com.sarvagya.android.ui.feeds.data.models.FeedDetail
import com.sarvagya.android.ui.videos.VideoAdapterOnClickListener
import com.sarvagya.android.ui.videos.VideosViewModel
import com.sarvagya.android.ui.videos.VideosViewModelFactory
import com.sarvagya.android.ui.videos.data.http.VideoService
import com.sarvagya.android.ui.videos.data.models.Video
import kotlinx.coroutines.launch
import javax.inject.Inject

class VideosFragment(private val activity: HomeActivity,private val listener: VideoAdapterOnClickListener) : Fragment() {

    private lateinit var videosBinding: FragmentVideosBinding
    private val videosAdapter by lazy { VideosAdapter(listener) }
    @Inject lateinit var videosViewModelFactory : VideosViewModelFactory
    private lateinit var viewModel: VideosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireContext().applicationContext as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        videosBinding = FragmentVideosBinding.inflate(layoutInflater)
        setUpVideosList()
        viewModel = ViewModelProviders.of(requireActivity(), videosViewModelFactory)[VideosViewModel::class.java]

        // toolbar icon change
        val musicButton = activity.binding.toolbar.menu.findItem(R.id.leftNavigate)
        musicButton.setIcon(R.drawable.ic_music)

        return videosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchVideos()
        observeData()
    }

    private fun observeData() {
        viewModel.liveVideoList.observe(viewLifecycleOwner) {
            videosAdapter.updateData(it)
        }
    }

    private fun setUpVideosList() {
        videosBinding.videosRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = videosAdapter
        }
    }
}
