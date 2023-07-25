package com.example.raksaonlinecompose.screen

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.raksaonlinecompose.R
import com.example.raksaonlinecompose.bottom_nav.BottomMenuContent
import com.example.raksaonlinecompose.route.ScreenRoute
import com.example.raksaonlinecompose.ui.theme.cardPolicy
import com.example.raksaonlinecompose.ui.theme.colorGradient
import com.example.raksaonlinecompose.ui.theme.revamp_main
import com.example.raksaonlinecompose.ui.theme.revamp_stroke_card
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.raksaonlinecompose.dao.CardDao
import com.example.raksaonlinecompose.dao.HistoryDao
import com.example.raksaonlinecompose.db.AppDatabase
import com.example.raksaonlinecompose.entity.Card
import com.example.raksaonlinecompose.entity.History
import com.example.raksaonlinecompose.model.ButtonProgress
import com.example.raksaonlinecompose.model.IconButtonHome
import com.example.raksaonlinecompose.model.PolicyActiveOrInactiveList
import com.example.raksaonlinecompose.model.ProgressHistoryData
import com.example.raksaonlinecompose.ui.theme.darkGrayColor
import com.example.raksaonlinecompose.ui.theme.lightGrayColor
import com.example.raksaonlinecompose.ui.theme.mediumGrayColor
import kotlinx.coroutines.delay
import java.text.DecimalFormat


@Composable
fun HomeScreen(navController: NavHostController, db: CardDao) {
    var selectedItemIndex by remember {
        mutableStateOf(0)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        ViewPager(
            db,
            navController,
            context = context,
            items = listOf(
//                IconButtonHome(
//                    iconID = R.drawable.button_home_claim_revamp,
//                    "Pengajuan Klaim",
//                    "Home"
//                ),
                IconButtonHome(
                    iconID = R.drawable.button_home_simulation_revamp,
                    "Riwayat Transaksi",
                    "Home"
                ),
//                IconButtonHome(
//                    iconID = R.drawable.button_home_towing_revamp,
//                    "Bantuan Derek",
//                    "Home"
//                ),
                IconButtonHome(
                    iconID = R.drawable.button_home_24hour_call_revamp,
                    "Layanan 24 Jam",
                    "Home"
                ),
                IconButtonHome(
                    iconID = R.drawable.button_location_info_revamp_new,
                    "Lokasi Layanan",
                    "Home"
                ),
//                IconButtonHome(
//                    iconID = R.drawable.button_service_revamp,
//                    "Bengkel Terdekat",
//                    "Home"
//                ),
            ),
            selectedItemIndex
        ){
            selectedItemIndex = it
        }

        if(selectedItemIndex == 1){
            FloatingActionButton(
                onClick = { /* Aksi yang dijalankan ketika tombol diklik */ },
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 10.dp, bottom = 10.dp)
                    .align(Alignment.BottomEnd)
                    .offset(y = (-60).dp), // Menambahkan offset di atas BottomMenu
                contentColor = Color.White,
                containerColor = revamp_main,
                elevation = FloatingActionButtonDefaults.elevation(1.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }


        BottomMenu(
            items = listOf(
                BottomMenuContent("Home",
                    if (selectedItemIndex == 0) revamp_stroke_card else Color.White,
                    if (selectedItemIndex == 0) R.drawable.home_buttom_nav_click else R.drawable.home_buttom_nav),
                BottomMenuContent("My Portofolio",
                    if (selectedItemIndex == 1) revamp_stroke_card else Color.White,
                    if (selectedItemIndex == 1) R.drawable.mypolicy_buttom_nav_click else R.drawable.mypolicy_buttom_nav),
                BottomMenuContent("Promo",
                    if (selectedItemIndex == 2) revamp_stroke_card else Color.White,
                    if (selectedItemIndex == 2) R.drawable.history_buttom_nav_click else R.drawable.history_buttom_nav),
//                BottomMenuContent("Profile",
//                    if (selectedItemIndex == 3) revamp_stroke_card else Color.White,
//                    if (selectedItemIndex == 3) R.drawable.profile_buttom_nav_click else R.drawable.profile_buttom_nav),
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        ){index->
            selectedItemIndex = index
        }
    }
}


@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.card_not_found_data).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier
            .height(103.dp)
            .width(167.dp),
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardViewPager(
    dataCard: List<Card>,
    navController: NavHostController
) {
    val pagerState = rememberPagerState()
    var indexState by remember { mutableStateOf(0) }


    HorizontalPager(pageCount = if (dataCard.isEmpty()) 1 else dataCard.size, state = pagerState) {
        if (dataCard.isNotEmpty()){
            Box(
                modifier = Modifier
                    .height(176.dp)
                    .width(350.dp)
//                    .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(gradientColor())
            ){
                DataCardView(dataCard,indexState, navController = navController)
            }
        }else{
            DataCardNull()
        }

    }
    Spacer(
        modifier = Modifier.height(10.dp)
    )
    // Indicators
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until if (dataCard.isEmpty()) 1 else dataCard.size) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = if (i == pagerState.currentPage) revamp_main else Color.Gray,
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )
            Spacer(
                modifier = Modifier.width(4.dp)
            )
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        indexState = pagerState.currentPage
    }
}

@Composable
fun DataCardView(dataCard: List<Card>, indexState: Int,navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            "Aldian Rahman",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(18.dp)
                .align(Alignment.TopStart)
        )
        Image(
            painterResource(R.drawable.logo_main),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(100.dp)
                .height(30.dp)
                .padding(top = 10.dp, end = 10.dp),
        )
        Box(
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(10.dp).clickable {
                    navController?.navigate(ScreenRoute.ScanScreen.withArgs(dataCard,indexState))
                }
        ){
            Text(
                "Scan To Pay",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .border(1.dp, revamp_stroke_card, RoundedCornerShape(5.dp))
                    .padding(8.dp),
                color = revamp_stroke_card
            )
        }
        Column(
            modifier = Modifier.align(Alignment.TopStart).padding(top = 40.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                "Nomor Kartu",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = revamp_stroke_card,
                modifier = Modifier.padding(start = 18.dp)
            )
            Text(
                dataCard[indexState].noCard,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 18.dp)
            )
            Spacer(
                modifier = Modifier.height(15.dp)
            )
            Text(
                "Saldo",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = revamp_stroke_card,
                modifier = Modifier.padding(start = 18.dp)
            )
            val formatter = DecimalFormat.getCurrencyInstance() as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.currencySymbol = "Rp. "
            formatter.decimalFormatSymbols = symbols
            val formattedAmount = formatter.format(dataCard[indexState].amount)
            Text(
                formattedAmount,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 18.dp)
            )
        }
    }
}

fun gradientColor(): Brush {
    val startColor = revamp_main
    val endColor = colorGradient
    val gradientColors = listOf(startColor, endColor)

    return Brush.linearGradient(
        colors = gradientColors,
        start = Offset(0f, 0f),
        end = Offset(1000f, 0f)
    )
}

@Composable
fun DataCardNull() {
    Card(
        modifier = Modifier
            .height(176.dp)
            .width(350.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(cardPolicy),
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    GifImage()
                    Text(
                        text = "Anda belum memiliki polis aktif, tambahkan polis sekarang?",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = revamp_stroke_card,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                        colors = CardDefaults.cardColors(revamp_main),
                        content = {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painterResource(R.drawable.add_policy),
                                    contentDescription = null,
                                    modifier = Modifier.padding(10.dp).size(24.dp)
                                )
                                Text(
                                    text = "Tambahkan Polis",
                                    fontSize = 11.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(10.dp),
                                )
                            }
                        }
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(
    db: CardDao,
    navController: NavHostController,
    context: Context,
    items: List<IconButtonHome>,
    indexClick: Int = 0,
    index: (Int) -> Unit
) {
    val pagerState = rememberPagerState()
    var indexState by remember { mutableStateOf(0) }
    val maxRows = 3
    val chunkedList = items.chunked(maxRows)
    LaunchedEffect(indexClick) {
        pagerState.scrollToPage(indexClick)
    }
    HorizontalPager(pageCount = 4, state = pagerState) { index1 ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(lightGrayColor),
            contentAlignment = Alignment.Center
        ) {
            if (indexClick == 0 || index1 == 0){
                Beranda(db = db,chunkedList, navController = navController)
            }else if (indexClick == 1 || index1 == 1){
                val dbHistory: HistoryDao = AppDatabase.getInstance(context)?.historyDao()!!
                val dataHistory = dbHistory.gelAllHistory()

                ListHistory(dataHistory, modifier = Modifier.align(Alignment.TopStart))
//                MyPolicy(
//                    Modifier.align(Alignment.TopStart),
//                    context
//                )
            }else if (indexClick == 2 || index1 == 2){
                Column(
                    modifier = Modifier.align(Alignment.TopStart),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopHeader(
                        modifier = Modifier.fillMaxWidth(),
                        "History"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Column(
                            modifier = Modifier.align(Alignment.TopStart)
                        ) {
                            TabLayoutScreenHistory(context)
                        }

                    }
                }
            }else if (indexClick == 3 || index1 == 3){
                TopHeader(
                    modifier = Modifier.align(Alignment.TopCenter),
                    "Profile"
                )
                Text(text = "Page Index : $index1")
            }


            LaunchedEffect(pagerState.currentPage) {
                indexState = pagerState.currentPage
                index(indexState) // Notify the external listener
            }
        }
    }
}

@Composable
fun ListHistory(
    dataHistory: List<History>,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeader(
            modifier = Modifier.fillMaxWidth(),
            "My History"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Image(
                        painterResource(R.drawable.icon_guides),
                        contentDescription = null,
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .padding(5.dp)
                    )
                }
                LazyColumn {
                    items(dataHistory){
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
//                                    .combinedClickable(
//                                        onClick = {Toast.makeText(context, items.claimNo, Toast.LENGTH_SHORT).show()},
//                                        onLongClick = {Toast.makeText(context, items.status, Toast.LENGTH_SHORT).show()},
//                                        onDoubleClick = {Toast.makeText(context, items.vehicle.toString(), Toast.LENGTH_SHORT).show()}
//                                    )
                        ,
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(4.dp),

                        ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                "Id Transaksi ${it.id}",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )
                            Text("No Kartu : ${it.noCard}")
                            Text("Nama Bank : ${it.bankName}")
                            Text("Nama Merchant : ${it.merchant}")
                            Text("Id User : ${it.idUser}")
                            Text("Saldo Awal : ${it.amount}")
                            Text("Saldo Akhir : ${it.newAmount}")
                            Text("Jumlah Transaksi : ${it.value}")
                            Text("Status Transaksi : ${it.status}")
                        }
                    }
                        if (it == dataHistory.last()){
                            Spacer(
                                modifier = Modifier.height(100.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun MyPolicy(
    modifier: Modifier,
    context: Context
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeader(
            modifier = Modifier.fillMaxWidth(),
            "My Policy"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Image(
                        painterResource(R.drawable.icon_guides),
                        contentDescription = null,
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .padding(5.dp)
                    )
                }
                TabLayoutScreen(context)
            }

        }
    }
}

@Composable
fun TabLayoutScreen(context: Context) {
    // Define the list of tab titles
    val tabTitles = listOf("Aktif", "Kadaluarsa")

    // Define the selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }


    val dataPolicy: List<PolicyActiveOrInactiveList> = List(100) { index ->
        val isPrime = isPrimeNumber(index)
        val typePolicy = if (index % 2 == 0) stringResource(R.string.auto_text) else stringResource(
                    R.string.non_auto_text)
        PolicyActiveOrInactiveList(
            noPolicy = "04-M00001-000-01-2019 $index",
            periodPolicy = "09 Dec 2018 s/d 09 Dec 2025",
            typePolicy = typePolicy,
            activeOrInactive = isPrime,
        )
    }.sortedWith(
        compareByDescending<PolicyActiveOrInactiveList> {
            it.typePolicy == context.getString(R.string.auto_text)
        }
            .thenByDescending {
                it.activeOrInactive
            }
    )

    var isFirstAutoItemExp = true
    var isFirstAutoItem = true
    var isFirstNonAutoItem = true
    var isFirstNonAutoItemExp = true

    dataPolicy.forEachIndexed { _, item ->
        if(item.typePolicy == stringResource(R.string.auto_text) && isFirstAutoItem && item.activeOrInactive){
            item.isHeader = true
            isFirstAutoItem = false
        }else if(item.typePolicy == stringResource(R.string.auto_text) && isFirstAutoItemExp && !item.activeOrInactive){
            item.isHeader = true
            isFirstAutoItemExp = false
        } else if(item.typePolicy == stringResource(R.string.non_auto_text) && isFirstNonAutoItem && item.activeOrInactive){
            item.isHeader = true
            isFirstNonAutoItem = false
        }else if(item.typePolicy == stringResource(R.string.non_auto_text) && isFirstNonAutoItemExp && !item.activeOrInactive){
            item.isHeader = true
            isFirstNonAutoItemExp = false
        }
    }


    Column {
        // Create the tab row
        TabRow(
            selectedTabIndex,
            containerColor = lightGrayColor,
            contentColor = revamp_main,
            modifier = Modifier.background(Color.Transparent)
            ) {
            // Create a tab for each title
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(16.dp),
                        color = Color.Black
                    )
                }
            }
        }

        // Content based on selected tab
        when (selectedTabIndex) {
            0 -> {
                // Content for Tab 1
                PolicyActiveOrInactive(
                    context,
                    activeOrInactive = true,
                    itemsPolicy = dataPolicy
                )
            }
            1 -> {
                // Content for Tab 2
                PolicyActiveOrInactive(
                    context,
                    activeOrInactive = false,
                    itemsPolicy = dataPolicy
                )
            }
        }
    }
}

@Composable
fun TabLayoutScreenHistory(context: Context) {
    var btnClick by remember { mutableStateOf(0) }
    var btnClickHistory by remember { mutableStateOf(0) }

    // Define the list of tab titles
    val tabTitles = listOf("Aktif", "Selesai")

    // Define the selected tab index
    var selectedTabIndex by remember { mutableStateOf(0) }

    val dataClaim = List(20){ index ->
        ProgressHistoryData(
            "04-00002-07-2023 $index",
            "04-M-00001-000-01-2019",
            "04-M00001-07-2023",
            "Bengkel Motor Lautan Berlian",
            "Toyota New Diesel G 4X2 2.5AT (B4329SIS)",
            "Pengajuan Klaim",
            index %2 == 0
        )
    }

    val dataTowing = List(20){ index ->
        ProgressHistoryData(
            "02-00002-07-2023 $index",
            "04-M-00001-000-01-2019",
            "-",
            "PT Mandiri Tunas Finance",
            "Toyota New Diesel G 4X2 2.5AT (B4329SIS)",
            "Permintaan Dikirim",
            index %2 == 0
        )
    }

    val dataSimulation = List(100){ index ->
        ProgressHistoryData(
            "02-00002-07-2023 $index",
            "Auto",
            "Hyundai Ionic 5",
            "Selesai Otomatis",
            "",
            "",
            index %2 == 0
        )
    }


//    val dataPolicy: List<PolicyActiveOrInactiveList> = List(100) { index ->
//        val isPrime = isPrimeNumber(index)
//        val typePolicy = if (index % 2 == 0) stringResource(R.string.auto_text) else stringResource(
//            R.string.non_auto_text)
//        PolicyActiveOrInactiveList(
//            noPolicy = "04-M00001-000-01-2019 $index",
//            periodPolicy = "09 Dec 2018 s/d 09 Dec 2025",
//            typePolicy = typePolicy,
//            activeOrInactive = isPrime,
//        )
//    }.sortedWith(
//        compareByDescending<PolicyActiveOrInactiveList> {
//            it.typePolicy == context.getString(R.string.auto_text)
//        }
//            .thenByDescending {
//                it.activeOrInactive
//            }
//    )
//
//    var isFirstAutoItemExp = true
//    var isFirstAutoItem = true
//    var isFirstNonAutoItem = true
//    var isFirstNonAutoItemExp = true
//
//    dataPolicy.forEachIndexed { _, item ->
//        if(item.typePolicy == stringResource(R.string.auto_text) && isFirstAutoItem && item.activeOrInactive){
//            item.isHeader = true
//            isFirstAutoItem = false
//        }else if(item.typePolicy == stringResource(R.string.auto_text) && isFirstAutoItemExp && !item.activeOrInactive){
//            item.isHeader = true
//            isFirstAutoItemExp = false
//        } else if(item.typePolicy == stringResource(R.string.non_auto_text) && isFirstNonAutoItem && item.activeOrInactive){
//            item.isHeader = true
//            isFirstNonAutoItem = false
//        }else if(item.typePolicy == stringResource(R.string.non_auto_text) && isFirstNonAutoItemExp && !item.activeOrInactive){
//            item.isHeader = true
//            isFirstNonAutoItemExp = false
//        }
//    }


    Column {
        // Create the tab row
        TabRow(
            selectedTabIndex,
            containerColor = Color.White,
            contentColor = revamp_main,
            modifier = Modifier.background(Color.Transparent)
        ) {
            // Create a tab for each title
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        btnClickHistory = 0
                        btnClick = 0
                              },
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(16.dp),
                        color = Color.Black
                    )
                }
            }
        }

        // Content based on selected tab
        when (selectedTabIndex) {
            0 -> {
                // Content for Tab 1
                ProgressClaim(
                    selectedTabIndex,
                    context,
                    dataClaim = dataClaim,
                    dataTowing = dataTowing,
                    dataSimulation = dataSimulation,
                    onLastItem = {

                    },
                    items = listOf(
                        ButtonProgress("Klaim", if(btnClick == 0) revamp_main else Color.White),
                        ButtonProgress("Bantuan Derek", if(btnClick == 1) revamp_main else Color.White),
                        ButtonProgress("Riwayat Transaksi", if(btnClick == 2) revamp_main else Color.White),
                    )
                ){index ->
                    btnClick = index
                }
            }
            1 -> {
                // Content for Tab 2
                ProgressClaim(
                    selectedTabIndex,
                    context,
                    dataClaim = dataClaim,
                    dataTowing = dataTowing,
                    dataSimulation = dataSimulation,
                    onLastItem = {

                    },
                    items = listOf(
                        ButtonProgress("Klaim", if(btnClickHistory == 0) revamp_main else Color.White),
                        ButtonProgress("Bantuan Derek", if(btnClickHistory == 1) revamp_main else Color.White),
                        ButtonProgress("Upgrade & Renewal", if(btnClickHistory == 2) revamp_main else Color.White),
                        ButtonProgress("Riwayat Transaksi", if(btnClickHistory == 3) revamp_main else Color.White),
                    )
                ){index ->
                    btnClickHistory = index
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProgressClaim(
    indexTabLayout: Int,
    context:Context,
    items: List<ButtonProgress>,
    dataClaim: List<ProgressHistoryData>,
    dataTowing: List<ProgressHistoryData>,
    dataSimulation: List<ProgressHistoryData>,
    onLastItem:(Boolean)->Unit,
    onItemClick: (Int) -> Unit,
    ) {
    var indexBtnClick by remember { mutableStateOf(0) }
    var showLoading by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        items.forEachIndexed { index, item ->
            ButtonProgressOrHistory(
                modifier = Modifier
                    .weight(1f),
                item.name,
                items,
                index,
                item.active
            ){
                onItemClick(it)
                indexBtnClick = index
            }
        }
    }
    if (indexBtnClick == 0 && indexTabLayout == 0){
        LazyColumn{
            items(dataClaim){ items ->
                ItemProgressActiveOrInactive(
                    true,
                    context,
                    items
                )

                if (items == dataClaim.last()) {
                    if (showLoading){
                        LoadingLastItem()
                        LaunchedEffect(true) {
                            delay(3000L) // 3 seconds delay
                            showLoading = false
                        }
                    }else{
                        val newData = GetData(dataClaim)
                        newData.toList().forEachIndexed() { index,duplicatedItem ->
                            ItemProgressActiveOrInactive(
                                true,
                                context,
                                duplicatedItem
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }else if (indexBtnClick == 0 && indexTabLayout == 1){
        LazyColumn{
            items(dataClaim){ items ->
                ItemProgressActiveOrInactive(
                    false,
                    context,
                    items
                )

                if (items == dataClaim.last()) {
                    LoadingLastItem()
                }
            }
        }
    }
    else if (indexBtnClick == 1 && indexTabLayout == 0){
        LazyColumn{
            items(dataTowing){ items ->
                ItemProgressActiveOrInactive(
                    true,
                    context,
                    items
                )

                if (items == dataTowing.last()) {
                    LoadingLastItem()
                }
            }
        }
    }else if (indexBtnClick == 1 && indexTabLayout == 1){
        LazyColumn{
            items(dataTowing){ items ->
                ItemProgressActiveOrInactive(
                    false,
                    context,
                    items
                )

                if (items == dataTowing.last()) {
                    LoadingLastItem()
                }
            }
        }
    }else if (indexBtnClick == 2 && indexTabLayout == 0){
        LazyColumn{
            items(dataSimulation){ items ->
                ItemProgressActiveOrInactive(
                    true,
                    context,
                    items
                )

                if (items == dataSimulation.last()) {
                    LoadingLastItem()
                }
            }
        }
    }else if (indexBtnClick == 3 && indexTabLayout == 1){
        LazyColumn{
            items(dataSimulation){ items ->
                ItemProgressActiveOrInactive(
                    false,
                    context,
                    items
                )

                if (items == dataSimulation.last()) {
                    LoadingLastItem()
                }
            }
        }
    }else{
        LazyColumn{
            items(dataSimulation){ items ->
                ItemProgressActiveOrInactive(
                    true,
                    context,
                    items
                )

                if (items == dataSimulation.last()) {
                    LoadingLastItem()
                }
            }
        }
    }

}

fun GetData(dataClaim: List<ProgressHistoryData>): List<ProgressHistoryData> {
    val newData = List(10) { index ->
        ProgressHistoryData(
            "04-00002-07-2023 ${index + dataClaim.size}",
            "04-M-00001-000-01-2019",
            "04-M00001-07-2023",
            "Bengkel Motor Lautan Berlian",
            "Toyota New Diesel G 4X2 2.5AT (B4329SIS)",
            "Pengajuan Klaim",
            (index + dataClaim.size) % 2 == 0
        )

    }

    return newData
}

@Composable
fun LoadingLastItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.size(50.dp)
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxSize()
                    .padding(5.dp),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemProgressActiveOrInactive(active: Boolean, context: Context, items: ProgressHistoryData) {
    if (items.active == active){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = {Toast.makeText(context, items.claimNo, Toast.LENGTH_SHORT).show()},
                        onLongClick = {Toast.makeText(context, items.status, Toast.LENGTH_SHORT).show()},
                        onDoubleClick = {Toast.makeText(context, items.vehicle.toString(), Toast.LENGTH_SHORT).show()}
                    ) ,
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(4.dp),

                ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ){
                    Image(
                        painterResource(R.drawable.icon_remove),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(start = 30.dp, top = 8.dp,end=10.dp)
                            .size(20.dp),
                    )
                    Column(
                        modifier = Modifier.align(Alignment.TopStart)
                    ) {
                        TextProgress("Reg. No.",items.regNo,true,active)
                        TextProgress("No Polis",items.policyNo,false,active)
                        TextProgress("Klaim No.",items.claimNo,false,active)
                        TextProgress("Tertanggung",items.insuredName,false,active)
                        if (items.vehicle.isNotBlank()){
                            TextProgress("Kendaraan",items.vehicle,false,active)
                        }
                        if (items.status.isNotBlank()){
                            TextProgress("Status",items.status,false,active)
                        }
//                        TextProgress("Aktif",items.active.toString(),false,active)
                    }
                }
            }
        }
    }
}

@Composable
fun TextProgress(s: String, s1: String, bold: Boolean,active: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            s,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(0.30f),
            fontSize = if (bold) 15.sp else 12.sp,
            color = if (active) mediumGrayColor else Color.Red,
            textAlign = TextAlign.Start
        )
        Text(
            ": ",
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .padding(top = 5.dp),
            fontSize = if (bold) 15.sp else 12.sp,
            color = if (active) mediumGrayColor else Color.Red,
            textAlign = TextAlign.Start
        )
        Text(
            s1,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(0.70f),
            fontSize = if (bold) 15.sp else 12.sp,
            color = if (active) mediumGrayColor else Color.Red,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun ButtonProgressOrHistory(
    modifier: Modifier,
    s: String,
    items: List<ButtonProgress>,
    index: Int,
    active: Color,
    onItemClick: (Int) -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(1f/items.size).padding(horizontal = 5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = active
        ),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onItemClick(index)
        }
    ){
        Text(
            s,
            fontSize = 7.sp,
            lineHeight = 7.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

fun isPrimeNumber(number: Int): Boolean {
    if (number <= 1) {
        return false
    }
    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PolicyActiveOrInactive(
    context: Context,
    activeOrInactive: Boolean,
    itemsPolicy: List<PolicyActiveOrInactiveList>
) {
    LazyColumn{
        items(itemsPolicy){ items ->
            if (if (activeOrInactive) items.activeOrInactive else !items.activeOrInactive){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    if (items.isHeader){
                        Text(
                            items.typePolicy,
                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    start = 10.dp,
                                    end = 10.dp
                                ),
                            fontWeight = FontWeight.Bold,
                            color = darkGrayColor
                        )
                    }
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {Toast.makeText(context, items.noPolicy, Toast.LENGTH_SHORT).show()},
                                onLongClick = {Toast.makeText(context, items.typePolicy, Toast.LENGTH_SHORT).show()},
                                onDoubleClick = {Toast.makeText(context, items.isHeader.toString(), Toast.LENGTH_SHORT).show()}
                            ) ,
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.cardElevation(4.dp),

                        ) {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 8.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(
                                items.noPolicy,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 5.dp, bottom = 20.dp),
                                fontSize = 15.sp,
                                color = if (activeOrInactive) mediumGrayColor else Color.Red,
                                textAlign = TextAlign.Center
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    "Periode Polis",
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    color = if (activeOrInactive) mediumGrayColor else Color.Red,
                                    fontSize = 14.sp
                                )
                                Text(
                                    items.periodPolicy,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    color = if (activeOrInactive) mediumGrayColor else Color.Red,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }

            if (items == itemsPolicy.last()) {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun Beranda(
    db: CardDao,
    chunkedList: List<List<IconButtonHome>>,
    navController: NavHostController
) {
    val amountCard1 by remember { mutableStateOf(5000000L) }
    val amountCard2 by remember { mutableStateOf(600000L) }


    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeader(
            modifier = Modifier.fillMaxWidth(),
            ScreenRoute.HomeScreen.route
        )
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ){
            item {
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                CardViewPager(
                    dataCard = db.gelAllCard(),
                    navController = navController
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Text(
                    "Daftar Layanan",
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            items(chunkedList) { chunkedItems ->
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items(chunkedItems) { item ->
                        Box(
                            modifier = Modifier.width(120.dp).height(100.dp).padding(5.dp).layoutId("Image Button"),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.align(Alignment.TopCenter).size(65.dp),
                                painter = painterResource(item.iconID),
                                contentDescription = null
                            )
                            Text(
                                item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(top = 60.dp),
                                color = Color.Gray,
                                lineHeight = 10.sp
                            )
                        }
                    }
                }
            }
            item {
                Spacer(
                    modifier = Modifier.height(25.dp)
                )
                Text(
                    "Promo",
                    modifier = Modifier
                        .padding(10.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                CardViewPager(
                    dataCard = listOf(
                    ),
                    navController = navController
                )
                Spacer(
                    modifier = Modifier.height(100.dp)
                )
            }
        }
    }
}


//@Composable
//fun LazyRowWithMaxRows(items: List<IconButtonHome>) {
//    val maxRows = 3
//    val chunkedList = items.chunked(maxRows)
//
//    LazyColumn(
//    ) {
//        items(chunkedList) { chunkedItems ->
//            LazyRow(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceAround
//            ) {
//                items(chunkedItems) { item ->
//                    Box(
//                        modifier = Modifier.width(127.dp).height(100.dp).padding(5.dp),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Image(
//                            modifier = Modifier.align(Alignment.TopCenter).size(65.dp),
//                            painter = painterResource(item.iconID),
//                            contentDescription = null
//                        )
//                        Text(
//                            item.name,
//                            textAlign = TextAlign.Center,
//                            fontSize = 11.sp,
//                            modifier = Modifier.align(Alignment.BottomCenter),
//                            color = Color.Gray
//                            )
//                    }
//                }
//            }
//        }
//    }
//}





@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = revamp_main,
    initialSelectedItemIndex: Int = 0,
    indexNavBottom: (Int) -> Unit
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }


    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(
                topEnd = 20.dp,
                topStart = 20.dp
            ))
            .fillMaxWidth()
            .background(revamp_main)
            .height(60.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                modifier = Modifier.weight(1f),
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor
            ) {
                selectedItemIndex = index
                indexNavBottom(index)
//                if (selectedItemIndex == 0){
//                    navController?.navigate(ScreenRoute.HomeScreen.route)
//                }else{
//                    Util.toastToText(context, "Clicked : $selectedItemIndex")
//                }
//                else if (selectedItemIndex == 1){
//                    navController?.navigate(ScreenRoute.SecondScreen.withArgs(selectedItemIndex.toString()))
//                }
            }
        }
    }
}

@Composable
fun TopHeader(
    modifier: Modifier = Modifier,
    s: String
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(revamp_main),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            if (s == ScreenRoute.HomeScreen.route){
                Image(
                    painterResource(R.drawable.logo_main),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(30.dp)
                )
                Spacer(
                    Modifier.height(10.dp)
                )
                Text(
                    "Hi, (User Email)",
                    color = revamp_stroke_card,
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Italic,
                )
            }else{
                Text(
                    text = s,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.Bold,
                    color = revamp_stroke_card
                )
            }
        }
        Image(
            painterResource(R.drawable.icon_notif),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(45.dp)
        )
    }
}

@Composable
fun BottomMenuItem(
    modifier: Modifier,
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.25f)
            .clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
        ) {
            Image(
                painter = painterResource(id = item.iconID),
                contentDescription = item.title,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = item.color,
            fontSize = 10.sp
        )
    }
}

