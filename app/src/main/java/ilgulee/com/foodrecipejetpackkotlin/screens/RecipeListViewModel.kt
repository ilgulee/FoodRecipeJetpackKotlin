package ilgulee.com.foodrecipejetpackkotlin.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ilgulee.com.foodrecipejetpackkotlin.network.Recipe
import ilgulee.com.foodrecipejetpackkotlin.network.RecipeApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val API_KEY = "e00db76169ba77efdb957e24d31d81c1"

class RecipeListViewModel : ViewModel() {
    private var query = "chicken breast"
    private var page = 2

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() {
            return _recipes
        }

    init {
        getResponseSearch()
    }

    private fun getResponseSearch() {
        coroutineScope.launch {
            val recipesDeferred = RecipeApi.retrofitService.getRecipeSearch(API_KEY, query, page)
            try {
                val recipeSearchResponse = recipesDeferred.await()
                _recipes.value = recipeSearchResponse.recipes
            } catch (e: Exception) {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}