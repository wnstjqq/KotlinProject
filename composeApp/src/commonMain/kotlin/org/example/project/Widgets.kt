package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.chop
import kotlinproject.composeapp.generated.resources.soft
import kotlinproject.composeapp.generated.resources.susemi
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource





@Composable
fun ItemCard(product: ProductItem, onNavigate: () -> Unit) {
    Card(
        modifier = Modifier
            .size(width = 140.dp, height = 170.dp)
            .padding(10.dp)
            .clickable(onClick = onNavigate),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2EDE4))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(resource = product.imageResource),
                contentDescription = product.name,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "rating",
                    modifier = Modifier.size(14.dp),
                    tint = Color(0xFF000000)
                )
                Text(
                    text = "${product.rating}",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 2.dp)
                )
                Text(
                    text = "${product.price}원",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 30.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}


data class Product(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Float,
    val description: String,
    val url: String,
    val rating: Float
)

val itemList = listOf(
    ProductItem(
        name = "대나무 칫솔",
        rating = 4.85,
        price = 1000,
        imageResource = Res.drawable.chop,
    ),
    ProductItem(
        name = "고체 샴푸바",
        rating = 4.84,
        price = 7600,
        imageResource = Res.drawable.soft
    ),
    ProductItem(
        name = "천연 수세미",
        rating = 4.82,
        price = 2300,
        imageResource = Res.drawable.susemi
    ),
    ProductItem(
        name = "고체 치약",
        rating = 4.69,
        price = 9520,
        imageResource = Res.drawable.solid
    ),
    ProductItem(
        name = "설거지바",
        rating = 4.82,
        price = 2300,
        imageResource = Res.drawable.bar
    ),
    ProductItem(
        name = "서랍장 냄세 제거 탈취제",
        rating = 4.55,
        price = 36400,
        imageResource = Res.drawable.smell
    ),
    ProductItem(
        name = "실리콘 지퍼백",
        rating = 4.43,
        price = 15900,
        imageResource = Res.drawable.silicon
    ),
    ProductItem(
        name = "스테인리스 빨대",
        rating = 4.8,
        price = 390,
        imageResource = Res.drawable.stainless
    ),
    ProductItem(
        name = "친환경 종이컵",
        rating = 4.89,
        price = 27000,
        imageResource = Res.drawable.cup
    ),
    ProductItem(
        name = "친환경 물티슈",
        rating = 4.86,
        price = 14300,
        imageResource = Res.drawable.tisue
    )

)

