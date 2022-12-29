package com.sarvagya.android.ui.home.models

data class HomeVM(
    val headerVM: HeaderVM,
    val selectedMenuItem: Menus,
)
data class HeaderVM(
    val title : String,
)
enum class Menus{
    FEEDS,
    VIDEOS,
    DONATION,
    APPOINTMENTS,
    VIDEOPLAYER,
}

enum class DrawerMenus{
    FESTIVAL,
}
