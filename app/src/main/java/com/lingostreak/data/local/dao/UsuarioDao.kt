package com.lingostreak.data.local.dao
import androidx.room.*
import com.lingostreak.data.local.entity.Usuario
import kotlinx.coroutines.flow.Flow
@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario WHERE id = 1") fun getUsuario(): Flow<Usuario?>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertarUsuario(usuario: Usuario)
    @Query("UPDATE Usuario SET rachaActual = rachaActual + 1, rachaMax = MAX(rachaMax, rachaActual + 1), ultimoLogin = :ahora, rachaEstado = 'ACTIVA' WHERE id = 1") suspend fun sumarRacha(ahora: Long)
    @Query("UPDATE Usuario SET rachaEstado = 'CONGELADA' WHERE id = 1") suspend fun congelarRacha()
    @Query("UPDATE Usuario SET tokensDescanso = tokensDescanso - 1 WHERE id = 1 AND tokensDescanso > 0") suspend fun usarTokenDescanso()
}
