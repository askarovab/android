


//Аскарова Бермет

package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val text = "Hello, World!".filter { it.isLetter() }

            val firstRow = text.take(5)
            val secondRow = text.takeLast(5)

            val words = listOf(firstRow, secondRow, "Kotlin", " The Best")


            val yellowCells = remember {
                words.mapIndexed { index, word ->
                    val isEvenRow = (index + 1) % 2 == 0
                    if (isEvenRow) {
                        val count = (1..word.length).random()
                        word.indices.shuffled().take(count).toSet()
                    } else {
                        emptySet()
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                words.forEachIndexed { rowIndex, word ->
                    val isEvenRow = (rowIndex + 1) % 2 == 0

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        word.forEachIndexed { cellIndex, char ->
                            val cellColor = when {
                                !isEvenRow -> MaterialTheme.colorScheme.primary
                                cellIndex in yellowCells[rowIndex] -> Color.Yellow
                                else -> Color.LightGray
                            }

                            Box(
                                modifier = Modifier
                                    .background(color = cellColor)
                                    .weight(1f)
                                    .aspectRatio(1f),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = char.toString(),
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}