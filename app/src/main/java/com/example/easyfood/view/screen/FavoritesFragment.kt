package com.example.easyfood.view.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.R
import com.example.easyfood.databinding.FragmentFavoritesBinding
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.view.adapter.FavoritesMealsAdapter
import com.example.easyfood.view.adapter.FavoritesMealsClickListener
import com.example.easyfood.viewmodel.HomeViewModel
import com.example.easyfood.viewmodel.MealViewModel
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment(),FavoritesMealsClickListener {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var itemsFavoritesMeals: MutableList<MealDetails>
    var favoritesMealsAdapter = FavoritesMealsAdapter(this)
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        const val MEAL_ID = "com.example.easyfood.view.screen.idMeal"
        const val MEAL_NAME = "com.example.easyfood.view.screen.nameMeal"
        const val MEAL_THUMB = "com.example.easyfood.view.screen.thumbMeal"
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
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.readAllMeals
        observeFavorites()
        initRecyclerViewFavorites()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val mealDetails = favoritesMealsAdapter.updatedFavoritesMealsList[position]
                homeViewModel.deleteMeal(mealDetails)
                Snackbar.make(requireView(), "Meal deleted", Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {
                        homeViewModel.insertMeal(mealDetails)
                    }
                ).show()
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavorites)
    }

    private fun initRecyclerViewFavorites() {
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = favoritesMealsAdapter
        }
    }

    private fun observeFavorites() {
        homeViewModel.readAllMeals.observe(viewLifecycleOwner){
            it.forEach { Log.d("Favorites",it.idMeal) }
            itemsFavoritesMeals = it.toMutableList()
            favoritesMealsAdapter.setFavoritesMealsList(itemsFavoritesMeals)
        }
    }

    override fun onFavoritesMealsClick(
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

}