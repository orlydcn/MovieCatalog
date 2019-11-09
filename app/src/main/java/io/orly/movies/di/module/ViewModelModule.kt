package io.orly.movies.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.orly.movies.di.ViewModelKey
import io.orly.movies.ui.category.CategoryViewModel
import io.orly.movies.ui.movie.MovieViewModel
import io.orly.movies.ui.movie_catalog.MovieCatalogViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindCategoryViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieCatalogViewModel::class)
    abstract fun bindMovieCatalogViewModel(viewModel: MovieCatalogViewModel): ViewModel
}