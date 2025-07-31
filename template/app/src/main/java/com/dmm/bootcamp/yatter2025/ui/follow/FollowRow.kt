package com.dmm.bootcamp.yatter2025.ui.follow

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2025.ui.profile.UserProfileTemplate
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.RelationshipBindingModel
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme

@Composable
fun FollowRow(
    userBindingModel: UserBindingModel,
    relationshipBindingModel: RelationshipBindingModel,
    onClickUser: () -> Unit,
    onClickFollow: () -> Unit,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding( 4.dp)
        .clickable {
            onClickFollow()
        }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp),
            model = userBindingModel.avatar,
            contentDescription = "アバター画像",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = userBindingModel.displayName ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.h5.fontSize
                    )
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
                Button(
                    onClick = onClickFollow,
                    shape = RoundedCornerShape(50),
                ) {
                    if (relationshipBindingModel.following) {
                        Text(text = "フォロー中")
                    } else {
                        Text(text = "フォロー")
                    }
                }
            }
            Text(
                text = userBindingModel.note ?: "",
                fontSize = MaterialTheme.typography.body1.fontSize
            )
        }
    }
}

@Preview
@Composable
fun FollowRowPreview() {
    Yatter2025Theme {
        Surface {
            FollowRow(
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
                    target = "KURO__48",
                    following = true,
                    followedBy = false,
                ),
                onClickFollow = {},
                onClickUser = {},
            )
        }
    }
}