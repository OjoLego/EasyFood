package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.RandomMeal
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
}