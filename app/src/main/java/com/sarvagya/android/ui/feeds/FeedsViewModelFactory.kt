package com.sarvagya.android.ui.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sarvagya.android.ui.feeds.data.http.FeedsService
import com.sarvagya.android.ui.feeds.view.FeedsPresenter
import javax.inject.Inject

class FeedsViewModelFactory
@Inject // with this keyword FeedsViewModelFactory will get the already cooked object of FeedsService from AppComponent
        //AppComponent delegates this task to AppModule
constructor(
    private val feedsService: FeedsService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeedsViewModel(feedsService) as T
    }
}