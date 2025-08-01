package com.dmm.bootcamp.yatter2025.ui.follower

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun UserFollowerPage(
    userFollowerViewModel: UserFollowerViewModel = getViewModel(),
    username: String
) {
    val uiState by userFollowerViewModel.uiState.collectAsState()
    val destination by userFollowerViewModel.destination.collectAsState()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            userFollowerViewModel.onCompleteNavigation()
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        userFollowerViewModel.onCreate(username)
    }

    UserFollowerTemplate(
        followers = uiState.followerList,
        relationship = uiState.relationship,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onClickFollow = userFollowerViewModel::onClickFollow,
        onClickUser = userFollowerViewModel::onClickUser,
        onRefresh = userFollowerViewModel::onRefresh,
        onClickNavIcon = userFollowerViewModel::onClickNavIcon
    )
}