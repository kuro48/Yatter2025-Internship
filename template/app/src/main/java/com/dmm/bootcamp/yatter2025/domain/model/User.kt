package com.dmm.bootcamp.yatter2025.domain.model

import com.dmm.bootcamp.yatter2025.common.ddd.Entity
import java.net.URL

data class User(
  override val id: UserId,
  val username: Username,
  val displayName: String?,
  val note: String?,
  val avatar: URL?,
  val header: URL?,
  val followingCount: Int,
  val followerCount: Int,
) : Entity<UserId>(id)
