package io.orly.movies.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.orly.movies.ui.category.CategoryFragment
import io.orly.movies.ui.movie.MovieFragment
import io.orly.movies.ui.movie_catalog.MovieCatalogFragment

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieCatalogFragment(): MovieCatalogFragment
}