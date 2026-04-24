package com.lingostreak.data.repository
import android.content.Context
import com.lingostreak.data.local.dao.*
import com.lingostreak.data.local.entity.*
import kotlinx.coroutines.flow.*
import org.json.JSONArray
class UsuarioRepository(private val usuarioDao: UsuarioDao, private val temaDao: TemaDao, private val preguntaDao: PreguntaDao, private val context: Context) {
    fun getUsuario(): Flow<Usuario?> = usuarioDao.getUsuario()
    suspend fun crearUsuarioInicial() { if (usuarioDao.getUsuario().firstOrNull() == null) usuarioDao.insertarUsuario(Usuario()) }
    suspend fun sumarRacha() = usuarioDao.sumarRacha(System.currentTimeMillis())
    suspend fun congelarRacha() = usuarioDao.congelarRacha()
    suspend fun cargarTemasDesdeAssets(): Boolean {
        return try {
            if (temaDao.contarTemas() > 0) return true
            val json = context.assets.open("temas_nivel_1.json").bufferedReader().use { it.readText() }
            val temasArray = JSONArray(json)
            val temas = mutableListOf<Tema>()
            val preguntas = mutableListOf<Pregunta>()
            for (i in 0 until temasArray.length()) {
                val temaJson = temasArray.getJSONObject(i)
                val temaId = temaJson.getInt("id")
                temas.add(Tema(temaId, temaJson.getString("nombre"), temaJson.getInt("nivel")))
                val preguntasArray = temaJson.getJSONArray("preguntas")
                for (j in 0 until preguntasArray.length()) {
                    val p = preguntasArray.getJSONObject(j)
                    preguntas.add(Pregunta(temaId = temaId, texto = p.getString("texto"), opciones = p.getJSONArray("opciones").toString(), respuestaCorrecta = p.getString("respuestaCorrecta"), explicacion = p.getString("explicacion")))
                }
            }
            temaDao.insertarTemas(temas)
            preguntaDao.insertarPreguntas(preguntas)
            true
        } catch (e: Exception) { false }
    }
}
