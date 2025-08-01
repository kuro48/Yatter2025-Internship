package com.dmm.bootcamp.yatter2025.ui.follow

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.ui.profile.UserProfileDestination.Companion.KEY_USERNAME
import com.dmm.bootcamp.yatter2025.ui.timeline.postdetail.PostDetailPage

class UserFollowDestination(private val username: String?
) : Destination(ROUTE) {
    override fun buildRoute(): String {
        return buildString {
            append(ROUTE_PATH)
            if (username != null) {
                append("?$KEY_USERNAME=$username")
            }
        }
    }
    companion object {
        private const val ROUTE_PATH = "followings"
        private const val KEY_USERNAME = "username"
        private const val ROUTE = "$ROUTE_PATH?$KEY_USERNAME={$KEY_USERNAME}"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = ROUTE,
                arguments = listOf(
                    navArgument(KEY_USERNAME) {
                        defaultValue = ""
                        type = NavType.StringType
                    },
                ),
            ) { backStackEntry ->
                val username = requireNotNull(backStackEntry.arguments?.getString(KEY_USERNAME))
                UserFollowPage(username = username)
            }
        }
    }
}