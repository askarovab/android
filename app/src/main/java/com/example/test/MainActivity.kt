package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---- Переключатели экспериментов ----
const val SCREEN_SPACE_BETWEEN = false
const val SLOT_TOP_ALIGN = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CampusScreen()
                }
            }
        }
    }
}

@Composable
fun CampusScreen() {
    // Упражнение 2
    var useSpaceBetween by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = if (SCREEN_SPACE_BETWEEN) Arrangement.SpaceBetween else Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Упражнение 1
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Campus Card", style = MaterialTheme.typography.headlineMedium)
            Text("Student ID: 2026001")
        }

        Spacer(modifier = Modifier.height(16.dp))


        ProfileHeader(name = "Ada Lovelace", status = "In class · Building B")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Today", style = MaterialTheme.typography.titleLarge)


        Column(modifier = Modifier.fillMaxWidth()) {
            ScheduleRow("09:00", "CS101 Intro", "Lab 3")
            ScheduleRow("11:00", "MATH210", "A-204")

            ScheduleRow(
                "14:00",
                "Soft Skills and Professional Communication for Engineers",
                "Hall 1"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Переключатель для упражнения 2
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = useSpaceBetween, onCheckedChange = { useSpaceBetween = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (useSpaceBetween) "SpaceBetween" else "Center + spacedBy(16.dp)")
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (useSpaceBetween)
                Arrangement.SpaceBetween
            else
                Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { /* Map */ }) { Text("Map") }
            TextButton(onClick = { /* QR */ }) { Text("Show QR") }
        }
    }
}

// Упражнение 5
@Composable
fun ProfileHeader(name: String, status: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Column {
            Text(name, style = MaterialTheme.typography.titleMedium)
            Text(status)
        }
    }
}

@Composable
fun ScheduleRow(time: String, title: String, room: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        // Упражнение 3
        verticalAlignment = if (SLOT_TOP_ALIGN) Alignment.Top else Alignment.CenterVertically
    ) {

        Text(text = time, fontSize = 22.sp, modifier = Modifier.width(76.dp))
        // Упражнение 4
        Text(
            text = title,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
        Text(text = room)
    }
}

@Preview(showBackground = true)
@Composable
fun CampusScreenPreview() {
    MaterialTheme { CampusScreen() }
}

