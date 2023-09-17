package br.com.fiap.leiapramim.view

import android.net.Uri
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
import br.com.fiap.leiapramim.view.actions.SendOCR
import coil.compose.rememberImagePainter

@Composable
fun PreviewScreen(navController: NavHostController, uriString: String) {

    val uri = Uri.parse(uriString)

    Box {
        Image(
            painter = rememberImagePainter(data = uri),
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
                    ) {
                        navController.popBackStack()
                    }
                }
                Column(columnModifier) {
                    ButtonPreview(
                        R.drawable.done,
                        "Botão de aceitar"
                    ) {
                        SendOCR(uri)
                    }
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