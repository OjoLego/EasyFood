package com.example.easyfood.model.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

// Class of List of CategoryMeal
@Keep
data class CategoryMealList (
    @SerializedName("categories")
    val categories: List<CategoryMeal>
    )