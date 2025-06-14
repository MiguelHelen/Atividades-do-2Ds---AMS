package com.example.appcadastro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.appcadastro.ui.theme.AppcadastroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppcadastroTheme {
                appPreview()
            }
        }
    }
}

@Composable
fun ProdutoItem() {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var curso by remember { mutableStateOf("") }
    var serie by remember { mutableStateOf("") }
    var observacoes by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            Button(
                onClick = { /* ação do botão */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Cadastrar")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {


            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color(0xFF1976D2), Color(0xFF64B5F6))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
               
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                    modifier = Modifier
                        .size(160.dp)
                        .border(3.dp, Color.White, RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.avatr_woman),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text("Cadastre-se", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome completo") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = { Text("Telefone") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = curso,
                onValueChange = { curso = it },
                label = { Text("Curso") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = serie,
                onValueChange = { serie = it },
                label = { Text("Série") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = observacoes,
                onValueChange = { observacoes = it },
                label = { Text("Observações") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun appPreview() {
    AppcadastroTheme {
        ProdutoItem()
    }
}
