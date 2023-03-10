package com.example.easyfood.view.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.databinding.FragmentSearchBinding
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.data.SearchMeal
import com.example.easyfood.view.adapter.FavoritesMealsAdapter
import com.example.easyfood.view.adapter.FavoritesMealsClickListener
import com.example.easyfood.viewmodel.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), FavoritesMealsClickListener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var itemsSearchedMeals: MutableList<MealDetails>
    var searchMealsAdapter = FavoritesMealsAdapter(this)
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewSearch()
        onSearchArrowClick()
        observeSearchMeal()

        var searchJob: Job ?= null
        binding.etSearchBox.addTextChangedListener {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500)
                homeViewModel.searchMeals(it.toString())
            }
        }
    }

    private fun observeSearchMeal() {
        homeViewModel.searchMeal.observe(viewLifecycleOwner){
            itemsSearchedMeals = it.toMutableList()
            searchMealsAdapter.setFavoritesMealsList(itemsSearchedMeals)
//            searchMealsAdapter.setSearchedMealsList(itemsSearchedMeals)
        }
    }

    private fun onSearchArrowClick() {
        binding.imgSearchArrow.setOnClickListener { searchMeals() }
    }

    private fun searchMeals() {
        val searchQuery = binding.etSearchBox.text.toString()
        if(searchQuery.isNotEmpty()){
            homeViewModel.searchMeals(searchQuery)
        }
    }

    private fun initRecyclerViewSearch() {
        binding.rvSearchedMeals.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = searchMealsAdapter
        }
    }

    override fun onFavoritesMealsClick(
        idMeal: String,
        nameMeal: String,
        thumbMeal: String,
        view: View
    ) {

    }
}