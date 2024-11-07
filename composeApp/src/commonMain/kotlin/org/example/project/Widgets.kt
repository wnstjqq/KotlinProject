package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.chop
import kotlinproject.composeapp.generated.resources.soft
import kotlinproject.composeapp.generated.resources.susemi
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource





@Composable
fun ItemCard(title: String, image: DrawableResource, onNavigate: () -> Unit) {
    Card(
        modifier = Modifier
            .height(170.dp)
            .width(140.dp)// Set the height of the card
            .padding(top = 10.dp), // Add top padding to ensure the image doesn't overlap the card
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2EDE4))
    ) {
        // Card Content
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
            painter = painterResource(resource = image),
            contentDescription = title,
            modifier = Modifier
                .clickable(onClick = onNavigate)
                .size(120.dp)
                .padding(top = 10.dp,start=20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        )
            Text(text = title, style = MaterialTheme.typography.bodySmall, modifier = Modifier.align(Alignment.Start).size(90.dp).padding(start = 20.dp))
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


val itemList = listOf(
    Pair("대나무 칫솔", Res.drawable.chop),
    Pair("고체 샴푸바", Res.drawable.soft),
    Pair("천연 수세미", Res.drawable.susemi),
    Pair("고체 치약", Res.drawable.solid),
    Pair("설거지 바", Res.drawable.bar) ,
    Pair("서랍장 냄새 제거 탈취제", Res.drawable.smell) ,
    Pair("살라콘 지퍼백", Res.drawable.silicon),
    Pair("스테인리스 빨대", Res.drawable.stainless),
    Pair("친환경 종이컵", Res.drawable.cup),
    Pair("친환경 물티슈", Res.drawable.tisue)

)
