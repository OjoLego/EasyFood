package com.example.easyfood.model.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

// Class of CategoryMeal
@Keep
data class CategoryMeal(
    @SerializedName("idCategory")
    val idCategory: String?,
    @SerializedName("strCategory")
    val strCategory: String?,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String?,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String?
)