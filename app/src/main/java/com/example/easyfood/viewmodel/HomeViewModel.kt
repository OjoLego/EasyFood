package com.example.easyfood.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.easyfood.database.MealDatabase
import com.example.easyfood.model.data.CategoryMeal
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.data.PopularMeal
import com.example.easyfood.model.datasource.EasyFoodRetrofit
import com.example.easyfood.model.repository.EasyFoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "EasyFoodViewModel"
class HomeViewModel(
    application: Application
): AndroidViewModel(
    application
) {

    private val mealDao = MealDatabase.getInstance(application).mealDao()
    private val retrofitService = EasyFoodRetrofit.easyFoodRetrofit
    private val easyFoodRepository = EasyFoodRepository(mealDao,retrofitService)

    private var _getRandomMeal = MutableLiveData<List<Meal>>()
    val getRandomMeal : LiveData<List<Meal>> = _getRandomMeal

    private var _getPopularMeal = MutableLiveData<List<PopularMeal>>()
    val getPopularMeal : LiveData<List<PopularMeal>> = _getPopularMeal

    private var _getCategoriesMeal = MutableLiveData<List<CategoryMeal>>()
    val getCategoriesMeal: LiveData<List<CategoryMeal>> = _getCategoriesMeal

    val readAllMeals: LiveData<List<MealDetails>> = easyFoodRepository.readAllMeals

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

    fun getCategoriesMeal(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res2 = easyFoodRepository.getCategoriesMeal()
                Log.d("CategoriesMeal", res2.categories.toString())
                _getCategoriesMeal.postValue(res2.categories)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun insertMeal(mealDetails: MealDetails){
        viewModelScope.launch(Dispatchers.IO) {
            easyFoodRepository.saveRandomMeal(mealDetails)
        }
    }

    fun deleteMeal(mealDetails: MealDetails){
        viewModelScope.launch {
            easyFoodRepository.deleteRandomMeal(mealDetails)
        }
    }


}