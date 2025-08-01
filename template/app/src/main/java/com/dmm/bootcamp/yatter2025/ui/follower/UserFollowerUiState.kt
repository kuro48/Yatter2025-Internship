package com.dmm.bootcamp.yatter2025.ui.follower

import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.RelationshipBindingModel
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel

data class UserFollowerUiState(
    val followerList: List<UserBindingModel>,
    val relationship: RelationshipBindingModel,
    val isLoading: Boolean,
    val isRefreshing: Boolean
) {
    companion object {
        fun empty(): UserFollowerUiState = UserFollowerUiState(
            followerList = emptyList(),
            relationship = RelationshipBindingModel(
                target = "",
                following = false,
                followedBy = true,
            ),
            isLoading = false,
            isRefreshing = false,
        )
    }
}
