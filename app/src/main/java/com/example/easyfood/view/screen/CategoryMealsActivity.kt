package com.example.easyfood.view.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.model.data.MealByCategory
import com.example.easyfood.view.adapter.MealByCategoryAdapter
import com.example.easyfood.view.adapter.MealByCategoryClickListener
import com.example.easyfood.viewmodel.CategoryMealViewModel

class CategoryMealsActivity : AppCompatActivity(), MealByCategoryClickListener {

    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryName: String
    private lateinit var itemMealByCategory: MutableList<MealByCategory>
    private lateinit var categoryMealViewModel: CategoryMealViewModel
    var mealByCategoryAdapter = MealByCategoryAdapter(this)

    companion object{
        const val MEAL_ID = "com.example.easyfood.view.screen.idMeal"
        const val MEAL_NAME = "com.example.easyfood.view.screen.nameMeal"
        const val MEAL_THUMB = "com.example.easyfood.view.screen.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryMealViewModel = ViewModelProviders.of(this)[CategoryMealViewModel::class.java]

        getInformationFromIntentCategories()
        getInformationFromIntentHome()
        categoryMealViewModel.getMealByCategory(categoryName)
        observeMealByCategory()
        initRecViewMealByCategory()

    }

    private fun getInformationFromIntentCategories() {
        val intent = intent
        categoryName = intent.getStringExtra(CategoriesFragment.MEAL_NAME)!!
    }

    private fun getInformationFromIntentHome() {
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

    override fun onMealByCategoryClick(
        idMeal: String,
        nameMeal: String,
        thumbMeal: String,
        view: View
    ) {
        val intent = Intent(this@CategoryMealsActivity, MealActivity::class.java)
        intent.putExtra(MEAL_ID,idMeal)
        intent.putExtra(MEAL_NAME,nameMeal)
        intent.putExtra(MEAL_THUMB,thumbMeal)
        startActivity(intent)
    }
}