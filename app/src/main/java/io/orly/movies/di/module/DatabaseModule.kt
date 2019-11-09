package io.orly.movies.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.orly.movies.data.local.AppDatabase
import io.orly.movies.util.AppConstant
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppConstant.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideCategoryDao(db: AppDatabase) = db.categoryDao()

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

}