package com.sarvagya.android.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.sarvagya.android.R
import com.sarvagya.android.R.id
import com.sarvagya.android.databinding.ActivityHomeBinding
import com.sarvagya.android.extension.attachWithReplace
import com.sarvagya.android.extension.navigateToActivity
import com.sarvagya.android.extension.showSnackbar
import com.sarvagya.android.root.SarvagyaApplication
import com.sarvagya.android.root.manager.shake.ShakeManager
import com.sarvagya.android.ui.festival.FestivalFragment
import com.sarvagya.android.ui.appointment.AppointmentFragment
import com.sarvagya.android.ui.donation.DonationFragment
import com.sarvagya.android.ui.feeds.FeedDetailActivity
import com.sarvagya.android.ui.feeds.FeedDetailActivity.Companion.FEED_DATA
import com.sarvagya.android.ui.feeds.FeedDetailActivity.Companion.FEED_ID
import com.sarvagya.android.ui.feeds.FeedsListener
import com.sarvagya.android.ui.feeds.view.FeedsFragment
import com.sarvagya.android.ui.home.DrawerMenus
import com.sarvagya.android.ui.home.DrawerMenus.*
import com.sarvagya.android.ui.home.HeaderVM
import com.sarvagya.android.ui.home.HomeVM
import com.sarvagya.android.ui.home.Menus
import com.sarvagya.android.ui.home.Menus.*
import com.sarvagya.android.ui.videos.VideoAdapterOnClickListener
import com.sarvagya.android.ui.videos.view.VideoPlayerActivity
import com.sarvagya.android.ui.videos.view.VideoPlayerActivity.Companion.VIDEO_DATA
import com.sarvagya.android.ui.videos.view.VideoPlayerActivity.Companion.VIDEO_ID
import com.sarvagya.android.ui.videos.view.VideosFragment
import com.sarvagya.android.ui.music.MusicActivity
import com.sarvagya.android.ui.music.MusicFragment
import com.sarvagya.android.ui.videos.VideoListener
import com.sarvagya.android.util.StringProvider
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityHomeBinding
    private var counter = 0
    private val drawerListener = DrawerListener()

    @Inject
    lateinit var stringProvider: StringProvider

    @Inject
    lateinit var shakeManager : ShakeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SarvagyaApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        shakeManager.invoke()

        //bottom navigation
        binding.homeNavigation.setOnItemSelectedListener(this)

        binding.navView.setNavigationItemSelectedListener(drawerListener)

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
            FEEDS -> FeedsFragment(this, FeedsListenerImpl()).attachWithReplace(this)
            VIDEOS -> VideosFragment(this, VideoAdapterOnClickListenerImpl()).attachWithReplace(this)
            DONATION -> DonationFragment().attachWithReplace(this)
            APPOINTMENTS -> AppointmentFragment().attachWithReplace(this)
        }
    }

    private fun loadDrawerFragment(selectedDrawerItem: DrawerMenus) {
        when (selectedDrawerItem) {
            FESTIVAL -> FestivalFragment(this).attachWithReplace(this)
            MUSIC -> navigateToActivity(MusicActivity::class.java, shouldFinish = false)
            else -> {
            }
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
            navigateToActivity(
                VideoPlayerActivity::class.java,
                shouldFinish = false,
                bundleKey = VIDEO_DATA,
                bundle = Bundle().apply
                { putInt(VIDEO_ID, id) }
            )
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {    //bottom navigation listener
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
                this.showSnackbar(
                    resId = R.string.coming_soon,
                    view = binding.root,
                )
            }
            id.appointment -> {
                this.showSnackbar(
                    resId = R.string.coming_soon,
                    view = binding.root,
                )
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.music_toolbar, menu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item!!)
        }
        return true
    }

    // overriden method for toolbar menus
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.leftNavigate -> {
                binding.tbTitle.text = stringProvider(R.string.music)
                   MusicFragment(this).attachWithReplace(this)
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



    inner class DrawerListener : NavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                id.festival -> {

                    //close drawer
                    binding.drawerLayout.closeDrawers()

                    //Remove Music Icon
                    val musicButton = binding.toolbar.menu.findItem(R.id.leftNavigate)
                    musicButton.setIcon(R.drawable.blank)

                    //load festival fragment
                    loadDrawerFragment(FESTIVAL)
                }

                id.video -> {
                    //close drawer
                    binding.drawerLayout.closeDrawers()

                    //change toolbar title
                    binding.tbTitle.text = stringProvider(R.string.videos)

                    //load video fragment
                    loadFragment(VIDEOS)
                }

                id.feed -> {
                    //close drawer
                    binding.drawerLayout.closeDrawers()

                    //change toolbar title
                    binding.tbTitle.text = stringProvider(R.string.news)

                    //load feed fragment
                    loadFragment(FEEDS)
                }

                id.donation -> {
                    //close drawer
                    binding.drawerLayout.closeDrawers()

                    //change toolbar title
                    binding.tbTitle.text = stringProvider(R.string.donation)

                    //load feed fragment
                    loadDrawerFragment(D_DONATION)
                }

                id.aarati -> {
                    //close drawer
                    binding.drawerLayout.closeDrawers()

                    //change toolbar title
                    binding.tbTitle.text = stringProvider(R.string.music)

                    //load music fragment
                    loadDrawerFragment(MUSIC)

                }

            }
            return true
        }
    }
}
