package com.sarvagya.android.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.sarvagya.android.R.*
import com.sarvagya.android.databinding.ActivityHomeBinding
import com.sarvagya.android.ui.home.appointment.AppointmentFragment
import com.sarvagya.android.ui.home.donation.DonationFragment
import com.sarvagya.android.ui.home.feeds.FeedDetailActivity
import com.sarvagya.android.ui.home.feeds.FeedsListener
import com.sarvagya.android.ui.home.feeds.view.FeedsFragment
import com.sarvagya.android.ui.home.models.HeaderVM
import com.sarvagya.android.ui.home.models.HomeVM
import com.sarvagya.android.ui.home.models.Menus
import com.sarvagya.android.ui.home.models.Menus.*
import com.sarvagya.android.ui.home.videos.VideoAdapterOnClickListener
import com.sarvagya.android.ui.home.videos.VideoPlayerActivity
import com.sarvagya.android.ui.home.videos.VideoPlayerFragment
import com.sarvagya.android.ui.home.videos.VideosFragment

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener,
    VideoAdapterOnClickListener, FeedsListener {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //toolbar
        setToolBar()

        //bottom navigation
        binding.homeNavigation.setOnItemSelectedListener(this)

        //start navigation drawer
        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }

        //load feed fragment
        loadFragment(FEEDS)
    }

    override fun onResume() {
        super.onResume()
        val vm = getHomeVM()
        setToolBar()
        renderHeaderView(vm.headerVM)
    }

    private fun setToolBar(){
        //title margin
        binding.toolbar.setTitleMargin(300,0,300,0)

        //setting menu icon for drawer
        binding.toolbar.navigationIcon = resources.getDrawable(drawable.ic_menu)

    }

    private fun renderHeaderView(vm: HeaderVM) {
        binding.toolbar.title = vm.title
    }

    private fun getHomeVM() = HomeVM(
        getHeaderVM(), FEEDS
    )

    private fun getHeaderVM(): HeaderVM = HeaderVM("Feed", drawable.ic_launcher_background)

    private fun loadFragment(selectedItem: Menus) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id.mainContainer, getFragment(selectedItem))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun getFragment(menuItem: Menus) = when (menuItem) {
        FEEDS -> FeedsFragment(this)
        VIDEOS -> VideosFragment(this)
        DONATION -> DonationFragment()
        APPOINTMENTS -> AppointmentFragment()
        VIDEOPLAYER -> VideoPlayerFragment(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            id.feeds -> {

                binding.toolbar.title = "News"
                loadFragment(FEEDS)
            }
            id.videos -> {
                binding.toolbar.title = "Video"
                loadFragment(VIDEOS)
            }
            id.donation -> {
                binding.toolbar.title = "Donate"
                loadFragment(DONATION)
            }
            id.appointment -> {
                binding.toolbar.title = "Book Appointment"
                loadFragment(APPOINTMENTS)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.sarvagya.android.R.menu.menu_toolbar, menu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item!!)
        }
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.leftNavigate -> {
                // startActivity(Intent(applicationContext,))//TODO: Music screen render
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick() {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        startActivity(intent)
    }

    override fun onClickFeeds(id:String) {
        val intent = Intent(this, FeedDetailActivity::class.java).apply{
            putExtra("feedId",id)
        }
        startActivity(intent)
    }

}