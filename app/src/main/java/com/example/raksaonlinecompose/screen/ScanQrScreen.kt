package com.example.raksaonlinecompose.screen

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.util.Size
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.raksaonlinecompose.dao.CardDao
import com.example.raksaonlinecompose.dao.HistoryDao
import com.example.raksaonlinecompose.entity.History
import com.example.raksaonlinecompose.route.ScreenRoute
import java.text.DecimalFormat

@Composable
fun ScanQRScreen(
    db: CardDao,
    dbHistory: HistoryDao,
    navController: NavHostController,
    string: String?
) {

    var code by remember { mutableStateOf("") }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult ={granted->
            hasCameraPermission = granted
        }
    )
    var saldo by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true){
        launcher.launch(
            Manifest.permission.CAMERA
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(hasCameraPermission){
            AndroidView(
                factory = { context ->
                    val previewView  = PreviewView(context)
                    val preview = Preview.Builder().build()
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setTargetResolution(
                            Size(
                            previewView.width,
                            previewView.height
                        )
                        )
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyzer{result ->
                            Log.d("QRCODERESULT", "ScanQRScreen: "+ result)
                            code = result
                        }
                    )
                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                    previewView
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.5f)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
            ){
                val parts = code.split(".")
                val bank = parts.getOrNull(0) ?: ""
                val id = parts.getOrNull(1) ?: ""
                val merchant = parts.getOrNull(2) ?: ""
                val amount = parts.getOrNull(3) ?: ""

                val split = string?.split("-")
                val noCard = split?.getOrNull(0)?: ""
                val balance = split?.getOrNull(1)?: ""
                saldo = balance.toInt()


                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        "No Card : $noCard",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Saldo : $saldo",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                if (code.isNotBlank()){
                    Column(
                        modifier = Modifier
                            .padding(32.dp)
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "Bank : $bank",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Id User : $id",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Merchant : $merchant",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        val formatter = DecimalFormat.getCurrencyInstance() as DecimalFormat
                        val symbols = formatter.decimalFormatSymbols
                        symbols.currencySymbol = "Rp. "
                        formatter.decimalFormatSymbols = symbols

                        val formattedAmount = formatter.format(amount.toInt())
                        Text(
                            text = "Jumlah : $formattedAmount,-",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        onClick = {
                            val result = saldo - amount.toInt()
                            Toast.makeText(context, "Pembayaran Berhasil, Sisa Saldo Anda $result", Toast.LENGTH_SHORT).show()
                            db.updateAmountByCardNo(noCard,result.toLong())
                            dbHistory.insertOrReplaceUser(
                                noCard,
                                saldo.toLong(),
                                bank,
                                id,
                                merchant,
                                result.toLong(),
                                amount.toLong(),
                                "Berhasil"
                            )

                            navController.navigate(ScreenRoute.HomeScreen.route)
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(10.dp)
                    ){
                        Text("Bayar")
                    }
                }
            }



        }
    }
}