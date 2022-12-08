package com.sarvagya.android.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.sarvagya.android.R
import com.sarvagya.android.R.*
import com.sarvagya.android.databinding.ActivityHomeBinding
import com.sarvagya.android.ui.home.appointment.AppointmentFragment
import com.sarvagya.android.ui.home.donation.DonationFragment
import com.sarvagya.android.ui.home.feeds.view.FeedsFragment
import com.sarvagya.android.ui.home.models.HeaderVM
import com.sarvagya.android.ui.home.models.HomeVM
import com.sarvagya.android.ui.home.models.Menus
import com.sarvagya.android.ui.home.models.Menus.*
import com.sarvagya.android.ui.home.videos.VideoAdapterOnClickListener
import com.sarvagya.android.ui.home.videos.VideoPlayerFragment
import com.sarvagya.android.ui.home.videos.VideosFragment


class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener, VideoAdapterOnClickListener{

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.homeNavigation.setOnItemSelectedListener(this)
        loadFragment(DONATION)
    }

    override fun onResume() {
        super.onResume()
        val vm = getHomeVM()
        renderHeaderView(vm.headerVM)
    }

    private fun renderHeaderView(vm: HeaderVM) {
        binding.toolbarTitle.text = vm.title
    }

    private fun getHomeVM() = HomeVM(
        getHeaderVM(), FEEDS
    )

    private fun getHeaderVM(): HeaderVM = HeaderVM("Feed", R.drawable.ic_launcher_background)

    private fun loadFragment(selectedItem: Menus) {
        binding.toolbar.visibility = View.INVISIBLE
        binding.homeNavigation.visibility = View.INVISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContainer, getFragment(selectedItem))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun getFragment(menuItem: Menus) = when (menuItem) {
        FEEDS -> FeedsFragment()
        VIDEOS -> VideosFragment(this)
        DONATION -> DonationFragment()
        APPOINTMENTS -> AppointmentFragment()
        VIDEOPLAYER -> VideoPlayerFragment(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            id.feeds -> loadFragment(FEEDS)
            id.videos -> loadFragment(VIDEOS)
            id.donation -> loadFragment(DONATION)
            id.appointment -> loadFragment(APPOINTMENTS)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
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
        binding.toolbar.visibility = View.INVISIBLE
        binding.homeNavigation.visibility = View.INVISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContainer, getFragment(VIDEOPLAYER))
        transaction.addToBackStack(null)
        transaction.commit()
    }

}