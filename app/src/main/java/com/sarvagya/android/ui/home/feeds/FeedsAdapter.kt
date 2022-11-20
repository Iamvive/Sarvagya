package com.sarvagya.android.ui.home.feeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sarvagya.android.R

class FeedsAdapter( ) : RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_home, parent, false)
       return FeedsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class FeedsViewHolder(itemView: View) : ViewHolder(itemView){

    }

}