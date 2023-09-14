package br.com.fiap.leiapramim

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.leiapramim.ui.theme.LeiaPraMimTheme
import br.com.fiap.leiapramim.view.CameraScreen
import br.com.fiap.leiapramim.view.PreviewScreen
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeiaPraMimTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "camera"
                    ) {
                        composable(route = "camera") { CameraScreen(navController) }
                        composable(route = "preview/{fileName}") { //
                            PreviewScreen(navController, it.arguments?.getString("fileName")!!)
                        }
                    }
                }
            }
        }
    }
}

/**
@Composable
fun CameraPreview() {
    val context = LocalContext.current
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                // Configure o PreviewView, se necessário
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
**/

/**
@Composable
fun CameraScreenBCKP() {
    val context = LocalContext.current
    val cameraExecutor = ContextCompat.getMainExecutor(context)
    var imageCapture: ImageCapture? = null

    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { view ->
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            /**val camera =**/ cameraProvider.bindToLifecycle(
                context as LifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )

            preview.setSurfaceProvider(view.surfaceProvider)
        }, cameraExecutor)
    }

    Log.i("teste", "11111")

    Button(
        onClick = {

//            val picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val picturesDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val outputFile = File(picturesDirectory, "nome_do_arquivo.jpg")
//            val outputFile = File(context.externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
//            val outputFile = File(context.filesDir, "foto_temp.jpg")
            val outputOptions = ImageCapture.OutputFileOptions.Builder(outputFile).build()

            Log.i("teste", "22222")

            imageCapture?.takePicture(
                outputOptions,
                cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                        Log.i("teste", "$outputFile")
                        // A imagem foi salva com sucesso
                        // Faça o que for necessário com a imagem salva aqui

                        // Supondo que você tenha a URI do arquivo salvo
                        val savedUri = Uri.fromFile(outputFile)
                        Log.i("teste", "$savedUri")

                    }

                    override fun onError(exception: ImageCaptureException) {
                        // Ocorreu um erro ao salvar a imagem
                        Log.e("teste", "$exception")
                    }
                }
            )

            Log.i("teste", "3333")
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Capturar Foto")
    }
}

@Composable
fun FotoView() {

    val context = LocalContext.current
    val cameraExecutor = ContextCompat.getMainExecutor(context)
    var imageCapture: ImageCapture? = null

    var uri by remember { mutableStateOf<Uri?>(null) }
    var savedUri by remember { mutableStateOf<Uri?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black)
                .weight(1f)
        ) {

            AndroidView(
                factory = { context ->
                    PreviewView(context).apply {
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    }
                },
                modifier = Modifier.fillMaxSize()
            ) { view ->
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener({

                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build()
                    imageCapture = ImageCapture.Builder().build()

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    val camera = cameraProvider.bindToLifecycle(
                        context as LifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )

                    preview.setSurfaceProvider(view.surfaceProvider)
                }, cameraExecutor)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Yellow)
                .weight(1f)
        ) {


            LaunchedEffect(savedUri) {
                savedUri?.let {
//                    ImagemTirada(it)
                }
            }

        }


        Row(
            modifier = Modifier
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

                Log.i("teste", "22222")

                imageCapture?.takePicture(
                    outputOptions,
                    cameraExecutor,
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                            savedUri = Uri.fromFile(outputFile)
//                            Log.i("teste", "$savedUri")
//                            uri = savedUri

                        }

                        override fun onError(exception: ImageCaptureException) {
                            Log.e("teste", "$exception")
                        }
                    }
                )

                Log.i("teste", "3333")
            }) {
                Text(text = "TIRAR FOTO")
            }
        }
    }
    }
}
**/