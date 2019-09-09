package ilgulee.com.foodrecipejetpackkotlin.network

import com.squareup.moshi.Json

data class Recipe(
    var title: String? = null,
    var publisher: String? = null,
    var ingredients: Array<String>? = null,
    @Json(name = "recipe_id")
    var rId: String? = null,
    @Json(name = "image_url")
    var imageUrl: String? = null,
    @Json(name = "social_rank")
    var rank: Float = 0.0f
)

data class RecipeSearchResponse(var count: Int = 0, var recipes: List<Recipe>? = null)
data class RecipeGetResponse(val recipe: Recipe)