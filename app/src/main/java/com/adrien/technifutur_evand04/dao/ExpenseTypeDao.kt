package com.adrien.technifutur_evand04.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adrien.technifutur_evand04.model.ExpenseType

@Dao
interface ExpenseTypeDao {
    @Query("SELECT * FROM expensetype")
    fun getAll(): LiveData<List<ExpenseType>>

    @Query("SELECT * FROM expensetype")
    suspend fun getAllType(): List<ExpenseType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expenseType: ExpenseType): Long

    @Query("DELETE FROM expensetype WHERE expenseTypeId = :expenseTypeID")
    suspend fun deleteBook(expenseTypeID: Long)

    @Query("SELECT * FROM expensetype WHERE expenseTypeId = :expenseTypeID LIMIT 1")
    fun findById(expenseTypeID: Long): LiveData<ExpenseType>


}