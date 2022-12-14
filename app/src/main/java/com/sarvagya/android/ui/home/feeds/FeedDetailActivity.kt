package com.sarvagya.android.ui.home.feeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.lifecycle.ViewModelProviders
import com.sarvagya.android.R
import com.sarvagya.android.databinding.ActivityFeedDetailBinding
import com.sarvagya.android.databinding.ActivityHomeBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.root.SarvagyaApplication
import javax.inject.Inject

class FeedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedDetailBinding
    private val activity : FeedDetailActivity  = this

    private lateinit var feedsViewModel: FeedsViewModel
    @Inject
    lateinit var feedsViewModelFactory: FeedsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFeedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        feedsViewModel = ViewModelProviders.of(this, feedsViewModelFactory)[FeedsViewModel::class.java]

        val id =  intent.extras?.getString("feedId").toString()
        feedsViewModel.fetchFeedDetail(id)

        observeData()

        //setting back icon
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back)
        binding.toolbar.title = "News"

        //back to home
        binding.toolbar.setNavigationOnClickListener {
            activity.finish()
        }

    }

    private fun observeData() {
        feedsViewModel
            .liveFeedDetail
            .observe(this) { feeds ->
                binding.feedTitleTV.text = feeds.title
                binding.feedDescTV.text = feeds.desc
                binding.feedIV.loadImage(feeds.thumbnail)
            }
    }
}