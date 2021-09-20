package com.adrien.technifutur_evand04.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ExpenseType (
    @PrimaryKey(autoGenerate = true)
    val expenseTypeId:Long,
    val name:String
        )
