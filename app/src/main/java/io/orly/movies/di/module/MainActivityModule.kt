package io.orly.movies.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.orly.movies.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}