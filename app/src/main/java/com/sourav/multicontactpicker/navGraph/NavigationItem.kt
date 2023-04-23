package com.sourav.multicontactpicker.navGraph


sealed class NavigationItem(
    val route : String,
    val title : String,
) {

    object ContactPickerScreen : NavigationItem(
        route = "contact_picker_screen",
        title = "Contact Picker"
    )

    object HomeScreen : NavigationItem(
        route = "home_screen",
        title = "Home"
    )


    fun withArgs(vararg args : String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}