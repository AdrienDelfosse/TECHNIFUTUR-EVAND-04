package com.adrien.technifutur_evand04.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adrien.technifutur_evand04.model.Expense
import com.adrien.technifutur_evand04.model.ExpenseTypeWithExpenses

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense WHERE expenseId = :expenseId LIMIT 1")
    fun findById(expenseId:Long): LiveData<Expense>

    @Query("SELECT * FROM expense WHERE expenseId = :typeId LIMIT 1")
    fun findExpenseWithExpenseTypeById(typeId: Long): LiveData<ExpenseTypeWithExpenses>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense): Long

    @Query("SELECT * FROM expense")
    fun getAll(): LiveData<List<Expense>>

    @Query("DELETE FROM expense WHERE expenseId = :expenseId")
    suspend fun deleteExpense(expenseId: Long)
}