package com.dmm.bootcamp.yatter2025.ui.follow

import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel

data class UserFollowUiState(
    val followingList: List<UserBindingModel>,
    val isLoading: Boolean,
    val isRefreshing: Boolean
) {
    companion object {
        fun empty(): UserFollowUiState = UserFollowUiState(
            followingList = emptyList(),
            isLoading = false,
            isRefreshing = false,
        )
    }
}
