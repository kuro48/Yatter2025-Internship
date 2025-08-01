package com.dmm.bootcamp.yatter2025.ui.follow

import com.dmm.bootcamp.yatter2025.domain.model.User
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.RelationshipBindingModel
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel

data class UserFollowUiState(
    val targetUser: UserBindingModel,
    val followingList: List<UserBindingModel>,
    val relationship: RelationshipBindingModel,
    val isLoading: Boolean,
    val isRefreshing: Boolean
) {
    companion object {
        fun empty(): UserFollowUiState = UserFollowUiState(
            targetUser = UserBindingModel(
                username = "",
                displayName = "",
                note = "",
                avatar = "",
                header = "",
                followingCount = 0,
                followerCount = 0,
            ),
            followingList = emptyList(),
            relationship = RelationshipBindingModel(
                target = "",
                following = false,
                followedBy = false,
            ),
            isLoading = false,
            isRefreshing = false,
        )
    }
}
