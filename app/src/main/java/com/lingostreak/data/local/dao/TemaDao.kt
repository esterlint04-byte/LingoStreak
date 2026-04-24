package com.lingostreak.data.local.dao
import androidx.room.*
import com.lingostreak.data.local.entity.Tema
import kotlinx.coroutines.flow.Flow
@Dao
interface TemaDao {
    @Query("SELECT * FROM Tema WHERE nivel = :nivel") fun getTemasPorNivel(nivel: Int): Flow<List<Tema>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertarTemas(temas: List<Tema>)
    @Query("SELECT COUNT(*) FROM Tema") suspend fun contarTemas(): Int
}
