package com.lingostreak.data.local.dao
import androidx.room.*
import com.lingostreak.data.local.entity.Pregunta
@Dao
interface PreguntaDao {
    @Query("SELECT * FROM Pregunta WHERE temaId = :temaId") suspend fun getPreguntasPorTema(temaId: Int): List<Pregunta>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertarPreguntas(preguntas: List<Pregunta>)
}
