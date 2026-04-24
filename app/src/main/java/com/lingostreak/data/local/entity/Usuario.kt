package com.lingostreak.data.local.entity
import androidx.room.*
@Entity
data class Usuario(
    @PrimaryKey val id: Int = 1,
    val rachaActual: Int = 0,
    val rachaMax: Int = 0,
    @ColumnInfo(defaultValue = "ACTIVA") val rachaEstado: String = "ACTIVA",
    val nivelActual: Int = 1,
    val tokensDescanso: Int = 0,
    val progresoNivel: Int = 100,
    val ultimoLogin: Long = System.currentTimeMillis()
)
