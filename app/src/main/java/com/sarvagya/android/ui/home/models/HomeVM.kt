package com.sarvagya.android.ui.home.models

data class HomeVM(
    val headerVM: HeaderVM,
    val selectedMenuItem: MenuItem
)
data class HeaderVM(
    val title : String,
    val iconRes : Int?
)
enum class MenuItem{
    FEEDS,
    VIDEOS,
    DONATION,
    APPOINTMENTS
}
