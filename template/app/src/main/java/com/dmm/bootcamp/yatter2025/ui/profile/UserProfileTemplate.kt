package com.dmm.bootcamp.yatter2025.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.RelationshipBindingModel
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserProfileTemplate(
    userBindingModel: UserBindingModel,
    relationshipBindingModel: RelationshipBindingModel,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onClickFollow: () -> Unit,
    onClickUnFollow: () -> Unit,
    onClickNavIcon: () -> Unit,
    onRefresh: () -> Unit,
    onClickFollowings: (username: String) -> Unit,
    onClickFollowers: (username: String) -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("プロフィール")
                },
                navigationIcon = {
                    IconButton(onClick = onClickNavIcon) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る")
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                model = userBindingModel.header,
                contentScale = ContentScale.Crop,
                contentDescription = "ヘッダー画像"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, start = 8.dp, end = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(64.dp),
                        model = userBindingModel.avatar,
                        contentDescription = "アバター画像",
                        contentScale = ContentScale.Crop
                    )
                    if (relationshipBindingModel.following) {
                        Button(
                            onClick = onClickUnFollow,
                            shape = RoundedCornerShape(50),
                            border = BorderStroke(3.dp, MaterialTheme.colors.primary),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colors.primary
                            )
                        ) {
                            Text(
                                text = "フォロー中",
                                color = MaterialTheme.colors.primary
                            )
                        }
                    } else {
                        Button(
                            onClick = onClickFollow,
                            shape = RoundedCornerShape(50),
                        ) {
                            Text(text = "フォロー")
                        }
                    }
                }
                Text(
                    text = userBindingModel.displayName ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h4.fontSize
                )
                Row {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium)
                                )
                            ) {
                                append("@${userBindingModel.username}")
                            }
                        },
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 8.dp, end = 4.dp)
                    )
                    if (relationshipBindingModel.followedBy) {
                        Text(
                            text = "フォローされています",
                            fontSize = 11.sp,
                            color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 4.dp)
                        )
                    }
                }
                Text(
                    text = userBindingModel.note ?: "",
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = MaterialTheme.typography.body1.fontSize,
                                    fontWeight = FontWeight.Bold
                                )
                            ){
                                append(userBindingModel.followingCount.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                                    fontSize = MaterialTheme.typography.caption.fontSize
                                )
                            ){
                                append("フォロー中")
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                onClickFollowings(userBindingModel.username)
                            }
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = MaterialTheme.typography.body1.fontSize,
                                    fontWeight = FontWeight.Bold
                                )
                            ){
                                append(userBindingModel.followerCount.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                                    fontSize = MaterialTheme.typography.caption.fontSize
                                )
                            ){
                                append("フォロワー")
                            }
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                onClickFollowers(userBindingModel.username)
                            }
                    )
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

}

@Preview
@Composable
fun UserProfileTemplatePreview() {
    Yatter2025Theme {
        Surface {
            UserProfileTemplate(
                userBindingModel = UserBindingModel(
                    username = "KURO__48",
                    displayName = "KURO",
                    note = "Tokyo→Rits 情理 SN Swift書いてます 動画編集してます ドラムも少し叩けます",
                    avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    header = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    followingCount = 0,
                    followerCount = 0,
                ),
                relationshipBindingModel = RelationshipBindingModel(
                    target = "",
                    following = true,
                    followedBy = false,
                ),
                isLoading = false,
                isRefreshing = false,
                onClickNavIcon = {},
                onClickFollowings = {},
                onClickFollowers = {},
                onClickFollow = {},
                onRefresh = {},
                onClickUnFollow = {}
            )
        }
    }
}