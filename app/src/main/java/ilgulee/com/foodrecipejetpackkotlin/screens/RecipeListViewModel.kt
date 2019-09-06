package ilgulee.com.foodrecipejetpackkotlin.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeListViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() {
            return _response
        }

    init {
        getResponse()
    }

    private fun getResponse() {
        _response.value = "data from viewModel!"
    }
}