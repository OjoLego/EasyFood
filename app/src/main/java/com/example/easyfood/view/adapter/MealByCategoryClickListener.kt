package com.example.easyfood.view.adapter

import android.view.View

interface MealByCategoryClickListener {
    fun onMealByCategoryClick(idMeal:String, nameMeal:String, thumbMeal:String, view: View)
}