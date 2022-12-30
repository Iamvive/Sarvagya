package com.sarvagya.android.ui.festival

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarvagya.android.databinding.FragmentFestivalsBinding
import com.sarvagya.android.ui.home.HomeActivity

class FestivalFragment(private val activity: HomeActivity) : Fragment() {

    private lateinit var binding: FragmentFestivalsBinding
    private val festiveAdapter by lazy { FestiveAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFestivalsBinding.inflate(layoutInflater)
        setFestivalUI()
        return binding.root
    }

    private fun setFestivalUI() {
        binding.festiveRV.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = festiveAdapter
        }

        //to be remove later
        binding.tvMonth.text = "अक्टूबर"
        binding.tvYear.text = "2022"

        //toolbar navigationIcon null
        activity.binding.tbTitle.text = "Festival"
    }

}
