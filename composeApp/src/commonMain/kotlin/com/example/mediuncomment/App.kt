package com.example.mediuncomment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mediuncomment.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource
import mediuncomment.composeapp.generated.resources.bg_video
import mediuncomment.composeapp.generated.resources.ic_mobile_signal
import mediuncomment.composeapp.generated.resources.ic_wifi
import mediuncomment.composeapp.generated.resources.ic_battery
import mediuncomment.composeapp.generated.resources.ic_header_divider
import mediuncomment.composeapp.generated.resources.ic_close
import mediuncomment.composeapp.generated.resources.ic_chevron_up
import mediuncomment.composeapp.generated.resources.ic_heart
import mediuncomment.composeapp.generated.resources.ic_ad_sign
import mediuncomment.composeapp.generated.resources.ic_emoji
import mediuncomment.composeapp.generated.resources.ic_tick
import mediuncomment.composeapp.generated.resources.avatar_martini_rond
import mediuncomment.composeapp.generated.resources.avatar_maxjacobson
import mediuncomment.composeapp.generated.resources.avatar_zackjohn
import mediuncomment.composeapp.generated.resources.avatar_kiero_d
import mediuncomment.composeapp.generated.resources.avatar_mis_potter
import mediuncomment.composeapp.generated.resources.avatar_karennne
import mediuncomment.composeapp.generated.resources.avatar_joshua_l

@Composable
@Preview
fun App() {
    MaterialTheme {
        TikTokCommentsScreen()
    }
}

@Composable
private fun TikTokCommentsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.bg_video),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        StatusBar(modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(horizontal = 16.dp)
            .align(Alignment.TopCenter))

        Spacer(Modifier.height(6.dp))

        Header(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 44.dp)
            .align(Alignment.TopCenter))

        CommentsSheet(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter))

        BottomInputBar(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter))
    }
}

@Composable
private fun StatusBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Time left aligned
        Text(
            text = "9:41",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        // Indicators right aligned
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(Res.drawable.ic_mobile_signal),
                contentDescription = null,
                modifier = Modifier.height(11.dp)
            )
            Spacer(Modifier.width(6.dp))
            Image(
                painterResource(Res.drawable.ic_wifi),
                contentDescription = null,
                modifier = Modifier.height(11.dp)
            )
            Spacer(Modifier.width(6.dp))
            Image(
                painterResource(Res.drawable.ic_battery),
                contentDescription = null,
                modifier = Modifier.height(10.5.dp)
            )
        }
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Text("Following", color = Color.White.copy(alpha = 0.6f), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.width(12.dp))
        Image(painterResource(Res.drawable.ic_header_divider), contentDescription = null, modifier = Modifier.height(13.dp))
        Spacer(Modifier.width(12.dp))
        Text("For You", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}

private val SheetBackground = Color(0xFFF5F5F4)
private val TextPrimary = Color(0xFF161722)
private val TextSecondary = Color(0xFF86878B)

@Composable
private fun CommentsSheet(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(571.dp)
            .background(SheetBackground, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .padding(top = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text(
                text = "579 comments",
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Center)
            )
            Image(
                painterResource(Res.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier.size(16.dp).align(Alignment.CenterEnd)
            )
        }

        val comments = remember { sampleComments() }
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 82.dp)) {
            items(comments) { item ->
                CommentRow(item)
            }
        }
    }
}

data class CommentItem(
    val avatarRes: DrawableResource,
    val username: String,
    val message: String,
    val time: String,
    val likes: String,
    val repliesText: String?,
    val verified: Boolean = false
)

@Composable
private fun CommentRow(item: CommentItem) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)) {
        Image(
            painterResource(item.avatarRes),
            contentDescription = null,
            modifier = Modifier.size(32.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(item.username, color = TextSecondary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                if (item.verified) {
                    Spacer(Modifier.width(4.dp))
                    Image(painterResource(Res.drawable.ic_tick), contentDescription = null, modifier = Modifier.size(10.dp))
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = buildAnnotatedString {
                    append(item.message)
                    append(" ")
                    withStyle(
                        SpanStyle(
                            color = Color(0xFF86878B),
                            fontSize = 13.sp
                        )
                    ) {
                        append(item.time)
                    }
                },
                fontSize = 15.sp
            )
            if (item.repliesText != null) {
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(item.repliesText, color = TextSecondary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(4.dp))
                    Image(painterResource(Res.drawable.ic_chevron_up), contentDescription = null, modifier = Modifier.size(10.dp))
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(Res.drawable.ic_heart), contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(Modifier.height(6.dp))
            Text(item.likes, color = TextSecondary, fontSize = 13.sp)
        }
    }
}

@Composable
private fun BottomInputBar(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        // home indicator
        Spacer(Modifier.height(6.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier
                .padding(bottom = 2.dp)
                .width(134.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xFF060606)))
        }
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text("Add comment...", color = TextSecondary, fontSize = 15.sp, modifier = Modifier.weight(1f))
                Image(painterResource(Res.drawable.ic_ad_sign), contentDescription = null, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(12.dp))
                Image(painterResource(Res.drawable.ic_emoji), contentDescription = null, modifier = Modifier.size(22.dp))
            }
        }
    }
}

private fun sampleComments(): List<CommentItem> = listOf(
    CommentItem(
        avatarRes = Res.drawable.avatar_martini_rond,
        username = "martini_rond",
        message = "How neatly I write the date in my book",
        time = "22h",
        likes = "8098",
        repliesText = "View replies (4)"
    ),
    CommentItem(
        avatarRes = Res.drawable.avatar_maxjacobson,
        username = "maxjacobson",
        message = "Now that’s a skill very talented",
        time = "22h",
        likes = "8098",
        repliesText = "View replies (1)"
    ),
    CommentItem(
        avatarRes = Res.drawable.avatar_zackjohn,
        username = "zackjohn",
        message = "Doing this would make me so anxious",
        time = "22h",
        likes = "8098",
        repliesText = null
    ),
    CommentItem(
        avatarRes = Res.drawable.avatar_kiero_d,
        username = "kiero_d",
        message = "Use that on r air forces to whiten them",
        time = "21h",
        likes = "8098",
        repliesText = "View replies (9)"
    ),
    CommentItem(
        avatarRes = Res.drawable.avatar_mis_potter,
        username = "mis_potter",
        message = "Sjpuld’ve used that on his forces 😷😷",
        time = "13h",
        likes = "8098",
        repliesText = "View replies (4)",
        verified = true
    ),
    CommentItem(
        avatarRes = Res.drawable.avatar_karennne,
        username = "karennne",
        message = "No prressure",
        time = "22h",
        likes = "8098",
        repliesText = "View replies (2)"
    ),
    CommentItem(
        avatarRes = Res.drawable.avatar_joshua_l,
        username = "joshua_l",
        message = "My OCD couldn’t do it",
        time = "15h",
        likes = "8098",
        repliesText = null
    )
)