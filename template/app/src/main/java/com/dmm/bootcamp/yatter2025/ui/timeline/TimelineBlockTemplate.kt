package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimelineBlockTemplate(
    yweetList: List<YweetBindingModel>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onClickPost: () -> Unit,
    onClickYweet: (yweetId: String) -> Unit,
    onClickAvater: (username: String) -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(yweetList) { item ->
                YweetRow(yweetBindingModel = item, onClickYweet = onClickYweet, onClickAvatar = onClickAvater)
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
private fun TimelineBlockTemplatePreview() {
    Yatter2025Theme {
        Surface {
            TimelineBlockTemplate(
                yweetList = listOf(
                    YweetBindingModel(
                        id = "id1",
                        displayName = "display name1",
                        username = "username1",
                        avatar = null,
                        content = "preview content1",
                        attachmentImageList = listOf()
                    ),
                    YweetBindingModel(
                        id = "id2",
                        displayName = "display name2",
                        username = "username2",
                        avatar = null,
                        content = "preview content2",
                        attachmentImageList = listOf()
                    ),
                    YweetBindingModel(
                        id = "id3",
                        displayName = "display name3",
                        username = "username3",
                        avatar = null,
                        content = "preview content3",
                        attachmentImageList = listOf()
                    ),
                ),
                isLoading = true,
                isRefreshing = false,
                onRefresh = {},
                onClickPost = {},
                onClickYweet = {},
                onClickAvater = {},
            )
        }
    }
}