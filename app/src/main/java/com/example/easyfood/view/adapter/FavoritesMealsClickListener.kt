package com.example.easyfood.view.adapter

import android.view.View

interface FavoritesMealsClickListener {
    fun onFavoritesMealsClick(idMeal:String, nameMeal:String, thumbMeal:String,view: View)
}