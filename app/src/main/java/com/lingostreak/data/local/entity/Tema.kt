package com.lingostreak.data.local.entity
import androidx.room.*
@Entity
data class Tema(
    @PrimaryKey val id: Int,
    val nombre: String,
    val nivel: Int,
    val duracionPomodoro: Int = 15
)
