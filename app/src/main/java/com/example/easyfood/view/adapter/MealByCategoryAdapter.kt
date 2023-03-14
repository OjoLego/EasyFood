package com.example.easyfood.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.MealByCategoryBinding
import com.example.easyfood.model.data.MealByCategory


class MealByCategoryAdapter(var mealByCategoryClickListener: MealByCategoryClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var updatedMealByCategoryList = mutableListOf<MealByCategory>()

    fun setMealByCategoryList(itemsMealByCategory: List<MealByCategory>){
        updatedMealByCategoryList = itemsMealByCategory.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MealByCategoryViewHolder(
            MealByCategoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MealByCategoryViewHolder -> {
                holder.bind(
                    updatedMealByCategoryList[position],
                    mealByCategoryClickListener
                )
            }
        }
    }

    override fun getItemCount(): Int {
       return updatedMealByCategoryList.size
    }
}