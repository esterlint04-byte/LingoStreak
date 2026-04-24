package com.lingostreak
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lingostreak.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val usuario by viewModel.usuario.collectAsState()
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text("LingoStreak", style = MaterialTheme.typography.headlineLarge)
                        Spacer(Modifier.height(32.dp))
                        Text("Racha: ${usuario?.rachaActual ?: 0}")
                        Text("Estado: ${usuario?.rachaEstado ?: "CARGANDO"}")
                        Spacer(Modifier.height(32.dp))
                        Button(onClick = { viewModel.sumarRacha() }) { Text("Simular +1 Racha") }
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.congelarRacha() }) { Text("Congelar Racha") }
                    }
                }
            }
        }
    }
}
