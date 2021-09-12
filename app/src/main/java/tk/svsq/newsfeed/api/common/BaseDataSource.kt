package tk.svsq.newsfeed.api.common

import android.util.Log
import retrofit2.Response
import tk.svsq.newsfeed.data.Result

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    private val TAG = javaClass.canonicalName

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {

        /** We can deserialize error model (in case we get error msg from server)
         * and pass the message */
        Log.e(TAG, message)
        return Result.Error("Network error: $message")
    }

}