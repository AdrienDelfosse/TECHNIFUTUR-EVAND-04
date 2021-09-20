package com.adrien.technifutur_evand04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrien.technifutur_evand04.databinding.ActivityMainBinding
import com.adrien.technifutur_evand04.model.Expense
import com.adrien.technifutur_evand04.ui.Adapter
import com.adrien.technifutur_evand04.viewmodel.ExpenseListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private lateinit var expenseListViewModel : ExpenseListViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expenseListViewModel = ViewModelProvider(this).get(ExpenseListViewModel::class.java)
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        expenseListViewModel.getExpenses(this).observeForever{
            updateDataWithExpenses(it)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.add) {
            startActivity(Intent(this, AddActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateDataWithExpenses(expenses : List<Expense>) {
        adapter = Adapter(expenses)

        binding.recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}