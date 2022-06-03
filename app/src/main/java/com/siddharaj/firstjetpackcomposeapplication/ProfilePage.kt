package com.siddharaj.firstjetpackcomposeapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun ProfilePage() {
    Card(
        elevation = 6.dp, modifier = Modifier
            .padding(top = 100.dp, bottom = 80.dp, start = 16.dp, end = 16.dp)
            .border(
                2.dp,
                Color.White, shape = RoundedCornerShape(30.dp)
            )
    ) {
        BoxWithConstraints() {
            val constraints = if (minWidth < 600.dp) {
                portraitConstraints(16.dp)
            } else {
               landscapeConstraintSet(16.dp)

            }
            ConstraintLayout(constraints) {
                Image(
                    painter = painterResource(id = R.drawable._5365),
                    contentDescription = "android",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(
                            CircleShape
                        )
                        .border(width = 2.dp, color = Color.Red, shape = CircleShape)
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Spiderman",
                    Modifier.layoutId("nameText"),
                    fontWeight = FontWeight.Bold
                )
                Text(text = "New York", Modifier.layoutId("countryText"))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStats")

                ) {
                    ProfileStats(count = "150", title = "Followers")
                    ProfileStats(count = "100", title = "Followings")
                    ProfileStats(count = "30", title = "Post")
                }

                Button(onClick = { /*TODO*/ }, Modifier.layoutId("buttonFollow")) {
                    Text(text = "Follow user")
                }
                Button(onClick = { /*TODO*/ }, Modifier.layoutId("buttonMessage")) {
                    Text(text = "Direct Meesage")
                }

            }
        }

    }
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

private fun portraitConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rowStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonMessage = createRefFor("buttonMessage")
        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(countryText) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats) {
            top.linkTo(countryText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(buttonFollow) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(buttonMessage.start)
            width = Dimension.wrapContent
        }
        constrain(buttonMessage) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }

    }
}

private fun landscapeConstraintSet(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rowStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonMessage = createRefFor("buttonMessage")
        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start,margin)
        }
        constrain(nameText){
            top.linkTo(image.bottom,margin)
            start.linkTo(image.start,margin)

        }
        constrain(countryText){
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }
        constrain(rowStats){
            top.linkTo(image.top)
            start.linkTo(image.end,margin)
            end.linkTo(parent.end)
        }
        constrain(buttonFollow){
          top.linkTo(rowStats.bottom,margin)
            start.linkTo(rowStats.start)
            end.linkTo(buttonMessage.start)
            bottom.linkTo(countryText.bottom)
            width= Dimension.wrapContent
        }
        constrain(buttonMessage){
            top.linkTo(rowStats.bottom,margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(countryText.bottom)
            width= Dimension.wrapContent
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}