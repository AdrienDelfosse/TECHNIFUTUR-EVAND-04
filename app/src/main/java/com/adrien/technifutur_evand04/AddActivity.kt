package com.adrien.technifutur_evand04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.adrien.technifutur_evand04.databinding.ActivityAddBinding
import com.adrien.technifutur_evand04.db.ExpenseRepository
import com.adrien.technifutur_evand04.model.ExpenseType
import java.util.*

class AddActivity : AppCompatActivity() {
    private lateinit var types: List<ExpenseType>
    private lateinit var selectedType: ExpenseType

    lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ExpenseRepository.getAllTypes(this).observe(this){
            this.types = it

        }


        binding.expenseTypeEditText.setOnClickListener{
            val array= types.map {it.name}.toTypedArray()

            val builder = AlertDialog.Builder(this)



            builder.setTitle("Choose a Type.")
            builder.setSingleChoiceItems(array, -1) { dialog, i-> }
            builder.setPositiveButton("Submit"){dialog,which->
                val position = (dialog as AlertDialog).listView.checkedItemPosition

                if (position !=-1){
                    selectedType = types[position]
                    binding.expenseTypeEditText.setText("Type : ${selectedType}")
                }
            }
            val dialog = builder.create()
            dialog.show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.saveExpense) {
            val name = binding.nameEditText.text.toString()
            val date = binding.dateEditText.text.toString()
            val value = binding.valueEditText.text.toString().toFloat()

            if(name.isNotBlank() && date.isNotBlank() && value != null) {
                ExpenseRepository.insertExpense(this, name = name, date = date, value = value, selectedType = selectedType)
                finish()
            } else {
                Toast.makeText(this, "Invalid Data", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}