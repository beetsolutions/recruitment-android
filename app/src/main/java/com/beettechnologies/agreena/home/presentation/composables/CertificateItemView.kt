package com.beettechnologies.agreena.home.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beettechnologies.agreena.R
import com.beettechnologies.agreena.home.domain.model.CertificateModel

@Composable
fun CertificateItemView(
    model: CertificateModel,
    favoriteAction: (id: String, value: Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {

            },
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF6BFFFF))
    ) {

        Column(modifier = Modifier.height(150.dp)) {

            Box(modifier = Modifier.fillMaxWidth()) {

                val icon =
                    if (model.isFavorite) R.drawable.favorite_filled else R.drawable.favorite_outlined
                Image(painterResource(icon),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(44.dp)
                        .clickable {
                            favoriteAction(model.id, !model.isFavorite)
                        })

                Text(
                    "ID: ${model.id}",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(end = 56.dp, start = 16.dp)
                        .align(Alignment.TopStart)
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 50.dp)
                ) {

                    Text(
                        "Originator: ${model.originator}".uppercase(),
                        color = Color.Black,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        "Owner: ${model.owner}".uppercase(),
                        color = Color.Black,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}
