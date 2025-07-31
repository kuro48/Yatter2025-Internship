package com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel

data class RelationshipBindingModel(
    val target: String,
    val following: Boolean,
    val followedBy: Boolean,
)
