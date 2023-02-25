package com.example.easyfood.view.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.easyfood.R
import com.example.easyfood.databinding.CategoryItemsBinding
import com.example.easyfood.model.data.CategoryMeal

private const val TAG = "CategoryMealViewHolder"
class CategoryMealViewHolder(private val binding: CategoryItemsBinding): RecyclerView.ViewHolder(binding.root) {

    private val categoryMealImage = binding.imgCategory
    private val categoryMealName = binding.tvCategoryName

    fun bind(categoryMeal: CategoryMeal, categoryMealClickListener: CategoryMealClickListener){
        categoryMeal.strCategoryThumb?.let { Log.d(TAG, it) }
        Glide.with(binding.imgCategory.context)
            .load(categoryMeal.strCategoryThumb)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(categoryMealImage)
        categoryMealName.text = categoryMeal.strCategory

        categoryMealImage.setOnClickListener { categoryMealClickListener.onCategoryMealClick(
            categoryMeal.strCategory!!,it
        ) }
        categoryMealName.setOnClickListener { categoryMealClickListener.onCategoryMealClick(
            categoryMeal.strCategory!!,it
        ) }
    }
}