package com.example.bookexplorer.di

import com.example.bookexplorer.App
import com.example.bookexplorer.data.BookRepository
import com.example.bookexplorer.util.ServiceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideBookRepository(serviceHelper: ServiceHelper) : BookRepository {
        return BookRepository(App.context, serviceHelper)
    }
    @Singleton
    @Provides
    fun provideServiceHelper() : ServiceHelper {
        return ServiceHelper(context = App.context)
    }
}