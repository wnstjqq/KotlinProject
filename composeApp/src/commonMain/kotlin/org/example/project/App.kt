package org.example.project


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import kotlinproject.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

expect fun createRetrofit(): Any

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf("home") }

    AnimatedContent(targetState = currentScreen) { screen ->
        when (screen) {
            "home" -> Main(
                onBambooClick = { currentScreen = "bamboo" },
                onSearchEnter = { currentScreen = "searchResult" },
                onLiked = { currentScreen = "like" },
                onProfile= { currentScreen = "profile"},
                onSolid= { currentScreen = "solid"},
                onBar = { currentScreen = "Bar"},
                onCup = { currentScreen = "Cup"},
                onSusemi = { currentScreen = "Susemi"},
                onSilicon = { currentScreen = "Silicon"},
                onSmell = { currentScreen = "Smell"},
                onStainless = { currentScreen = "Stainless"},
                onTisue = { currentScreen = "Tisue"},
                onSoft = { currentScreen = "Soft"}
            )
            "Bar" -> Bar(onBackBarClick = { currentScreen = "home" })
            "Cup" -> Cup(onBackBarClick = { currentScreen = "home" })
            "Susemi" -> Susemi(onBackBarClick = { currentScreen = "home" })
            "Silicon" -> Silicon(onBackBarClick = { currentScreen = "home" })
            "Smell" -> Smell(onBackBarClick = { currentScreen = "home" })
            "Stainless" -> Stainless(onBackBarClick = { currentScreen = "home" })
            "Tisue" -> Tisue(onBackBarClick = { currentScreen = "home" })
            "Soft" -> Soft(onBackBarClick = { currentScreen = "home" })
            "bamboo" -> Bamboo(onBackBarClick = { currentScreen = "home" })
            "searchResult" -> SearchResultScreen(
                onBackBarClick = { currentScreen = "home" },
                onLiked = { currentScreen = "like" },
                onSolid = { currentScreen = "solid"}
            )
            "profile" -> MyHomeScreen(onHome = { currentScreen = "home"},
                onLiked = { currentScreen = "like"})
            "like" -> like(onHome = { currentScreen = "home" },
                onProfile= { currentScreen = "profile"})
            "solid" -> solid(onBackBarClick = { currentScreen = "searchResult" })
        }
    }
}

@Composable
fun Main(onBambooClick: () -> Unit,
        onSearchEnter: () -> Unit,
        onLiked: () -> Unit,
        onProfile: () -> Unit,
        onSolid: () -> Unit,
        onBar: () -> Unit,
        onCup: () -> Unit,
        onSusemi: () -> Unit,
        onSilicon: () -> Unit,
        onSmell: () -> Unit,
        onStainless: () -> Unit,
        onTisue: () -> Unit,
        onSoft: () -> Unit) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("", "", "")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Person)
    val unselectedIcons =
        listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Person)
    var isVisible by remember { mutableStateOf(true)}

    Box(modifier = Modifier.fillMaxSize().padding(bottom = 10.dp)) {
        // Main content on top of the NavigationBar
        Column(
            modifier = Modifier.fillMaxWidth(),//.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Ecompass", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),modifier = Modifier.padding(top = 10.dp))

                Spacer(modifier = Modifier.height(10.dp))

                // Search Bar
                SearchBar(
                    onSearchBarClick = {
                        isVisible = it.isEmpty()
                    },
                    onSearchEnter = {
                        if (it.isNotEmpty()) {
                            onSearchEnter()
                        }
                    }
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    initialOffsetY = { it }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it }
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Choose Green With Confidence", style = MaterialTheme.typography.bodyLarge)
                    LazyVerticalGrid( // 각 항목의 최소 너비를 120.dp로 설정
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        columns = GridCells.Adaptive(minSize = 120.dp),
                        contentPadding = PaddingValues(30.dp, 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(18.dp)// Item padding
                ) {
                    items(itemList.size) { itemIndex ->
                        if (itemIndex == 0)
                            ItemCard(itemList[itemIndex], onBambooClick)
                        else if (itemIndex == 1)
                            ItemCard(itemList[itemIndex], onSoft)
                        else if (itemIndex == 2)
                            ItemCard(itemList[itemIndex], onSusemi)
                        else if (itemIndex == 3)
                            ItemCard(itemList[itemIndex], onSolid)
                        else if (itemIndex == 4)
                            ItemCard(itemList[itemIndex], onBar)
                        else if (itemIndex == 5)
                            ItemCard(itemList[itemIndex], onSmell)
                        else if (itemIndex == 6)
                            ItemCard(itemList[itemIndex], onSilicon)
                        else if (itemIndex == 7)
                            ItemCard(itemList[itemIndex], onStainless)
                        else if (itemIndex == 8)
                            ItemCard(itemList[itemIndex], onCup)
                        else if (itemIndex == 9)
                            ItemCard(itemList[itemIndex], onTisue)
                    }
                }}
            }
            AnimatedVisibility(
                visible = !isVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(text = "최근 검색어", style = MaterialTheme.typography.bodyLarge)

            }
        }

        // NavigationBar as a layer on top, but at the bottom of the screen
        NavigationBar(
            containerColor = Color(0x80E7E6ED),
            modifier = Modifier
                .align(Alignment.BottomCenter)  // Padding for spacing
                .clip(RoundedCornerShape(50.dp)) // Round the corners of the bar
                .height(60.dp)
                .width(200.dp)// Set height
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center ,
                verticalAlignment =  Alignment.CenterVertically // Center align the items in the row
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = item,
                                modifier = Modifier.size(24.dp)// 아이콘 크기 조정
                            )
                        },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            // 하트(좋아요) 아이콘 클릭 시 like 화면으로 이동
                            if (index == 1) {  // 1은 하트 아이콘의 인덱스
                                onLiked()
                            }
                            else if(index == 2) {
                                onProfile()
                            }
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp)) // 둥근 모서리 적용
                            .padding(4.dp) // 아이템 간격 조정
                    )
                }
            }
        }
    }
}

data class ProductItem(
    val name: String,
    val rating: Double,
    val price: Int,
    val imageResource: DrawableResource,
    val tags: List<String> = emptyList()
)


@Composable
fun SearchResultScreen(
    onLiked: () -> Unit = {},
    onHome: () -> Unit ={},
    onBackBarClick: () -> Unit={},
    onSolid: () -> Unit={},
    modifier: Modifier = Modifier,
    searchText: String = "solid"
) {
    var currentScreen by remember { mutableStateOf("searchResult") }
    var selectedItem by remember { mutableIntStateOf(2) } // 1은 하트 아이콘의 인덱스
    val items = listOf("", "", "")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Person)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Person)
    Scaffold(
        bottomBar = {  },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Top Bar with back button and logo
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackBarClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }
                Text(
                    text = "Ecompass",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Search Bar
            SearchBar()

            // Search Result Text
            Text(
                text = "'$searchText'에 대한 검색 결과입니다",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Product List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleProducts.size) { index ->
                    ProductCard(sampleProducts[index], onSolid)
                }
            }
            }
            NavigationBar(
                containerColor = Color(0x80E7E6ED),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .height(60.dp)
                    .width(200.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                    contentDescription = item,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                if (index == 0)
                                    onHome()
                                else if (index == 1)// 하트 아이콘 클릭
                                    onLiked()
                                    // 2 -> onProfile() // 프로필 아이콘 클릭 (필요한 경우 추가)

                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun ProductCard(product: ProductItem, onSolid:() ->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2EDE4)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Product Image
                Image(
                    painter = painterResource(product.imageResource),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onSolid),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = product.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "rating",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFFFFD700)
                        )
                        Text(
                            text = "${product.rating}",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Tags
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        product.tags.forEach { tag ->
                            Chip(tag)
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${product.price}원",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}@Composable
fun Chip(text: String) {
    Surface(
        color = Color(0xFFE8F5E9),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF2E7D32),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}


// 샘플 데이터
val sampleProducts = listOf(
    ProductItem(
        name = "고체 샴푸바",
        rating = 4.84,
        price = 7600,
        imageResource = Res.drawable.soft,
        tags = listOf("친환경", "zero waste")
    ),
    ProductItem(
        name = "고체 치약",
        rating = 4.69,
        price = 9520,
        imageResource = Res.drawable.solid,
        tags = listOf("천연", "플라스틱 프리")
    )
)

@Composable
fun like(onHome: () -> Unit,
         onProfile: () -> Unit) {
    var selectedItem by remember { mutableIntStateOf(1) } // 1은 하트 아이콘의 인덱스
    val items = listOf("", "", "")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Person)
    val unselectedIcons = listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Person)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 70.dp) // 하단 네비게이션 바 공간 확보
        ) {
            // Top Bar with title only (removed back button)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ecompass",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Liked Items Section
            Text(
                text = "좋아요",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp).padding(start = 12.dp)
            )

            // Liked Items List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(likedProducts) { product ->
                    LikedProductCard(product)
                }
            }
        }

        // Bottom Navigation Bar
        NavigationBar(
            containerColor = Color(0x80E7E6ED),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(50.dp))
                .height(60.dp)
                .width(200.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = item,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when (index) {
                                0 -> onHome() // 하트 아이콘 클릭
                                2 -> onProfile() // 프로필 아이콘 클릭 (필요한 경우 추가)
                            }
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}
@Composable
private fun LikedProductCard(product: ProductItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2EDE4)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Product Image
                Image(
                    painter = painterResource(product.imageResource),
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = product.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "rating",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFFFFD700)
                        )
                        Text(
                            text = "${product.rating}",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Tags
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        product.tags.forEach { tag ->
                            Chip(tag)
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${product.price}원",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                IconButton(
                    onClick = { /* Remove from liked items */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Unlike",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
private val likedProducts = listOf(
    ProductItem(
        name = "고체 샴푸바",
        rating = 4.84,
        price = 7600,
        imageResource = Res.drawable.soft,
        tags = listOf("친환경", "zero waste")
    ),
    ProductItem(
        name = "고체 치약",
        rating = 4.69,
        price = 9520,
        imageResource = Res.drawable.solid,
        tags = listOf("천연", "플라스틱 프리")
    )
)

@Composable
fun CustomNavigationBar(
    items: List<String>,
    selectedIcons: List<String>,
    unselectedIcons: List<String>,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedItem: Int = 0
) {
    val backgroundColor = Color(0x80E7E6ED)
    val selectedItemColor = Color(0xFFA1B5D8)

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .height(60.dp)
            .width(200.dp),
        color = backgroundColor,
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, _ ->
                NavigationItem(
                    isSelected = selectedItem == index,
                    selectedText = selectedIcons[index],
                    unselectedText = unselectedIcons[index],
                    selectedBackgroundColor = selectedItemColor,
                    onItemClick = { onItemSelected(index) }
                )
            }
        }
    }
}

@Composable
private fun NavigationItem(
    isSelected: Boolean,
    selectedText: String,
    unselectedText: String,
    selectedBackgroundColor: Color,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(if (isSelected) selectedBackgroundColor else Color.Transparent)
            .clickable(onClick = onItemClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = if (isSelected) selectedText else unselectedText,
            color = if (isSelected) Color.White else Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }
}