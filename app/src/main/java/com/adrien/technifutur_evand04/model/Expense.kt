package com.adrien.technifutur_evand04.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Expense (
    @PrimaryKey(autoGenerate = true)
    val expenseId: Long,
    val date: String,
    val name : String,
    val value : Float,
    val typeId: Long
        )

