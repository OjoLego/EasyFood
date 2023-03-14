package com.example.easyfood.model.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MealByCategoryList(
    @SerializedName("meals")
    val meals: List<MealByCategory>
)

    @Keep
    data class MealByCategory(
        @SerializedName("idMeal")
        val idMeal: String?,
        @SerializedName("strMeal")
        val strMeal: String?,
        @SerializedName("strMealThumb")
        val strMealThumb: String?
    )
