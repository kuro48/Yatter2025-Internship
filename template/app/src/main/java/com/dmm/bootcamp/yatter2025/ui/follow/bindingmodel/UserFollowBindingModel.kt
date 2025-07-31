package com.dmm.bootcamp.yatter2025.ui.follow.bindingmodel

data class UserFollowBindingModel(
    val id: String,
    val displayName: String,
    val username: String,
    val avatar: String?,
    val note: String,
    val isFollowed: Boolean,
)
