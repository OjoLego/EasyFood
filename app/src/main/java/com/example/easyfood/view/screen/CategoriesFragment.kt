package com.example.easyfood.view.screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.R
import com.example.easyfood.databinding.FragmentCategoriesBinding
import com.example.easyfood.model.data.CategoryMeal
import com.example.easyfood.view.adapter.CategoryMealAdapter
import com.example.easyfood.view.adapter.CategoryMealClickListener
import com.example.easyfood.viewmodel.HomeViewModel

class CategoriesFragment : Fragment(), CategoryMealClickListener {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var itemCategoriesFragment: MutableList<CategoryMeal>
    var categoriesFragmentAdapter = CategoryMealAdapter(this)

    companion object{
        const val MEAL_NAME = "com.example.easyfood.view.screen.nameMeal"
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
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getCategoriesMeal()
        observeCategoriesFragment()
        initRecViewCategoriesFragment()
    }

    private fun observeCategoriesFragment() {
        homeViewModel.getCategoriesMeal.observe(viewLifecycleOwner){
            itemCategoriesFragment = it.toMutableList()
            categoriesFragmentAdapter.setCategoriesMealList(itemCategoriesFragment)
        }
    }

    private fun initRecViewCategoriesFragment() {
        binding.rvCategoriesFragment.apply {
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoriesFragmentAdapter
        }
    }

    override fun onCategoryMealClick(categoryName: String, view: View) {
        val intent = Intent(activity, CategoryMealsActivity::class.java)
        intent.putExtra(MEAL_NAME,categoryName)
        startActivity(intent)
    }
}