package com.dmm.bootcamp.yatter2025.ui.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.common.navigation.PopBackDestination
import com.dmm.bootcamp.yatter2025.domain.model.User
import com.dmm.bootcamp.yatter2025.domain.model.Username
import com.dmm.bootcamp.yatter2025.domain.repository.UserRepository
import com.dmm.bootcamp.yatter2025.domain.service.GetLoginUserService
import com.dmm.bootcamp.yatter2025.ui.profile.UserProfileDestination
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserFollowerViewModel(
    private val userRepository: UserRepository,
    private val getLoginUserService: GetLoginUserService,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserFollowerUiState> = MutableStateFlow(UserFollowerUiState.empty())
    val uiState: StateFlow<UserFollowerUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    private fun fetchFollowers(username: String) {
        viewModelScope.launch {
            val followers = userRepository.followers(username)
            _uiState.update {
                it.copy(
                    followerList = UserConverter.convertToBindingModel(followers)
                )
            }
        }
    }

    fun onCreate(username: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val targetUser = userRepository.findByUsername(Username(username),true)
            if (targetUser != null) {
                _uiState.update {
                    it.copy(
                        targetUser = UserConverter.convertToBindingModel(targetUser)
                    )
                }
                fetchFollowers(targetUser.username.value)
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            fetchFollowers(uiState.value.targetUser.username)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickFollow() {
        viewModelScope.launch {
            val me = getLoginUserService.execute()
            _uiState.update { it.copy(isLoading = true) }
            if (me != null) {
//                userRepository.follow(me, Username())
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickUnfollow(username: String) {
        viewModelScope.launch {
            val me = getLoginUserService.execute()
            _uiState.update { it.copy(isLoading = true) }
            if (me != null) {
                userRepository.unfollow(me, Username(username))
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickUser(username: String) {
        _destination.value = UserProfileDestination(username)
    }

    fun onClickNavIcon() {
        _destination.value = PopBackDestination
    }

    fun onCompleteNavigation() {
        _destination.value = null
    }
}