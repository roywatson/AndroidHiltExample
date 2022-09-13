package com.delasystems.androidhiltexampla.di

import android.content.Context
import com.delasystems.androidhiltexampla.RoysApplication
import com.delasystems.androidhiltexample.data.RoyFiler
import com.delasystems.androidhiltexample.data.RoysRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoysModule {

    @Singleton
    @Provides
    fun providesRoysRepository(filer: RoyFiler) : RoysRepository {
        return RoysRepository(filer)
    }

    @Singleton
    @Provides
    fun providesRoyFiler(context: Context) : RoyFiler {
        return RoyFiler(context)
    }

    @Provides
    fun provideContext(): Context {
        return RoysApplication.get().applicationContext
    }
}