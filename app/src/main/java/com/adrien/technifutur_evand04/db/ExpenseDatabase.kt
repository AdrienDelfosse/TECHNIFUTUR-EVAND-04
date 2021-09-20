package com.adrien.technifutur_evand04.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adrien.technifutur_evand04.dao.ExpenseDao
import com.adrien.technifutur_evand04.dao.ExpenseTypeDao
import com.adrien.technifutur_evand04.dao.ExpenseTypeWithExpensesDao
import com.adrien.technifutur_evand04.model.Expense
import com.adrien.technifutur_evand04.model.ExpenseType
import com.adrien.technifutur_evand04.model.ExpenseTypeWithExpenses

@Database(entities = arrayOf(Expense::class, ExpenseTypeWithExpenses::class, ExpenseType::class ),version =1)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao() : ExpenseDao
    abstract fun expenseTypeDao() : ExpenseTypeDao
    abstract fun expenseTypeWithExpensesDao() : ExpenseTypeWithExpensesDao


    companion object {

        @Volatile
        private var sharedInstance: ExpenseDatabase? = null

        fun getDB(context: Context) : ExpenseDatabase {
            if (sharedInstance != null) return sharedInstance!!
            synchronized(this) {
                sharedInstance = Room
                    .databaseBuilder(context, ExpenseDatabase::class.java, "expense.db")
                    .fallbackToDestructiveMigration()
                    .build()
                return sharedInstance!!
            }
        }
    }
}

