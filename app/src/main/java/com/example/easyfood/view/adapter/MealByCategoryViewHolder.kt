package com.example.easyfood.view.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.easyfood.R
import com.example.easyfood.databinding.MealByCategoryBinding
import com.example.easyfood.model.data.MealByCategory

private const val TAG = "MealByCategoryViewHolder"
class MealByCategoryViewHolder (private val binding: MealByCategoryBinding): RecyclerView.ViewHolder(binding.root){

    private val mealByCategoryImg = binding.mealByCategoryImg
    private val mealByCategoryName = binding.mealByCategoryName

    fun bind(mealByCategory: MealByCategory){
        mealByCategory.strMealThumb?.let { Log.d(TAG,it)}
        Glide.with(binding.mealByCategoryImg.context)
            .load(mealByCategory.strMealThumb)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(mealByCategoryImg)
        mealByCategoryName.text = mealByCategory.strMeal
    }


}