package com.sarvagya.android.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationBarView
import com.sarvagya.android.R
import com.sarvagya.android.R.id
import com.sarvagya.android.util.StringProvider
import com.sarvagya.android.databinding.ActivityHomeBinding
import com.sarvagya.android.extension.attachWithReplace
import com.sarvagya.android.extension.navigateToActivity
import com.sarvagya.android.extension.showSnackbar
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.ui.home.appointment.AppointmentFragment
import com.sarvagya.android.ui.home.donation.DonationFragment
import com.sarvagya.android.ui.home.feeds.FeedDetailActivity
import com.sarvagya.android.ui.home.feeds.FeedDetailActivity.Companion.FEED_DATA
import com.sarvagya.android.ui.home.feeds.FeedDetailActivity.Companion.FEED_ID
import com.sarvagya.android.ui.home.feeds.FeedsListener
import com.sarvagya.android.ui.home.feeds.view.FeedsFragment
import com.sarvagya.android.ui.home.models.HeaderVM
import com.sarvagya.android.ui.home.models.HomeVM
import com.sarvagya.android.ui.home.models.Menus
import com.sarvagya.android.ui.home.models.Menus.*
import com.sarvagya.android.ui.home.videos.VideoAdapterOnClickListener
import com.sarvagya.android.ui.home.videos.view.VideoPlayerActivity
import com.sarvagya.android.ui.home.videos.view.VideoPlayerActivity.Companion.VIDEO_DATA
import com.sarvagya.android.ui.home.videos.view.VideoPlayerActivity.Companion.VIDEO_ID
import com.sarvagya.android.ui.home.videos.view.VideoPlayerFragment
import com.sarvagya.android.ui.home.videos.view.VideosFragment
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private var counter = 0

    @Inject
    lateinit var stringProvider: StringProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //bottom navigation
        binding.homeNavigation.setOnItemSelectedListener(this)

        //start navigation drawer
        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        //load feed fragment
        loadFragment(FEEDS)
    }

    override fun onResume() {
        super.onResume()
        binding.toolbar.title = ""
        val vm = getHomeVM()
        renderHeaderView(vm.headerVM)
    }

    private fun renderHeaderView(vm: HeaderVM) {
        binding.tbTitle.text = vm.title
    }

    private fun getHomeVM() = HomeVM(
        getHeaderVM(), FEEDS
    )

    private fun getHeaderVM(): HeaderVM =
        HeaderVM(stringProvider(R.string.news))

    private fun loadFragment(selectedItem: Menus) {
        when (selectedItem) {
            FEEDS -> FeedsFragment(FeedsListenerImpl()).attachWithReplace(this)
            VIDEOS -> VideosFragment(VideoAdapterOnClickListenerImpl()).attachWithReplace(this)
            DONATION -> DonationFragment().attachWithReplace(this)
            APPOINTMENTS -> AppointmentFragment().attachWithReplace(this)
            VIDEOPLAYER -> VideoPlayerFragment(this).attachWithReplace(this)
        }
    }

    inner class FeedsListenerImpl : FeedsListener {
        override fun onFeedTapped(id: String) {
            navigateToActivity(
                FeedDetailActivity::class.java, shouldFinish = false,
                bundleKey = FEED_DATA, bundle = Bundle().apply { putString(FEED_ID, id) }
            )
        }
    }

    inner class VideoAdapterOnClickListenerImpl : VideoAdapterOnClickListener {
        override fun onClick(id: Int) {
            navigateToActivity(VideoPlayerActivity::class.java, shouldFinish = false,
                bundleKey = VIDEO_DATA, bundle = Bundle().apply
                { putInt(VIDEO_ID, id) })
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            id.feeds -> {
                binding.tbTitle.text = stringProvider(R.string.news)
                loadFragment(FEEDS)
            }
            id.videos -> {
                binding.tbTitle.text = stringProvider(R.string.videos)
                loadFragment(VIDEOS)
            }
            id.donation -> {
                binding.tbTitle.text = stringProvider(R.string.donation)
                loadFragment(DONATION)
            }
            id.appointment -> {
                binding.tbTitle.text = stringProvider(R.string.appointment)
                loadFragment(APPOINTMENTS)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item!!)
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.leftNavigate -> {
//                 startActivity(Intent(this,))//TODO: Music screen render
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        counter++
        if (counter >= 2) {
            super.onBackPressed()
        } else {
            this.showSnackbar(
                resId = R.string.exit_msg,
                view = binding.root,
            )
        }
    }
}
