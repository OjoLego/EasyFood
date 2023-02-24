package com.example.easyfood.model.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoryMealList (
    @SerializedName("categories")
    val categories: List<CategoryMeal>
    )