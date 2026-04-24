package com.lingostreak.data.local
import androidx.room.*
import com.lingostreak.data.local.dao.*
import com.lingostreak.data.local.entity.*
@Database(entities = [Usuario::class, Tema::class, Pregunta::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun temaDao(): TemaDao
    abstract fun preguntaDao(): PreguntaDao
}
