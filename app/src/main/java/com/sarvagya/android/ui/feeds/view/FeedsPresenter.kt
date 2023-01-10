package com.sarvagya.android.ui.feeds.view

import kotlinx.coroutines.flow.Flow

interface FeedsPresenter {
    fun didTapBack() : Unit
    fun didTapItem() : Flow<String>
}
