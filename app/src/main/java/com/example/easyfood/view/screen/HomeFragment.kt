package com.example.easyfood.view.screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.model.data.CategoryMeal
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.PopularMeal
import com.example.easyfood.view.adapter.CategoryMealAdapter
import com.example.easyfood.view.adapter.CategoryMealClickListener
import com.example.easyfood.view.adapter.MostPopularAdapter
import com.example.easyfood.view.adapter.PopularMealClickListener
import com.example.easyfood.viewmodel.HomeViewModel

class HomeFragment : Fragment(), PopularMealClickListener,CategoryMealClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var itemPopularMeals: MutableList<PopularMeal>
    private lateinit var itemCategoriesMeal: MutableList<CategoryMeal>
    var mostPopularAdapter = MostPopularAdapter(this)
    var categoryMealAdapter = CategoryMealAdapter(this)

    companion object{
        const val MEAL_ID = "com.example.easyfood.view.screen.idMeal"
        const val MEAL_NAME = "com.example.easyfood.view.screen.nameMeal"
        const val MEAL_THUMB = "com.example.easyfood.view.screen.thumbMeal"
        const val MEAL_CATEGORY = "Seafood"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate( inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        homeViewModel.getMostPopularMeals(MEAL_CATEGORY)
        observerPopularMeals()
        initRecyclerViewPopular()

        homeViewModel.getCategoriesMeal()
        observerCategoriesMeals()
        initRecyclerViewCategories()

        onSearchEtItemClick()
    }

    private fun onSearchEtItemClick() {
        binding.imgSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun initRecyclerViewCategories() {
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(activity,3,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealAdapter
        }
    }

    private fun observerCategoriesMeals() {
        homeViewModel.getCategoriesMeal.observe(viewLifecycleOwner){
            itemCategoriesMeal = it.toMutableList()
            categoryMealAdapter.setCategoriesMealList(itemCategoriesMeal)
        }
    }

    private fun initRecyclerViewPopular() {
        binding.recViewMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = mostPopularAdapter
        }
    }

    private fun observerPopularMeals() {
        homeViewModel.getPopularMeal.observe(viewLifecycleOwner){
            itemPopularMeals = it.toMutableList()
            mostPopularAdapter.setMostPopularMealsDetails(itemPopularMeals)
        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
       homeViewModel.getRandomMeal.observe(viewLifecycleOwner){

           Glide.with(this@HomeFragment)
               .load(
                   it[0].strMealThumb
               )
               .into(binding.imgRandomView)
           this.randomMeal = it[0]
       }
    }

    override fun onPopularMealClick(
        idMeal: String,
        nameMeal: String,
        thumbMeal: String,
        view: View
    ) {
        val intent = Intent(activity,MealActivity::class.java)
        intent.putExtra(MEAL_ID,idMeal)
        intent.putExtra(MEAL_NAME,nameMeal)
        intent.putExtra(MEAL_THUMB,thumbMeal)
        startActivity(intent)
    }

    override fun onLongPopularMealClick(
        idMeal: String,
        nameMeal: String,
        thumbMeal: String,
        view: View
    ) {
        val mealBottomSheetFragment = MealBottomSheetFragment.newInstance(idMeal)
        mealBottomSheetFragment.show(childFragmentManager, "Meal Info")
    }

    override fun onCategoryMealClick(categoryName: String, view: View) {
        val intent = Intent(activity,CategoryMealsActivity::class.java)
        intent.putExtra(MEAL_NAME,categoryName)
        startActivity(intent)
    }

}