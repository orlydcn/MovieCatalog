package io.orly.movies.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.orly.movies.data.remote.LiveDataCallAdapterFactory
import io.orly.movies.data.remote.MovieService
import io.orly.movies.util.AppConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Reusable
    @Provides
    fun provideMovieService(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): MovieService =
        Retrofit.Builder()
            .baseUrl(AppConstant.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(MovieService::class.java)


}