import android.content.Context
import com.ehsieh2.letswatchtv.MoviesResponse
import com.google.gson.Gson

object AssetJsonReader {
    fun getMoviesFromJson(context: Context, fileName: String): MoviesResponse {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonString, MoviesResponse::class.java)
    }
}
