package com.example.chetile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(context = this)
        }
    }
}

@Composable
fun MainScreen(context: Context) {
    val scope = rememberCoroutineScope()
    val hadithDbHelper = HadithDatabaseHelper(context)
    var hadithText by remember { mutableStateOf(hadithDbHelper.getRandomHadith()) }
    var userInput by remember { mutableStateOf("") }
    var selectedColumn by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf("") }

    // Создаем новый Excel файл, если его нет
    val excelHelper = ExcelHelper(context)
    excelHelper.createNewExcelFile()

    // Используем `LaunchedEffect` для изменения хадиса при старте экрана
    LaunchedEffect(Unit) {
        hadithText = hadithDbHelper.getRandomHadith() // Получаем новый хадис при старте
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        // Отображение хадиса
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.LightGray, RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = hadithText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }

        // Столбцы и Дни недели под хадисом
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 250.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 10.dp)
            ) {
                val columns = listOf("KK", "PR", "RNK", "MP3", "OR", "TH", "B/T", "CV", "SLVT")
                columns.forEach { column ->
                    Button(
                        onClick = { selectedColumn = column },
                        modifier = Modifier
                            .width(90.dp)
                            .height(50.dp)
                            .border(
                                width = 1.dp,
                                color = if (selectedColumn == column) Color.Cyan else Color.Gray,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(column, fontSize = 18.sp, color = Color.White)
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(end = 10.dp)
            ) {
                val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                days.forEach { day ->
                    Button(
                        onClick = { selectedDay = day },
                        modifier = Modifier
                            .width(90.dp)
                            .height(50.dp)
                            .border(
                                width = 1.dp,
                                color = if (selectedDay == day) Color.Cyan else Color.Gray,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(day, fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }

        // Поле ввода числа и кнопка отправки
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BasicTextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .width(80.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(6.dp)
            )

            Button(onClick = {
                scope.launch {
                    val cell = "${getColumnLetter(selectedColumn)}${getDayRow(selectedDay)}"
                    excelHelper.updateCell("Лист1", cell, userInput)
                    Toast.makeText(context, "Данные записаны в Excel!", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Отправить")
            }

            // Кнопка для обновления хадиса
            Button(onClick = {
                hadithText = hadithDbHelper.getRandomHadith() // Обновление хадиса по нажатию
            }) {
                Text("Сменить хадис")
            }
        }

        // Кнопка для открытия Excel файла
        Button(
            onClick = {
                val file = File(context.filesDir, "Chetile.xlsx")
                if (file.exists()) {
                    val uri = FileProvider.getUriForFile(
                        context,
                        "com.example.chetile.provider",
                        file
                    )
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Файл Excel не найден", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .background(Color.Cyan, RoundedCornerShape(50))
                .width(120.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Excel",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Функция для преобразования столбца в букву
fun getColumnLetter(column: String): Char {
    return when (column) {
        "KK" -> 'C'
        "PR" -> 'D'
        "RNK" -> 'E'
        "MP3" -> 'F'
        "OR" -> 'G'
        "TH" -> 'H'
        "B/T" -> 'I'
        "CV" -> 'J'
        "SLVT" -> 'K'
        else -> 'A'
    }
}

// Функция для получения строки на основе дня недели
fun getDayRow(day: String): Int {
    return when (day) {
        "Mon" -> 4
        "Tue" -> 5
        "Wed" -> 6
        "Thu" -> 7
        "Fri" -> 8
        "Sat" -> 9
        "Sun" -> 10
        else -> 4
    }
}
