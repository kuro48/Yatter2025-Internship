package com.dmm.bootcamp.yatter2025.ui.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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

class UserFollowViewModel(
    private val userRepository: UserRepository,
    private val getLoginUserService: GetLoginUserService,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserFollowUiState> = MutableStateFlow(UserFollowUiState.empty())
    val uiState: StateFlow<UserFollowUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    private fun fetchFollowings(me: User) {
        viewModelScope.launch {
            val followings = userRepository.followings(me)
            _uiState.update {
                it.copy(
                    followingList = UserConverter.convertToBindingModel(followings)
                )
            }
        }
    }

    fun onCreate() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val me = getLoginUserService.execute()
            if (me != null) {
                fetchFollowings(me)
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val me = getLoginUserService.execute()
            if (me != null) {
                fetchFollowings(me)
            }
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