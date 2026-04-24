package com.lingostreak.ui.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lingostreak.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(private val repo: UsuarioRepository) : ViewModel() {
    val usuario = repo.getUsuario().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
    init { viewModelScope.launch { repo.crearUsuarioInicial(); repo.cargarTemasDesdeAssets() } }
    fun sumarRacha() { viewModelScope.launch { repo.sumarRacha() } }
    fun congelarRacha() { viewModelScope.launch { repo.congelarRacha() } }
}
