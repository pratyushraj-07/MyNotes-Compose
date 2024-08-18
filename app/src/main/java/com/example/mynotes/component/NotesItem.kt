package com.example.mynotes.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotes.R
import com.example.mynotes.data.NotesEntity
import com.example.mynotes.ui.NoteEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NotesItem(
    note: NotesEntity,
    onClick:()->Unit,
    onDelete:()->Unit
) {
    var isVisible by remember{ mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutHorizontally() + fadeOut()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .wrapContentSize(),
            onClick = { onClick() },
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.SoftMocha))
        ) {
            Row(
                modifier = Modifier.padding(end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                )
                {
                    Text(
                        text = note.title,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = note.description,
                        color = Color.White,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                IconButton(onClick = {
                    isVisible = false
                    scope.launch {
                        delay(500)
                        onDelete()
                    }
                }){
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
        }
    }
}