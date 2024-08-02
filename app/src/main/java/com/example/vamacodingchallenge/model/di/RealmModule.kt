package com.example.vamacodingchallenge.model.di

import com.example.vamacodingchallenge.model.Album
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {
    
    @Provides
    @Singleton
    fun providesRealmConfigs(): Realm {
        val config = RealmConfiguration.create(setOf(Album::class))
        return Realm.open(config)
    }


}












