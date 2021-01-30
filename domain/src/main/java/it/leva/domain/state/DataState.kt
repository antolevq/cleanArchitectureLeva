package it.leva.domain.state

sealed class DataState<T>(
    var loading: Boolean,
    var data: T? = null,
    var errorMessage: String?=null
) {
    class LOADING<T>(
        isLoading: Boolean,
        cachedData: T? = null
    ) : DataState<T>(
        loading = isLoading,
        data = cachedData
    )

    class SUCCESS<T>(
        data: T? = null,
        errorMessage: String?=null
    ) : DataState<T>(
        loading = false,
        data = data,
        errorMessage = errorMessage
)

    class ERROR<T>(
        errorMessage: String
    ) : DataState<T>(
        loading = false,
        data = null,
        errorMessage = errorMessage
    )
}