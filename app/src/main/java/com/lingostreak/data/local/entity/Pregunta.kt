package com.lingostreak.data.local.entity
import androidx.room.*
@Entity
data class Pregunta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val temaId: Int,
    val texto: String,
    val opciones: String,
    val respuestaCorrecta: String,
    val explicacion: String
)
