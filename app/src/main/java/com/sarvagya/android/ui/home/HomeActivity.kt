package com.sarvagya.android.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import com.sarvagya.android.R
import com.sarvagya.android.databinding.ActivityHomeBinding
import com.sarvagya.android.ui.home.appointment.AppointmentFragment
import com.sarvagya.android.ui.home.donation.DonationFragment
import com.sarvagya.android.ui.home.feeds.view.FeedsFragment
import com.sarvagya.android.ui.home.models.HeaderVM
import com.sarvagya.android.ui.home.models.HomeVM
import com.sarvagya.android.ui.home.models.Menus
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView
import com.sarvagya.android.ui.home.models.Menus.*
import com.sarvagya.android.ui.home.videos.VideosFragment

class HomeActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

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
        binding.toolbar.title = vm.title
    }

    private fun getHomeVM() = HomeVM(
        getHeaderVM(), FEEDS
    )

    private fun getHeaderVM(): HeaderVM = HeaderVM("Feed", R.drawable.ic_launcher_background)

    private fun loadFragment(selectedItem: Menus) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContainer, getFragment(selectedItem))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun getFragment(menuItem: Menus) = when (menuItem) {
        FEEDS -> FeedsFragment()
        VIDEOS -> VideosFragment()
        DONATION -> DonationFragment()
        APPOINTMENTS -> AppointmentFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.feeds -> loadFragment(FEEDS)
            R.id.videos -> loadFragment(VIDEOS)
            R.id.donation -> loadFragment(DONATION)
            R.id.appointment -> loadFragment(APPOINTMENTS)
        }
        return true
    }


}