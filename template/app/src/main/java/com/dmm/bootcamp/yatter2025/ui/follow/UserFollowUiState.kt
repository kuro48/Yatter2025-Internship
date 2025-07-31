package com.dmm.bootcamp.yatter2025.ui.follow

import com.dmm.bootcamp.yatter2025.ui.follow.bindingmodel.UserFollowBindingModel

data class UserFollowUiState(
    val followingList: List<UserFollowBindingModel>,
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
