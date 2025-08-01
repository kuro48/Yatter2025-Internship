package com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel

import com.dmm.bootcamp.yatter2025.domain.model.Relationship
import com.dmm.bootcamp.yatter2025.domain.model.User
import com.dmm.bootcamp.yatter2025.domain.model.Yweet
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

object RelationshipConverter {
    fun convertToBindingModel(relationship: Relationship): RelationshipBindingModel = RelationshipBindingModel(
        target = relationship.target.value,
        following = relationship.following,
        followedBy = relationship.followedBy,
    )
}