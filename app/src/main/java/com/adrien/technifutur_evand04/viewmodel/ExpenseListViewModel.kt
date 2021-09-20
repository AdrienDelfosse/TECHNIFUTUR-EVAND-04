package com.adrien.technifutur_evand04.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adrien.technifutur_evand04.db.ExpenseRepository
import com.adrien.technifutur_evand04.model.Expense

internal class ExpenseListViewModel: ViewModel() {
    fun getExpenses(context: Context): LiveData<List<Expense>> {
        return ExpenseRepository.getAllExpense(context)
    }
}