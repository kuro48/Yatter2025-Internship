package com.dmm.bootcamp.yatter2025.infra.domain.repository

import android.util.Log
import com.dmm.bootcamp.yatter2025.auth.TokenProvider
import com.dmm.bootcamp.yatter2025.domain.model.User
import com.dmm.bootcamp.yatter2025.domain.model.Password
import com.dmm.bootcamp.yatter2025.domain.model.Username
import com.dmm.bootcamp.yatter2025.domain.repository.UserRepository
import com.dmm.bootcamp.yatter2025.domain.service.GetLoginUsernameService
import com.dmm.bootcamp.yatter2025.infra.api.YatterApi
import com.dmm.bootcamp.yatter2025.infra.api.json.CreateUserJson
import com.dmm.bootcamp.yatter2025.infra.domain.converter.UserConverter
import com.dmm.bootcamp.yatter2025.infra.pref.LoginUserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.URL

class UserRepositoryImpl(
  private val yatterApi: YatterApi,
  private val getLoginUsernameService: GetLoginUsernameService,
  private val tokenProvider: TokenProvider,
) : UserRepository {
  private val userCache: MutableMap<Username, User> = mutableMapOf()

  override suspend fun create(
    username: Username,
    password: Password
  ): User = withContext(Dispatchers.IO) {
    val userJson = yatterApi.createNewUser(
      CreateUserJson(
        username = username.value,
        password = password.value
      )
    )
    UserConverter.convertToDomainModel(userJson)
  }

  override suspend fun findLoginUser(disableCache: Boolean): User? = withContext(Dispatchers.IO) {
    val username = getLoginUsernameService.execute() ?: return@withContext null
    findByUsername(username = username, disableCache = disableCache)
  }

  override suspend fun findByUsername(
    username: Username,
    disableCache: Boolean,
  ): User? = withContext(Dispatchers.IO) {
    if (!disableCache) {
      userCache[username]?.let {
        // キャッシュに存在する場合はキャッシュを返す
        return@withContext it
      }
    }
    try {
      val userJson = yatterApi.getUserByUsername(username = username.value)
      val user = UserConverter.convertToDomainModel(userJson)
      userCache[username] = user
      return@withContext user
    } catch (e: HttpException) {
      Log.d("UserRepositoryImpl", "HTTP error: ${e.code()} message:${e.message()}")
      null
    } catch (e: Exception) {
      Log.d("UserRepositoryImpl", "Error: ${e.message}")
      null
    }
  }

  override suspend fun update(
    me: User,
    newDisplayName: String?,
    newNote: String?,
    newAvatar: URL?,
    newHeader: URL?
  ): User {
    TODO("Not yet implemented")
  }

  override suspend fun followings(me: User): List<User> {
    val followings = yatterApi.getFollowings(me.username.value)
    return  followings.map{ UserConverter.convertToDomainModel(it) }
  }

  override suspend fun followers(me: User): List<User> {
    val followings = yatterApi.getFollowers(me.username.value)
    return  followings.map{ UserConverter.convertToDomainModel(it) }
  }

  override suspend fun follow(me: User, username: Username) {
    yatterApi.postFollow(
      tokenProvider.provide(),
      username.value
    )
  }

  override suspend fun unfollow(me: User, username: Username) {
    yatterApi.postUnFollow(
      tokenProvider.provide(),
      username.value
    )
  }
}
