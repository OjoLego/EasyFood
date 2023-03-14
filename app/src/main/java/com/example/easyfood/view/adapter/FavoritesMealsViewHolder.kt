package com.example.easyfood.view.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.easyfood.R
import com.example.easyfood.databinding.CategoryItemsBinding
import com.example.easyfood.databinding.MealByCategoryBinding
import com.example.easyfood.model.data.MealDetails

private const val TAG = "FavoritesMealsViewHolder"
class FavoritesMealsViewHolder(private val binding: MealByCategoryBinding): RecyclerView.ViewHolder(binding.root) {

    private val favoritesMealImage = binding.mealByCategoryImg
    private val favoritesMealName = binding.mealByCategoryName

    fun bind(mealDetails: MealDetails, favoritesMealsClickListener: FavoritesMealsClickListener){
        mealDetails.strMealThumb?.let { Log.d(TAG,it) }
        Glide.with(binding.mealByCategoryImg.context)
            .load(mealDetails.strMealThumb)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(favoritesMealImage)

        favoritesMealName.text = mealDetails.strMeal

        favoritesMealImage.setOnClickListener { favoritesMealsClickListener.onFavoritesMealsClick(
            mealDetails.idMeal!!, mealDetails.strMeal!!, mealDetails.strMealThumb!!,it) }

        favoritesMealName.setOnClickListener {
            favoritesMealsClickListener.onFavoritesMealsClick(
                mealDetails.idMeal!!, mealDetails.strMeal!!, mealDetails.strMealThumb!!,it)
        }
    }
}