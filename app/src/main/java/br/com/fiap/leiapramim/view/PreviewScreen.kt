package br.com.fiap.leiapramim.view

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.leiapramim.R
import coil.compose.rememberImagePainter

@Composable
fun PreviewScreen(navController: NavHostController, uri: String) {

    val uri = Uri.parse(uri)
    Log.i("teste", "$uri")

    Box {
        Image(
            painter = rememberImagePainter(data = uri),
//            painter = painterResource(id = R.drawable.foto),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.size(220.dp, 100.dp)
            ) {
                val columnModifier = Modifier
                    .weight(1f)
//                    .fillMaxSize()
//                    .background(Color.Red)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                Column(columnModifier) {
                    ButtonPreview(
                        R.drawable.cancel,
                        "Botão de cancelar"
                    ) { navController.popBackStack() }
                }
                Column(columnModifier) {
                    ButtonPreview(
                        R.drawable.done,
                        "Botão de aceitar"
                    ) { Log.i("teste", "ACEITADO") }
                }
            }
        }
    }
}

@Composable
fun ButtonPreview(icon: Int, description: String, action: () -> Unit) {

    IconButton(
        onClick = { action() },
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
            tint = Color.Unspecified
        )
    }
}



