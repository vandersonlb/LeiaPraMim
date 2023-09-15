package br.com.fiap.leiapramim.view

import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import java.io.File


@Composable
fun CameraScreen() {

    val context = LocalContext.current
    val cameraExecutor = ContextCompat.getMainExecutor(context)
    var imageCapture: ImageCapture? = null

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)){

        Row(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .weight(1f)){

            AndroidView(
                factory = { context ->
                    PreviewView(context).apply {
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    }
                },
                modifier = Modifier.fillMaxSize()
            ) {
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener({

                    val cameraProvider = cameraProviderFuture.get()
                    cameraProvider.unbindAll()

                    val preview = Preview.Builder().build()
                    imageCapture = ImageCapture.Builder().build()

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    val camera = cameraProvider.bindToLifecycle(
                        context as LifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )

                    preview.setSurfaceProvider(it.surfaceProvider)
                }, cameraExecutor)
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {

//                val picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                val picturesDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//                val outputFile = File(picturesDirectory, "nome_do_arquivo.jpg")

//                val outputFile = File(context.externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
                val outputFile = File(context.filesDir, "foto_temp.jpg")
                val outputOptions = ImageCapture.OutputFileOptions.Builder(outputFile).build()

                imageCapture?.takePicture(
                    outputOptions,
                    cameraExecutor,
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                            val savedUri = Uri.fromFile(outputFile)
                            var fileName = savedUri.toString().substringAfterLast("/")
//                            navController.navigate(route = "preview/$fileName")

                        }

                        override fun onError(exception: ImageCaptureException) {
                            Log.e("teste", "$exception")
                        }
                    }
                )
            }) {
                Text(text = "TIRAR FOTO 123")
            }
        }
    }
}
