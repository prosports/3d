import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

// Data classes for requests and responses
data class ConvertRequest(val amount: Double, val fromCurrency: String, val toCurrency: String)
data class ConversionResponse(val result: Double)
data class UploadResponse(val success: Boolean, val message: String)

// Retrofit API service interface
interface ApiService {
    @POST("convert")
    fun convert(@Body request: ConvertRequest): Call<ConversionResponse>

    @GET("models")
    fun getModels(): Call<List<UploadResponse>>
}

object ApiClient {
    private const val BASE_URL = "https://api.example.com/"

    private val logging = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}