package ilgulee.com.foodrecipejetpackkotlin.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ilgulee.com.foodrecipejetpackkotlin.network.RecipeApi
import ilgulee.com.foodrecipejetpackkotlin.network.RecipeGetResponse
import ilgulee.com.foodrecipejetpackkotlin.network.RecipeSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "e00db76169ba77efdb957e24d31d81c1"

class RecipeListViewModel : ViewModel() {
    private var query = "chicken breast"
    private var page = 2
    private var recipe_id = "35382"
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() {
            return _response
        }

    init {
//        getResponseSearch()
        getResponseGet()
    }

    private fun getResponseGet() {
        RecipeApi.retrofitService.getRecipeGet(API_KEY, recipe_id)
            .enqueue(object : Callback<RecipeGetResponse> {
                override fun onFailure(call: Call<RecipeGetResponse>, t: Throwable) {
                    _response.value = t.message
                }

                override fun onResponse(
                    call: Call<RecipeGetResponse>,
                    response: Response<RecipeGetResponse>
                ) {
                    if (response.isSuccessful) {
                        _response.value = "Responsed recipe is ${response.body()}"
                    }
                }

            })
    }

    private fun getResponseSearch() {
        RecipeApi.retrofitService.getRecipeSearch(API_KEY, query, page)
            .enqueue(object : Callback<RecipeSearchResponse> {
                override fun onFailure(call: Call<RecipeSearchResponse>, t: Throwable) {
                    _response.value = t.message
                }

                override fun onResponse(
                    call: Call<RecipeSearchResponse>,
                    response: Response<RecipeSearchResponse>
                ) {
                    if (response.isSuccessful) {
                        _response.value =
                            "Responsed recipes are ${response.body()?.recipes?.get(0).toString()}"
                    }
                }

            })
    }
}