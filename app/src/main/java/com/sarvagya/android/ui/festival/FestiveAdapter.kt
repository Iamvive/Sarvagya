package com.sarvagya.android.ui.festival

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarvagya.android.databinding.ItemFestiveBinding
import com.sarvagya.android.extension.loadImage
import com.sarvagya.android.ui.home.HomeActivity

class FestiveAdapter() : RecyclerView.Adapter<FestiveAdapter.FestiveVM>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestiveVM {
        val viewBinding = ItemFestiveBinding.inflate(LayoutInflater.from(parent.context))
        return FestiveVM(viewBinding)
    }

    override fun onBindViewHolder(holder: FestiveVM, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return 17
    }

    inner class FestiveVM(private val viewBinding: ItemFestiveBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData() {
            viewBinding.apply {
                viewBinding.festiveName.text = "दीपावली"
                viewBinding.festiveDesc.text = "रोशनी और खुशियों का त्योहार"
                viewBinding.dateAndTime.text = "सोमवार, 24 अक्टूबर"
                viewBinding.festiveIV.loadImage("https://sarvagya.blob.core.windows.net/images/gettyimages-641015871-sixteen_nine.webp")
            }
        }
    }
}