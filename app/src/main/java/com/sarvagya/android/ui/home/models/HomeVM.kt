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

}

enum class DrawerMenus{
    D_FEEDS,
    D_VIDEOS,
    D_DONATION,
    D_APPOINTMENTS,
    FESTIVAL,
    MUSIC,

}
