package br.com.fiap.leiapramim.view

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.R
import coil.compose.rememberImagePainter

@Composable
fun PreviewScreen(navController: NavHostController, fileName: String) {

    val path = "file:///data/user/0/br.com.fiap.leiapramim/files/"
    val uri = Uri.parse("$path$fileName")

    Box {
        Image(
            painter = rememberImagePainter(data = uri),
            contentDescription = "Imagem teste",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
    }
}
