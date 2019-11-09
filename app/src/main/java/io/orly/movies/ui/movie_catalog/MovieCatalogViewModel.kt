package io.orly.movies.ui.movie_catalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import io.orly.movies.data.repository.MovieRepository
import javax.inject.Inject

class MovieCatalogViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {
    val categoryId = MutableLiveData(0)
    val movies = categoryId.switchMap { id ->
        movieRepository.loadMovieByCategory(
            id
        )
    }

}