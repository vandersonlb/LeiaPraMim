package br.com.fiap.leiapramim.view

import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.R
import br.com.fiap.leiapramim.route.NavigationItem
import br.com.fiap.leiapramim.view.components.BottomNavigation
import br.com.fiap.leiapramim.view.components.CameraView
import br.com.fiap.leiapramim.viewmodel.NavigationViewModel
import java.io.File

@Composable
fun CameraScreen(navController: NavHostController, navigationViewModel: NavigationViewModel) {

    val context = LocalContext.current
    val cameraExecutor = ContextCompat.getMainExecutor(context)
    val imageCapture = ImageCapture.Builder().build()

    Box {

        CameraView(context, imageCapture, cameraExecutor)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row {

                IconButton(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    onClick = {

//                        val picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        val picturesDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                        val outputFile = File(picturesDirectory, "${System.currentTimeMillis()}.jpg")

//                        val outputFile = File(context.externalMediaDirs.first(),"${System.currentTimeMillis()}.jpg")
//                        val outputFile = File(context.filesDir, "${System.currentTimeMillis()}.jpg")
                        val outputOptions = ImageCapture.OutputFileOptions.Builder(outputFile).build()

                        imageCapture.takePicture(
                            outputOptions,
                            cameraExecutor,
                            object : ImageCapture.OnImageSavedCallback {

                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    val savedUri = Uri.fromFile(outputFile)
                                    val escapedUri = Uri.encode(savedUri.toString())
                                    navController.navigate(route = "${NavigationItem.Preview.route}/$escapedUri")
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    Log.e("ERROR", "$exception")
                                }
                            }
                        )

                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.click),
                        contentDescription = "Click Button",
                        tint = Color.Unspecified,
                    )
                }
            }

            Row {
                BottomNavigation(navController, navigationViewModel)
            }
        }
    }
}