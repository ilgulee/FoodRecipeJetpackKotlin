package ilgulee.com.foodrecipejetpackkotlin.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ilgulee.com.foodrecipejetpackkotlin.network.RecipeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "e00db76169ba77efdb957e24d31d81c1"

class RecipeListViewModel : ViewModel() {
    private var query = "chicken breast"
    private var page = 2
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() {
            return _response
        }

    init {
        getResponse()
    }

    private fun getResponse() {
        RecipeApi.retrofitService.getRecipeSearch(API_KEY, query, page)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = t.message
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        _response.value = response.body()
                    }
                }

            })
    }
}