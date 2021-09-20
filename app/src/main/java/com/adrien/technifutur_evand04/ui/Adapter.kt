package com.adrien.technifutur_evand04.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrien.technifutur_evand04.databinding.ItemBinding
import com.adrien.technifutur_evand04.model.Expense

class Adapter(
    model: List<Expense>
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var binding: ItemBinding
    private var expenseList = mutableListOf<Expense>()

    init {
        expenseList.clear()
        expenseList.addAll(model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =   ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetModel = expenseList[position]
        holder.bind(assetModel)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    class ViewHolder(
        private var viewBinding: ItemBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {



        fun bind(model: Expense) {
            viewBinding.dateTextView.text = model.date.toString()
            viewBinding.nameTextView.text = model.name
            viewBinding.valueTextView.text = "${model.value}$"
        }

        fun unbind() {
            viewBinding.dateTextView.text = null
            viewBinding.nameTextView.text = null
            viewBinding.valueTextView.text = null
        }
    }
}