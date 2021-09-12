package tk.svsq.newsfeed.data

sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val message: String? = "Unknown error", val data: T? = null): Result<T>()
}
