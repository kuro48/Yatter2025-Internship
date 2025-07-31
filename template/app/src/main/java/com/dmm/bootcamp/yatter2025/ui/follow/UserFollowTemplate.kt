package com.dmm.bootcamp.yatter2025.ui.follow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.RelationshipBindingModel
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserFollowTemplate(
    followings: List<UserBindingModel>,
    relationship: RelationshipBindingModel,
    onClickFollow: () -> Unit,
    onClickUser: () -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("フォロー一覧")
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
        LazyColumn {
            items(followings) { item ->
                FollowRow(
                    userBindingModel = item,
                    relationshipBindingModel = relationship,
                    onClickFollow = {},
                    onClickUser = {},
                )
            }
        }
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
    }
    }
}

@Preview
@Composable
fun UserFollowTemplatePreview() {
    Yatter2025Theme {
        Surface {
            UserFollowTemplate(
                followings = listOf(
                    UserBindingModel(
                        username = "username",
                        displayName = "displayname",
                        note = "note",
                        avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                        header = "https://avatars.githubusercontent.com/u/19385268?v=4",
                        followingCount = 0,
                        followerCount = 0,
                    ),
                    UserBindingModel(
                        username = "username",
                        displayName = "displayname",
                        note = "note",
                        avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                        header = "https://avatars.githubusercontent.com/u/19385268?v=4",
                        followingCount = 0,
                        followerCount = 0,
                    ),
                    UserBindingModel(
                        username = "username",
                        displayName = "displayname",
                        note = "note",
                        avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                        header = "https://avatars.githubusercontent.com/u/19385268?v=4",
                        followingCount = 0,
                        followerCount = 0,
                    )
                ),
                relationship = RelationshipBindingModel(
                    target = "username",
                    following = true,
                    followedBy = false,
                ),
                onClickFollow = {},
                onClickUser = {},
                onRefresh = {},
                isRefreshing = false,
            )
        }
    }
}