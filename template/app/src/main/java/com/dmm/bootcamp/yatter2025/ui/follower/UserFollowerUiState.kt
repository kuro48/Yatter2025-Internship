package com.dmm.bootcamp.yatter2025.ui.follower

import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.RelationshipBindingModel
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel

data class UserFollowerUiState(
    val targetUser: UserBindingModel,
    val followerList: List<UserBindingModel>,
    val relationship: RelationshipBindingModel,
    val isLoading: Boolean,
    val isRefreshing: Boolean
) {
    companion object {
        fun empty(): UserFollowerUiState = UserFollowerUiState(
            targetUser = UserBindingModel(
                username = "",
                displayName = "",
                note = "",
                avatar = "",
                header = "",
                followingCount = 0,
                followerCount = 0,
            ),
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
