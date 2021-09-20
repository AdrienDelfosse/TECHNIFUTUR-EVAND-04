package com.adrien.technifutur_evand04.model

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseTypeWithExpenses(
    @Embedded val expenseType: ExpenseType,
    @Relation(
        parentColumn = "expenseTypeId",
        entityColumn = "typeId"
    )
    val expenses: LiveData<List<Expense>>
        )
