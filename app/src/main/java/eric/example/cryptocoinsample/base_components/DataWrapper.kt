package eric.example.cryptocoinsample.base_components

class DataWrapper<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T?): DataWrapper<T> = DataWrapper(Status.SUCCESS, data, null)

        fun <T> error(message: String?, data: T? = null): DataWrapper<T> =
            DataWrapper(Status.ERROR, data, message)

        fun <T> loading(data: T? = null): DataWrapper<T> = DataWrapper(Status.LOADING, data, null)
    }
}