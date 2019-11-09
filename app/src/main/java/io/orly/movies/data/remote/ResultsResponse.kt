package io.orly.movies.data.remote

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(
    @SerializedName("count")
    val ok: Boolean,
    @SerializedName("data")
    val data: List<T>
)