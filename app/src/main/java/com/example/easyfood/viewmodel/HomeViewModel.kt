package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.PopularMeal
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.datasource.EasyFoodRetrofit
import com.example.easyfood.model.repository.EasyFoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "EasyFoodViewModel"
class HomeViewModel():ViewModel() {

    private val retrofitService = EasyFoodRetrofit.easyFoodRetrofit
    private val easyFoodRepository = EasyFoodRepository(retrofitService)

    private var _getRandomMeal = MutableLiveData<List<Meal>>()
    val getRandomMeal : LiveData<List<Meal>> = _getRandomMeal

    private var _getPopularMeal = MutableLiveData<List<PopularMeal>>()
    val getPopularMeal : LiveData<List<PopularMeal>> = _getPopularMeal

    fun getRandomMeal(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = easyFoodRepository.getRandomMeal()
                Log.d(TAG, res.meals.toString())
                _getRandomMeal.postValue(res.meals)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getMostPopularMeals(category: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res1 = easyFoodRepository.getMostPopularMeals(category)
                Log.d("RandomMeals",res1.meals.toString())
                _getPopularMeal.postValue(res1.meals)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


}