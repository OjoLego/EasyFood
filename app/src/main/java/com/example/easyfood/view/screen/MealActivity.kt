package com.example.easyfood.view.screen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.data.RandomMeal
import com.example.easyfood.viewmodel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var youtubeLink:String
    private lateinit var mealViewModel: MealViewModel

    private var mealToSave:MealDetails ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealViewModel = ViewModelProviders.of(this)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationInViews()
        loadingCase()
        mealViewModel.getMealDetailsId(mealId)
        observeMealDetailsId()
        onYoutubeImageClick()

        onFavoriteClick()
        getFavoritesMealInfoFromIntent()

        getCategoriesMealInfoFromIntent()
        getSearchedMealInfoFromIntent()
    }

    private fun getSearchedMealInfoFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(SearchFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(SearchFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(SearchFragment.MEAL_THUMB)!!
    }

    private fun getCategoriesMealInfoFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(CategoryMealsActivity.MEAL_ID)!!
        mealName = intent.getStringExtra(CategoryMealsActivity.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(CategoryMealsActivity.MEAL_THUMB)!!
    }

    private fun getFavoritesMealInfoFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(FavoritesFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(FavoritesFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(FavoritesFragment.MEAL_THUMB)!!
    }

    private fun onFavoriteClick() {
        binding.btnAddToFavorites.setOnClickListener {
            mealToSave?.let {
                mealViewModel.insertMeal(it)
                Toast.makeText(this,"Meal Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onYoutubeImageClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observeMealDetailsId() {
        mealViewModel.getMealDetailsId.observe(this){
            onResponseCase()

            mealToSave = it[0]

            binding.tvCategory.text = "Category: ${it[0].strCategory}"
            binding.tvArea.text = "Area: ${it[0].strArea}"
            binding.tvInstructionsSteps.text = it[0].strInstructions

            youtubeLink = it[0].strYoutube.toString()
        }
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.btnAddToFavorites.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun onResponseCase(){
        binding.btnAddToFavorites.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }
}