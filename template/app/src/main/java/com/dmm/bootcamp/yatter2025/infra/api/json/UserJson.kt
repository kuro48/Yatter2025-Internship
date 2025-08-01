package com.dmm.bootcamp.yatter2025.infra.api.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserJson(
  @Json(name = "id") val id: String,
  @Json(name = "username") val username: String,
  @Json(name = "display_name") val displayName: String?,
  @Json(name = "note") val note: String?,
  @Json(name = "avatar") val avatar: String?,
  @Json(name = "header") val header: String?,
  @Json(name = "following_count") val followingCount: Int,
  @Json(name = "followers_count") val followersCount: Int,
  @Json(name = "created_at") val createdAt: String
)
