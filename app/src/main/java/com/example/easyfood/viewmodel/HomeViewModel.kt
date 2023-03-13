package com.example.easyfood.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.easyfood.database.MealDatabase
import com.example.easyfood.model.data.*
import com.example.easyfood.model.datasource.EasyFoodRetrofit
import com.example.easyfood.model.repository.EasyFoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query

private const val TAG = "EasyFoodViewModel"
class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val mealDao = MealDatabase.getInstance(application).mealDao()
    private val retrofitService = EasyFoodRetrofit.easyFoodRetrofit
    private val easyFoodRepository = EasyFoodRepository(mealDao,retrofitService)

    private var _getRandomMeal = MutableLiveData<List<Meal>>()
    val getRandomMeal : LiveData<List<Meal>> = _getRandomMeal

    private var _getPopularMeal = MutableLiveData<List<PopularMeal>>()
    val getPopularMeal : LiveData<List<PopularMeal>> = _getPopularMeal

    private var _getCategoriesMeal = MutableLiveData<List<CategoryMeal>>()
    val getCategoriesMeal: LiveData<List<CategoryMeal>> = _getCategoriesMeal

    private var _getMealDetailsId = MutableLiveData<List<MealDetails>>()
    val getMealDetailsId : LiveData<List<MealDetails>> = _getMealDetailsId

    private var _searchMeals = MutableLiveData<List<MealDetails>>()
    val searchMeal: LiveData<List<MealDetails>> = _searchMeals
//    private var _searchMeals = MutableLiveData<List<SearchMeal>>()
//    val searchMeal: LiveData<List<SearchMeal>> = _searchMeals

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

    fun getMealDetailsId(id:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res3 = easyFoodRepository.getMealDetailsId(id)
                Log.d(TAG,res3.meals.toString())
                _getMealDetailsId.postValue(res3.meals)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun searchMeals(searchQuery: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val res = easyFoodRepository.searchMeals(searchQuery)
                Log.d("Search Meals", res.meals.toString())
                _searchMeals.postValue(res.meals)
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}