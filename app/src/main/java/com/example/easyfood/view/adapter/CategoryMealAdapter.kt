package com.example.easyfood.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.CategoryItemsBinding
import com.example.easyfood.model.data.CategoryMeal

class CategoryMealAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var updatedCategoryMealList = mutableListOf<CategoryMeal>()

    fun setCategoriesMealList(itemsCategoriesMeal: List<CategoryMeal>){
        updatedCategoryMealList = itemsCategoriesMeal.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryMealViewHolder(
            CategoryItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CategoryMealViewHolder -> {
                holder.bind(
                    updatedCategoryMealList[position]
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return updatedCategoryMealList.size
    }


}