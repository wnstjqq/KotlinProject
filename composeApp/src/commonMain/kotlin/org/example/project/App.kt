package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import kotlinproject.composeapp.generated.resources.*



@Composable
fun AnimatedScreenTransition() {
    var showScreen1 by remember { mutableStateOf(true) }

    Box(contentAlignment = Alignment.Center) {
        // 첫 번째 화면
        AnimatedVisibility(
            visible = showScreen1,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            App {
                showScreen1 = false // 클릭 시 두 번째 화면으로 전환
            }
        }

        // 두 번째 화면
        AnimatedVisibility(
            visible = !showScreen1,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Bamboo {
                showScreen1 = true // 클릭 시 첫 번째 화면으로 돌아가기
            }
        }
    }
}

@Composable
fun App(onNavigate: () -> Unit) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("", "", "")
    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Person)
    val unselectedIcons =
        listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Person)
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content on top of the NavigationBar
        Column(
            modifier = Modifier.fillMaxWidth(),//.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar()

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "Choose Green With Confidence", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid( // 각 항목의 최소 너비를 120.dp로 설정
                modifier = Modifier.fillMaxWidth().padding(8.dp) ,
                columns = GridCells.Adaptive(minSize = 120.dp),
                contentPadding = PaddingValues(30.dp,8.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)// Item padding
            ) {
                items(itemList.size) { itemIndex ->
                    ItemCard(itemList[itemIndex].first, itemList[itemIndex].second, onNavigate)
                }
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
                        onClick = { selectedItem = index },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp)) // 둥근 모서리 적용
                            .padding(4.dp) // 아이템 간격 조정
                    )
                }
            }
        }
    }
}




@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onSearch(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("검색어를 입력하세요") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { searchText = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Text",
                        tint = Color.Gray
                    )
                }
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF6200EE),
            unfocusedBorderColor = Color.Gray,
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp),
        singleLine = true
    )
}

@Composable
fun Bamboo(onNavigate: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(resource = Res.drawable.chop),
                contentDescription = "대나무 칫솔",
                modifier = Modifier.size(200.dp, 200.dp)
            )
            Text(text = "대나무 칫솔",style = MaterialTheme.typography.titleLarge)

        }
    }
}




@Composable
fun SimpleLazyColumn() {
    // 샘플 데이터를 위한 리스트
    val itemsList = List(20) { "Item #$it" }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // items를 사용해 리스트 항목을 구성
        items(itemsList) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

