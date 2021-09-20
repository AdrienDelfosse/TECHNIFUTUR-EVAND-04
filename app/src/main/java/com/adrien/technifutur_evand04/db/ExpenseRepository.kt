package com.adrien.technifutur_evand04.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.adrien.technifutur_evand04.model.Expense
import com.adrien.technifutur_evand04.model.ExpenseType
import com.adrien.technifutur_evand04.model.ExpenseTypeWithExpenses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseRepository {
    companion object {
        var expenseDatabase: ExpenseDatabase? = null
        var expenseType: LiveData<ExpenseTypeWithExpenses>? = null


        fun initializeDB(context: Context) : ExpenseDatabase {
            val db = ExpenseDatabase.getDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val genres = expenseDatabase!!.expenseTypeDao().getAllType()
                if(genres.isNullOrEmpty()) {
                    expenseDatabase!!.expenseTypeDao().insert(ExpenseType(name = "Food"))
                    expenseDatabase!!.expenseTypeDao().insert(ExpenseType(name = "Home"))
                    expenseDatabase!!.expenseTypeDao().insert(ExpenseType(name = "Other"))
                    expenseDatabase!!.expenseTypeDao().insert(ExpenseType(name = "Car"))

                }
            }
            return db
        }


        fun insertExpense(
            context: Context,
            date: String,
            name: String,
            value: Float,
            selectedType: ExpenseType
        ) {
            expenseDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {



                expenseDatabase!!.expenseDao().insert(Expense(date = date , name = name, value = value, typeId = selectedType.expenseTypeId))
            }
        }





        fun getAllExpense(context: Context): LiveData<List<Expense>> {
            if(expenseDatabase == null) {
                expenseDatabase = initializeDB(context)
            }
            return expenseDatabase!!.expenseDao().getAll()
        }

        fun getAllTypes(context: Context): LiveData<List<ExpenseType>> {
            if(expenseDatabase == null) {
                expenseDatabase = initializeDB(context)
            }
            return expenseDatabase!!.expenseTypeDao().getAll()
        }


    }
}