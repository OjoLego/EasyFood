package com.example.easyfood.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.easyfood.database.MealDatabase
import com.example.easyfood.model.data.Meal
import com.example.easyfood.model.data.MealDetails
import com.example.easyfood.model.datasource.EasyFoodRetrofit
import com.example.easyfood.model.repository.EasyFoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MealViewModel"
class MealViewModel(
    application: Application
//    val mealDatabase: MealDatabase
    ): AndroidViewModel(
    application
) {
    private val mealDao = MealDatabase.getInstance(application).mealDao()
    private val retrofitService = EasyFoodRetrofit.easyFoodRetrofit
    private val easyFoodRepository = EasyFoodRepository(mealDao,retrofitService)

    private var _getMealDetailsId = MutableLiveData<List<MealDetails>>()
    val getMealDetailsId : LiveData<List<MealDetails>> = _getMealDetailsId

    fun getMealDetailsId(id:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = easyFoodRepository.getMealDetailsId(id)
                Log.d(TAG,res.meals.toString())
                _getMealDetailsId.postValue(res.meals)
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
}