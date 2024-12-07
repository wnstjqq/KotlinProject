package org.example.project

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun Soft(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Soft") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Soft",
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

                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.soft),
                    contentDescription = "고체 샴푸바",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "고체 샴푸바",
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
                        text =    "샴푸바는 고체 형태로 플라스틱 용기가 필요하지 않아, 플라스틱 쓰레기 감소 \n" +
                                "\n" +
                                "자연 유래 성분을 기반으로 만들며,\n" +
                                "합성 향료 등의 유해 성분이 적거나 없음.\n" +
                                "두피와 모발에 자극이 덜해 피부에도 안전함.\n" +
                                "\n" +
                                "유해한 화학물질이 적게 포함되어 있어 \n" +
                                "하수로 흘러가도 오염을 줄일 수 있음.\n" +
                                "\n"      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://brand.naver.com/donggubat/products/5248863166?nl-au=57352fc2955741ea9a0b40116ae66fcb&nl-query=%EC%83%B4%ED%91%B8%EB%B0%94")
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
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}
@Composable
fun Susemi(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Susemi") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Susemi",
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
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.susemi),
                    contentDescription = "천연 수세미",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "천연 수세미",
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
                        text =    "천연 수세미는 식물로 만들어져\n" +
                                "사용 후 자연에서 분해됨.\n" +
                                "\n" +
                                "천연 수세미는 인공 화학 물질 없이 자연 재료,\n" +
                                "유해 물질이 물이나 음식으로 유출될 가능성 낮음.\n" +
                                "이는 더 건강한 선택으로 이어짐.\n" +
                                "\n" +
                                "천연 수세미는 일반 수세미에 비해 \n" +
                                "박테리아 번식이 덜하여 상대적으로 냄새가 덜 남.\n" +
                                "\n" +
                                "천연 수세미는 부드러워서 다양한 용도로 사용."      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/dalpoong/products/4414653078?nl-au=b3695a53bc4f48a4bda1cb924f618ca8&nl-query=%EC%B2%9C%EC%97%B0+%EC%88%98%EC%84%B8%EB%AF%B8")},
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
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}
@Composable
fun Bar(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Bar") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Bar",
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
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.bar),
                    contentDescription = "설거지 바",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "설거지 바",
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
                        text =    "설거지바는 고체형태로 제공되기 때문에 \n" +
                                "포장쓰레기를 줄일 수 있어 환경에 더 친화적임.\n" +
                                "\n" +
                                "천연 성분을 사용해 제조되며, \n" +
                                "과일이나 채소를 씻는 데도 사용할 수 있음.\n" +
                                "이는 인체에 해가 덜 미칠 수 있음. \n" +
                                "\n" +
                                "적은 양으로도 충분한 거품을 내고 세척력이 높아,\n" +
                                "세제를 씻어내기 위해 사용하는 물의 양 감소.\n" +
                                "이는 물 절약에도 기여하는 장점.\n" +
                                "\n"      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/somjy/products/4505413677?NaPm=ct%3Dm34odn1k%7Cci%3D3ea1f98f4342d4d0c6792b7c9f1a8b06ef5dfba7%7Ctr%3Dslsl%7Csn%3D862169%7Chk%3D15447dbc8bdb6b3af9dbe016c107c4f6dcaa2acd&nl-au=0f248d47e41e464f9db923e7d529d302&nl-query=설거지바")
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
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}
@Composable
fun Smell(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Smell") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Smell",
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

                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.smell),
                    contentDescription = "서랍장 탈취제",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "서랍장 탈취제",
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
                        text =    "천연 탈취제는 활성탄, 피톤치드, \n" +
                                "제올라이트와 같은 자연 유래 성분으로 만들어져\n" +
                                "유해 화학물질 방출 위험이 적음.\n" +
                                "\n" +
                                "인공 화학물질을 포함하지 않아, \n" +
                                "알레르기나 천식을 유발할 가능성이 낮음.\n" +
                                "\n" +
                                "플라스틱 용기 없이도 사용할 수 있는 형태도 많아\n" +
                                "쓰레기 감소에도 도움.\n" +
                                "\n" +
                                "오랜 시간 동안 냄새를 억제하고, \n" +
                                "공기 중의 유해 물질을 줄이는데 기여. \n" +
                                "\n"      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/urte/products/4749466163?nl-au=839d4ef8ed274ee8aca8a6dd4958e2f6&nl-query=천연")
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
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Silicon(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Silicon") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Silicon",
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

                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.silicon),
                    contentDescription = "실리콘 지퍼백",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "실리콘 지퍼백",
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
                        text =    "실리콘 지퍼백은 내구성이 높아 다회용 사용 가능.\n" +
                                "탄성이 뛰어나 물리적 손상에 강함.\n" +
                                "\n" +
                                "냉동 및 전자레인지 사용이 가능하여 \n" +
                                "다양한 용도로 활용할 수 있음.\n" +
                                "\n" +
                                "열탕 소독이 가능해 세균이나 잔여 오염 물질을\n" +
                                "효과적으로 제거하는 데 유용.\n" +
                                "\n" +
                                "실리콘은 식품 안전 등급으로 만들어져,\n" +
                                "식품을 보관할 때 유해 화학물질이 침출될 \n" +
                                "가능성이 적음.\n" +
                                "\n"      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://brand.naver.com/shinsung_silicone/products/5943787717?n_media=27758&n_query=실리콘지퍼백&n_rank=2&n_ad_group=grp-a001-02-000000023866822&n_ad=nad-a001-02-000000159940248&n_campaign_type=2&n_mall_id=ncp_1nu5cs_01&n_mall_pid=5943787717&n_ad_group_type=2&n_match=3&NaPm=ct%3Dm34td4go%7Cci%3D0yW0003RowDBx18dIf2z%7Ctr%3Dpla%7Chk%3D51861b47306979a9ad516890bed2e65a057939ed%7Cnacn%3DpNowBMwHr16K")
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
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Cup(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Cup") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Cup",
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

                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.cup),
                    contentDescription = "친환경 종이컵",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "친환경 종이컵",
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
                        text =    "친환경 종이컵은 자연에서 쉽게 분해되는 소재,\n" +
                                "쓰레기를 배출되더라도 자연에 부담이 적음. \n" +
                                "\n" +
                                "재활용된 종이 또는 지속 가능한 산림에서 얻은 \n" +
                                "종이를 원료로 사용하여 자원낭비를 줄임. \n" +
                                "\n" +
                                "친환경 종이컵은 식물성 코팅 또는 무독성 잉크로\n" +
                                "만들어지는 경우가 많아 건강에 해가 되지 않음.\n" +
                                "\n" +
                                "별도의 플라스틱 코팅이 없어 재활용 공정이 \n" +
                                "더 쉽고, 재활용률을 높이는데 기여.\n" +
                                "\n"      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/foodfnb/products/5765437023?nl-au=a601e1fa1eec439c9697581f0c9af47e&nl-query=친환경+종이컵")
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
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Tisue(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Tisue") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Tisue",
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
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.tisue),
                    contentDescription = "친환경 물티슈",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "친환경 물티슈",
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
                        text =    "친환경 물티슈는 자연에서 유래한 \n" +
                                "생분해성 소재로 만들어져 자연에서 쉽게 분해.\n" +
                                "\n" +
                                "친환경 물티슈는 일반 물티슈에 포함될 수 있는\n" +
                                "화학물질을 배제하고, 천연성분으로 제조.\n" +
                                "피부가 민감한 사람에게 더 안전한 선택.\n" +
                                "\n" +
                                "친환경 물티슈는 소각 시 유해 물질이 적게 발생\n" +
                                "공기 오염을 줄이는데 도움이 됨.\n" +
                                "\n"      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/yuhan_kb/products/5773558629?nl-au=5918f506761f44c3b961fcec575dd8bd&nl-query=친환경물티슈")
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
                        containerColor = Color(0xFFA1B5D8)
                    )
                ) {
                    Text("구매하러 가기", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Stainless(onBackBarClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    var currentScreen by remember { mutableStateOf("Stainless") }
    var selectedItem by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        // 첫 번째 화면: 대나무 화면
        AnimatedVisibility(
            visible = currentScreen == "Stainless",
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
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 이미지
                Image(
                    painter = painterResource(resource = Res.drawable.stainless),
                    contentDescription = "스테인리스 빨대",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 제목 텍스트
                Text(
                    text = "스테인리스 빨대",
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
                        text =    "스테인리스 빨대는 내구성이 높아 세척 후 \n" +
                                "여러 번 사용할 수 있는 오래 사용 가능.\n" +
                                "\n" +
                                "재사용 가능한 금속 소재로 만들어져, \n" +
                                "매번 버려지는 쓰레기와 플라스틱 오염 문제 해결.\n" +
                                "\n" +
                                "화학물질이 방출되지 않아,\n" +
                                "고온 및 저온 음료 모두에 안전하게 사용 가능.\n" +
                                "\n" +
                                "세척이 용이하여 위생적으로 유지가 가능.\n" +
                                "\n"      ,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 버튼
                Button(
                    onClick = {
                        uriHandler.openUri("https://smartstore.naver.com/soulbean/products/3613591240?NaPm=ct%3Dm34rrq3s%7Cci%3D037543e1d680b0502f2c43981ea854454be99d8c%7Ctr%3Dslsl%7Csn%3D257923%7Chk%3Db288d6be7a4d236302577d2b5f8aee0afe7bb3f0&nl-au=e4ac7cfb24bc4b6eb0dfd4d1261e821b&nl-query=스테인레스+빨대")
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
                        containerColor = Color(0xFFA1B5D8)
                    )
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
                    textAlign = TextAlign.Center)
                // 설명 텍스트를 스크롤 가능하게 변경
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .height(400.dp)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()) // 스크롤 추가
                ) {
                    StyledText(
                        title = "주요 특징",
                        content = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("• 100% 자연 친화적 소재")
                            }
                            append(": 대나무 칫솔은 100% 자연에서 온 대나무로 만들어져 사용 후에도 자연스럽게 분해되어 지구에 부담을 주지 않음.\n\n")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("• 완전한 생분해")
                            }
                            append(": 대나무는 빠르게 자라며, 농약이나 화학 비료를 사용하지 않기 때문에 자연 그대로의 상태로 사용될 수 있음.\n\n")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("• 내구성")
                            }
                            append(": 대나무 칫솔은 내구성이 뛰어나며, 사용이 편리한 가격대의 플라스틱 칫솔에 비해 경제적임.")
                        }
                    )

                    StyledText(
                        title = "주요 사항",
                        content = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("""
• 칫솔을 너무 오랜 시간 동안 습기가 있는 곳에 두지 말고, 건조한 곳에 보관하십시오.
                                    
• 대나무는 물에 오래 노출되면 변형될 수 있으므로 사용 후 잘 말려주십시오.
                                    
• 칫솔의 강도와 경도에 따라 칫솔의 사용 느낌이 달라지며, 일반적으로 부드럽고, 중간, 단단한 종류가 있습니다.""")
                            }
                        }
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
fun StyledText(title: String, content: AnnotatedString) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.body2.copy(lineHeight = 20.sp),
            color = Color.Black
        )
    }
}
