package com.example.easyfood.view.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.databinding.MealByCategoryBinding
import com.example.easyfood.model.data.MealByCategory
import com.example.easyfood.view.adapter.MealByCategoryAdapter
import com.example.easyfood.viewmodel.CategoryMealViewModel
import com.example.easyfood.viewmodel.MealViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryName: String
    private lateinit var itemMealByCategory: MutableList<MealByCategory>
    private lateinit var categoryMealViewModel: CategoryMealViewModel
    var mealByCategoryAdapter = MealByCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryMealViewModel = ViewModelProviders.of(this)[CategoryMealViewModel::class.java]

        getInformationFromIntent()
        categoryMealViewModel.getMealByCategory(categoryName)
        observeMealByCategory()
        initRecViewMealByCategory()

    }

    private fun getInformationFromIntent() {
        val intent = intent
        categoryName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
    }

    private fun initRecViewMealByCategory() {
        binding.rvCategoryMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL, false)
            adapter = mealByCategoryAdapter
        }
    }

    private fun observeMealByCategory() {
        categoryMealViewModel.getMealByCategory.observe(this){
            itemMealByCategory = it.toMutableList()
            mealByCategoryAdapter.setMealByCategoryList(itemMealByCategory)
            binding.tvCategoryCount.text = it.size.toString()
        }
    }
}