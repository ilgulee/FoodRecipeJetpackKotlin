package ilgulee.com.foodrecipejetpackkotlin.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.food2fork.com/api/"
private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttp = OkHttpClient.Builder().addInterceptor(logger)
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .client(okHttp.build())
    .build()

interface RecipeApiService {
    @GET("search")
    fun getRecipeSearch(
        @Query("key") API_KEY: String
        , @Query("q") query: String
        , @Query("page") page: Int
    ): Call<String>
}

object RecipeApi {
    val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
//    fun<T> buildService(serviceType:Class<T>): T {
//        return retrofit.create(serviceType)
//    }
}