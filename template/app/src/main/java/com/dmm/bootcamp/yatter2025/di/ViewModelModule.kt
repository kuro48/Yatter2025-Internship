package com.dmm.bootcamp.yatter2025.di

import com.dmm.bootcamp.yatter2025.ui.MainViewModel
import com.dmm.bootcamp.yatter2025.ui.follow.UserFollowViewModel
import com.dmm.bootcamp.yatter2025.ui.follower.UserFollowerViewModel
import com.dmm.bootcamp.yatter2025.ui.login.LoginViewModel
import com.dmm.bootcamp.yatter2025.ui.post.PostViewModel
import com.dmm.bootcamp.yatter2025.ui.profile.UserProfileViewModel
import com.dmm.bootcamp.yatter2025.ui.register.RegisterUserViewModel
import com.dmm.bootcamp.yatter2025.ui.timeline.postdetail.PostDetailViewModel
import com.dmm.bootcamp.yatter2025.ui.timeline.hometimeline.HomeTimelineViewModel
import com.dmm.bootcamp.yatter2025.ui.timeline.publictimeline.PublicTimelineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
  viewModel { MainViewModel(get()) }
  viewModel { PublicTimelineViewModel(get()) }
  viewModel { PostViewModel(get(), get()) }
  viewModel { RegisterUserViewModel(get()) }
  viewModel { LoginViewModel(get()) }
  viewModel { HomeTimelineViewModel(get())}
  viewModel { PostDetailViewModel(get()) }
  viewModel { UserProfileViewModel(get()) }
  viewModel { UserFollowViewModel(get(), get()) }
  viewModel { UserFollowerViewModel(get(), get()) }
}
