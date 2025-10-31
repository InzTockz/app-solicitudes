package com.battilana.app_solicitudes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BattiElevatedCardSection(
    modifier: Modifier = Modifier,
    text: String,
    listButton: List<BattiButtonItem>
){
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ) {
            //Text(text = "Acciones rapidas")
            Text(text = text)
            Spacer(Modifier.height(20.dp))
            listButton.forEach { buttonItem ->
                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = buttonItem.actionOnClick,
                    //modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            painter = painterResource(id = buttonItem.icons),
                            contentDescription = null
                        )
                        Text(text = buttonItem.texto)
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

data class BattiButtonItem(
    var actionOnClick:() -> Unit,
    var icons: Int,
    var texto: String
)