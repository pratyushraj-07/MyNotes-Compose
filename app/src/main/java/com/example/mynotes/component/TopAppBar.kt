package com.example.mynotes.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title:String,
    onBackClick:(() -> Unit)? = null,
    onDeleteAllClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.RichTaupe)),
        title = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = title,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.Cream)
                    )
            }
        },
        navigationIcon = {
            onBackClick?.let {
                IconButton(onClick = it) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }

        },
        actions = {
            onDeleteAllClick?.let {
                IconButton(onClick = it,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(35.dp)) {
                    Icon(painter = painterResource(id = R.drawable.delete_forever_svgrepo_com), contentDescription = "Delete all")
                }
            }
        }
    )
}