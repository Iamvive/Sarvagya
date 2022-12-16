package com.sarvagya.android.ui.home.feeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.sarvagya.android.R
import com.sarvagya.android.util.StringProvider
import com.sarvagya.android.databinding.ActivityFeedDetailBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.root.SarvagyaApplication
import javax.inject.Inject

class FeedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedDetailBinding
    private val activity: FeedDetailActivity = this

    private lateinit var feedsViewModel: FeedsViewModel

    @Inject
    lateinit var feedsViewModelFactory: FeedsViewModelFactory

    @Inject
    lateinit var stringProvider: StringProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFeedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        feedsViewModel =
            ViewModelProviders.of(this, feedsViewModelFactory)[FeedsViewModel::class.java]

        val id = intent.getBundleExtra(FEED_DATA)?.getString(FEED_ID)
        if (id != null) {
            feedsViewModel.fetchFeedDetail(id)
        }

        observeData()

        binding.toolbar.title = stringProvider(R.string.news)

        //back to home
        binding.toolbar.setNavigationOnClickListener {
            activity.finish()
        }

    }

    private fun observeData() {
        feedsViewModel
            .liveFeedDetail
            .observe(this) { feed ->
                binding.apply {
                    feedTitleTV.text = feed.title
                    feedDescTV.text = feed.desc
                    feedIV.loadImage(feed.thumbnail)
                }
            }
    }

    companion object {
        const val FEED_ID = "feed_id"
        const val FEED_DATA = "feed_data"
    }
}