package com.example.easyfood.view.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.CategoryItemsBinding
import com.example.easyfood.databinding.MealByCategoryBinding
import com.example.easyfood.model.data.CategoryMeal
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.data.SearchMeal

class FavoritesMealsAdapter(var favoritesMealsClickListener: FavoritesMealsClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var updatedFavoritesMealsList = mutableListOf<MealDetails>()

    fun setFavoritesMealsList(itemsFavoritesMeals: List<MealDetails>){
        updatedFavoritesMealsList = itemsFavoritesMeals.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoritesMealsViewHolder(
            MealByCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FavoritesMealsViewHolder -> {
                    holder.bind(
                        updatedFavoritesMealsList[position],
                        favoritesMealsClickListener
                    )
            }
        }
    }

    override fun getItemCount(): Int {
        return updatedFavoritesMealsList.size
    }
}