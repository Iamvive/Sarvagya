package com.sarvagya.android.ui.home.feeds.view

import kotlinx.coroutines.flow.Flow

interface FeedsPresenter {
    fun didTapBack() : Unit
    fun didTapItem() : Flow<Any>
}