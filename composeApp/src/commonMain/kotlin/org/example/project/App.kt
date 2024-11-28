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
fun AnimatedScreenTransition() {
    var currentScreen by remember { mutableStateOf("home") }

    AnimatedContent(targetState = currentScreen) { screen ->
        when (screen) {
            "home" -> App(
                onBambooClick = { currentScreen = "bamboo" },
                onSearchEnter = { currentScreen = "searchResult" },
                onLiked = { currentScreen = "like" },
                onProfile= { currentScreen = "profile"}
            )
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
fun App(onBambooClick: () -> Unit,
        onSearchEnter: () -> Unit,
        onLiked: () -> Unit,
        onProfile: () -> Unit){
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
                        ItemCard(itemList[itemIndex], onBambooClick)
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



@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchBarClick: (String) -> Unit={},
    onSearchEnter: (String) -> Unit={} // 엔터 이벤트 처리
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onSearchBarClick(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .onKeyEvent { event ->
                if (event.key == Key.Enter) {
                    onSearchEnter(searchText)  // 엔터 키를 눌렀을 때 처리
                    true
                } else {
                    false
                }
            },
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
        singleLine = true,
    )
}


@Composable
fun Bamboo(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("bamboo") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "bamboo",
            enter = fadeIn(tween(1000)) + slideInHorizontally(initialOffsetX = { 1000 }), // 오른쪽에서 왼쪽으로 슬라이드
            exit = fadeOut(tween(1000)) + slideOutHorizontally(targetOffsetX = { -1000 }) // 왼쪽으로 슬라이드
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 뒤로 가기 버튼
                IconButton(
                    onClick = onBackBarClick,
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 상단 텍스트
                CustomNavigationBar(
                    items = listOf("친환경", "일반"),
                    selectedIcons = listOf("친환경", "일반"),
                    unselectedIcons = listOf("친환경", "일반"),
                    selectedItem = selectedItem,
                    onItemSelected = { index ->
                        selectedItem = index
                        if (index == 1) {
                            currentScreen = "newScreen"
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.chop),
                    contentDescription = "대나무 칫솔",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "대나무 칫솔",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 설명 텍스트를 스크롤 가능하게 변경
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .height(400.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()) // 스크롤 추가
                ) {
                    Text(
                        text = "대나무 칫솔의 손잡이는 자연에서 쉽게 분해 가능합니다.\n\n"+
                                "대나무는 빠르게 자라는 식물로, 재배와 수확이 쉽고 지속 가능한 자원으로써 자원 낭비를 줄일 수 있습니다.\n\n"+
                                "대나무에는 자연적으로 향균 성분이 있어, 사용 중 박테리아 번식을 억제하는 데 도움줄 수 있습니다.\n\n"+
                                "대나무 칫솔을 사용하면 미세 플라스틱 문제를 줄일 수 있습니다.\n\n"+
                                "사용자 리뷰          4.85\n\n"+
                                "    5   \n" +
                                "suak****| 24.10.19. | 대나무 칫솔 : 검정  나무의 잎 이라는 기업명 답게 상자 포장 테이프부터 안쪽 포장까지 전부 종이였어요! 친환경입니다 ㅎㅎ 물건 하나하나 사용법, 버리는 법까지...\n\n"+
                                "    5\n"+
                                "wjaw****| 24.10.21. | 대나무 칫솔 : 검정  원래 대나무 칫솔 쓰고 있는데요. 여러 사람한테 나눠 줘야 할 일이 있어서 조금 많이 구매했어요. 가격도 비싸지 않고, 많이 샀다고 몇 개 더 주셨네요. 친절한 편지도 감사 드려요. 잘 쓰겠습니다.\n"+
                                "더보기...\n\n"+
                                "관련 정보\n"+
                                "자연 분해 가능성: 대나무 칫솔의 손잡이는 자연에서 쉽게 분해됩니다. 대나무는 생분해성이 뛰어나며, 폐기 시 환경에 미치는 영향이 적습니다. https://www.greencompostables.com/blog/bamboo-toothbrush\n\n"+
                                "지속 가능한 자원: 대나무는 성장 속도가 매우 빠른 식물로, 하루에 최대 1미터까지 자랄 수 있습니다. 이러한 특성으로 인해 재배와 수확이 용이하며, 지속 가능한 자원으로 활용됩니다.\n"+
                                "https://www.joongang.co.kr/article/25065732\n\n"+
                                "자연 항균 성분: 대나무에는 항균 작용이 포함되어 있어, 사용 중 박테리아 번식을 억제하는 데 도움이 됩니다. 이로 인해 곰팡이 발생이 적고 위생적인 사용이 가능합니다.\n"+
                                "https://www.greencompostables.com/blog/bamboo-toothbrush\n\n"+
                                "미세 플라스틱 문제 감소: 대나무 칫솔을 사용하면 플라스틱 사용을 줄여 미세 플라스틱 문제를 완화할 수 있습니다. 플라스틱 칫솔은 분해되지 않고 환경에 축적되지만, 대나무 칫솔은 자연 분해되어 환경 오염을 줄입니다.\n"+
                                "https://www.greencompostables.com/blog/bamboo-toothbrush "                    ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/giraffe_store/products/5497692332?nl-au=451e104c09f7467bbc7eb4c287c6cfdd&nl-query=%EB%8C%80%EB%82%98%EB%AC%B4+%EC%B9%AB%EC%86%94")
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }

        // 두 번째 화면: 새 화면
        AnimatedVisibility(
            visible = currentScreen == "newScreen",
            enter = fadeIn(tween(1000)) + slideInHorizontally(initialOffsetX = { -1000 }), // 왼쪽에서 오른쪽으로 슬라이드
            exit = fadeOut(tween(1000)) + slideOutHorizontally(targetOffsetX = { 1000 }) // 오른쪽으로 슬라이드
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 뒤로 가기 버튼
                IconButton(
                    onClick = onBackBarClick,
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 상단 텍스트
                CustomNavigationBar(
                    items = listOf("친환경", "일반"),
                    selectedIcons = listOf("친환경", "일반"),
                    unselectedIcons = listOf("친환경", "일반"),
                    selectedItem = selectedItem,
                    onItemSelected = { index ->
                        selectedItem = index
                        if (index == 0) {
                            currentScreen = "bamboo"
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.plastic),
                    contentDescription = "플라스틱 칫솔",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "투명 플라스틱 칫솔",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 설명 텍스트를 스크롤 가능하게 변경
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .height(400.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()) // 스크롤 추가
                ) {
                    Text(
                        text = "물과 습기에 강해 오래 사용할 수 있고 손잡이가 쉽게 변형되지 않아 실용적임.\n\n" +
                                "상대적으로 저렴한 가격에 제공됨.\n\n" +
                                "하지만, 사용 후 폐기물이 환경에 큰 영향을 미침.\n\n" +
                                "일부 저렴한 플라스틱 칫솔은 제조 과정에서 유해 화학 물질이 포함될 수 있어 건강에 해로울 가능성이 있음.",
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = { /* 구매 버튼 클릭 시 동작 */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA1B5D8))
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun solid(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("solid") }
    var selectedItem by remember { mutableIntStateOf(0) }// 화면 상태를 관리
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "solid",
            enter = fadeIn(tween(1000)) + slideInHorizontally(initialOffsetX = { 1000 }), // 오른쪽에서 왼쪽으로 슬라이드
            exit = fadeOut(tween(1000)) + slideOutHorizontally(targetOffsetX = { -1000 }) // 왼쪽으로 슬라이드
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 뒤로 가기 버튼
                IconButton(
                    onClick = onBackBarClick,
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 상단 텍스트
                CustomNavigationBar(
                    items = listOf("친환경", "일반"),
                    selectedIcons = listOf("친환경", "일반"),
                    unselectedIcons = listOf("친환경", "일반"),
                    selectedItem = selectedItem,
                    onItemSelected = { index ->
                        selectedItem = index
                        if (index == 1) {
                            currentScreen = "newScreen"
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.solid),
                    contentDescription = "고체 치약",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "고체 치약",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 설명 텍스트를 스크롤 가능하게 변경
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .height(400.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()) // 스크롤 추가
                ) {
                    Text(
                        text = "일반 치약에 비해 재활용 가능한 포장재로 제공,\n" +
                                "폐기물 문제를 줄이는 데 기여함.\n" +
                                "\n" +
                                "고체 치약은 알약 형태로, 개별 포장되어 있어 \n" +
                                "필요한 개수만큼 가져갈 수 있어 편리함.\n" +
                                "\n" +
                                "한 알에 필요한 양만 정확히 포함되어 있어 \n" +
                                "과도한 치약 사용을 방지할 수 있음.\n" +
                                "\n" +
                                "천연 성분으로 만들어져 인체와 환경에 더 안전함\n" +
                                "특히 화학 성분에 민감한 사람들에게 좋음.\n"            ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/mintedshop/products/7149880472?nl-au=cd944803b34f40d3afd7222badf7bbfb&nl-query=%EA%B3%A0%EC%B2%B4%EC%B9%98%EC%95%BD")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }

        // 두 번째 화면: 새 화면
        AnimatedVisibility(
            visible = currentScreen == "newScreen",
            enter = fadeIn(tween(1000)) + slideInHorizontally(initialOffsetX = { -1000 }), // 왼쪽에서 오른쪽으로 슬라이드
            exit = fadeOut(tween(1000)) + slideOutHorizontally(targetOffsetX = { 1000 }) // 오른쪽으로 슬라이드
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 뒤로 가기 버튼
                IconButton(
                    onClick = onBackBarClick,
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 상단 텍스트
                CustomNavigationBar(
                    items = listOf("친환경", "일반"),
                    selectedIcons = listOf("친환경", "일반"),
                    unselectedIcons = listOf("친환경", "일반"),
                    selectedItem = selectedItem,
                    onItemSelected = { index ->
                        selectedItem = index
                        if (index == 0) {
                            currentScreen = "solid"
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.common),
                    contentDescription = "일반 치약",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            // 이미지 클릭 시 애니메이션 효과를 주면서 다른 화면으로 변경
                            currentScreen = "solid"  // 원래 화면으로 되돌림
                        }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "일반 치약",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 설명 텍스트를 스크롤 가능하게 변경
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .height(400.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()) // 스크롤 추가
                ) {
                    Text(
                        text = "일반 치약은 주로 플라스틱 유브에 담겨 있어 \n" +
                                "일반 치약에는 보존제, 인공 색소, 향료,\n" +
                                "과도한 거품을 내기 위한 계면활성제 등이 포함.\n" +
                                "입안의 민감성, 구강 건강 문제를 야기.\n\n" +
                                "원하는 양을 짜내기 때문에 필요 이상으로 \n" +
                                "많이 짜내거나 낭비하기 쉬움.\n\n" +
                                "일반 치약은 부피가 크고 무거우며, 액체류로 \n" +
                                "간주될 수 있어 기내 반입에 제약이 있음.\n\n" ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = { /* 구매 버튼 클릭 시 동작 */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA1B5D8))
                ) {
                    Text("구매하러 가기", color = Color.White)
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