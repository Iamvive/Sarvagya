package com.sarvagya.android.ui.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.*
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.sarvagya.android.databinding.FragmentDonationBinding

class DonationFragment : Fragment() {
    
    private lateinit var binding: FragmentDonationBinding
    private val amountList = listOf("+50", "+100", "+150", "+200")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDonationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAmountSelectionView()
    }

    private fun setUpAmountSelectionView() {
       val chipWidth = 200
        amountList.forEach {
            val chip = Chip(binding.amountChips.context)
                .apply {
                    text = it
                    isClickable = true
                    layoutParams = LayoutParams(chipWidth, MATCH_PARENT)
                }
            binding.amountChips.addView(chip)
        }
    }

}
