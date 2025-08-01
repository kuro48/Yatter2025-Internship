package com.dmm.bootcamp.yatter2025.ui.follow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.dmm.bootcamp.yatter2025.ui.LocalNavController

@Composable
fun UserFollowPage(
    userFollowViewModel: UserFollowViewModel
) {
    val uiState by userFollowViewModel.uiState.collectAsState()
    val destination by userFollowViewModel.destination.collectAsState()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            userFollowViewModel.onCompleteNavigation()
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        userFollowViewModel.onCreate()
    }

    UserFollowTemplate(
        followings = uiState.followingList,
        relationship = uiState.relationship,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onClickFollow = userFollowViewModel::onClickFollow,
        onClickUser = userFollowViewModel::onClickUser,
        onRefresh = userFollowViewModel::onRefresh
    )
}