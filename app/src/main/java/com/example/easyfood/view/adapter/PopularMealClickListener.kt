package com.example.easyfood.view.adapter

import android.view.View

interface PopularMealClickListener {
    fun onPopularMealClick(idMeal:String, nameMeal:String, thumbMeal:String,view:View)
    fun onLongPopularMealClick(idMeal: String, nameMeal:String, thumbMeal:String, view: View)
}