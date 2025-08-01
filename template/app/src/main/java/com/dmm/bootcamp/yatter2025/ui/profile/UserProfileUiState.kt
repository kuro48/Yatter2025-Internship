package com.dmm.bootcamp.yatter2025.ui.profile

import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.RelationshipBindingModel
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel

data class UserProfileUiState(
    val userBindingModel: UserBindingModel,
    val relationshipBindingModel: RelationshipBindingModel,
    val isLoading: Boolean,
    val isRefreshing: Boolean,
) {
    companion object {
        fun empty(): UserProfileUiState = UserProfileUiState(
            userBindingModel = UserBindingModel(
                username = "",
                displayName = "",
                note = null,
                avatar = null,
                header = null,
                followingCount = 0,
                followerCount = 0,
            ),
            relationshipBindingModel = RelationshipBindingModel(
                target = "",
                following = false,
                followedBy = false
            ),
            isLoading = false,
            isRefreshing = false,
        )
    }
}