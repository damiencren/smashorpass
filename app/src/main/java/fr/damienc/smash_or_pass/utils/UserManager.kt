import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.damienc.smash_or_pass.models.UserData
import fr.damienc.smash_or_pass.models.UserResponse
import fr.damienc.smash_or_pass.models.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object UserManager {
    private const val BASE_URL = "https://smash.antoinejosset.fr/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val userService = retrofit.create(UserService::class.java)

    suspend fun createUser(name: String, mail: String, password: String): UserResponse? {
        val userData = UserData(name, mail, password)
        val response = userService.createUser(userData)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getUser(username: String, password: String): UserResponse? {
        val response = userService.getUser(username, password)
        Log.d(TAG, response.toString())
        return if (response.isSuccessful) {

            response.body()
        } else {
            null
        }
    }

    fun saveToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }
}
