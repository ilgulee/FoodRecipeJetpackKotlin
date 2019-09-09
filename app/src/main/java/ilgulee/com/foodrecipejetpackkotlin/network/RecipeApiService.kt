package ilgulee.com.foodrecipejetpackkotlin.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.food2fork.com/api/"
private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttp = OkHttpClient.Builder().addInterceptor(logger)
private val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(okHttp.build())
    .build()

interface RecipeApiService {
    @GET("search")
    fun getRecipeSearch(
        @Query("key") API_KEY: String
        , @Query("q") query: String
        , @Query("page") page: Int
    ): Deferred<RecipeSearchResponse>

    @GET("get")
    fun getRecipeGet(
        @Query("key") API_KEY: String
        , @Query("rId") recipe_id: String
    ): Deferred<RecipeGetResponse>
}

object RecipeApi {
    val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
//    fun<T> buildService(serviceType:Class<T>): T {
//        return retrofit.create(serviceType)
//    }
}