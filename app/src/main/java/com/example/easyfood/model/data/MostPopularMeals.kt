package com.example.easyfood.model.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MostPopularMeals(
    @SerializedName("meals")
    val meals: List<PopularMeal>
)

@Keep
data class PopularMeal(
    @SerializedName("idMeal")
    val idMeal: String?,
    @SerializedName("strMeal")
    val strMeal: String?,
    @SerializedName("strMealThumb")
    val strMealThumb: String?
)