package com.example.mynotes.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mynotes.R
import com.example.mynotes.component.TopBar
import com.example.mynotes.data.NotesEntity
import com.example.mynotes.ui.NoteEvents

@Composable
fun AddEditScreen(
    id: Long,
    note: NotesEntity?,
    event: (NoteEvents) -> Unit,
    navController: NavController
) {

    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    LaunchedEffect(note) {
        if (id != 0L && note != null) {
            title = note.title
            description = note.description
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = if (id == 0L) "Add Note" else "Edit Note",
                onBackClick = { navController.navigateUp() }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(id = R.color.RichTaupe)
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(80.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.SoftMocha)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {

                OutlinedTextField(
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxSize(),
                    placeholder = {
                        Text(
                            text = "Title",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    singleLine = true,
                    value =  title ,
                    onValueChange = { title = it }
                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .size(300.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.SoftMocha)),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                OutlinedTextField(
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.fillMaxSize(),
                    placeholder = {
                        Text(
                            color = Color.White,
                            text = "Description...",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    value = description ,
                    onValueChange = { description = it }
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            ElevatedButton(
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorResource(id = R.color.SoftMocha),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onClick = {
                    if(id == 0L){
                        if(title.isNotEmpty() || description.isNotEmpty()){
                            event(NoteEvents.AddNote(title, description))
                            navController.navigateUp()
                        }else{
                            Toast.makeText(context, "Enter title or description to save", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        event(NoteEvents.EditNote(id, title, description))
                        navController.navigateUp()
                    }
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}