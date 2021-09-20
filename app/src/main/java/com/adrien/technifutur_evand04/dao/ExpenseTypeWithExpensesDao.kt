package com.adrien.technifutur_evand04.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adrien.technifutur_evand04.model.ExpenseTypeWithExpenses

@Dao
interface ExpenseTypeWithExpensesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ExpenseType : ExpenseTypeWithExpenses)
    @Transaction
    @Query("SELECT * FROM expensetype")
    fun getExpenseTypesWithExpenses() : LiveData<List<ExpenseTypeWithExpenses>>
}