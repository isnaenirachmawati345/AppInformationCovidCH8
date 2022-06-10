package com.isna.appinformationcovid.di

import androidx.room.Room
import com.isna.appinformationcovid.data.room.CovidDatabase
import com.isna.appinformationcovid.data.room.DatabaseHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            CovidDatabase::class.java,
            "covidDatabase"
        ).fallbackToDestructiveMigration().build()
    }
    single {
        get<CovidDatabase>().favoriteDao()
    }
    single {
        get<CovidDatabase>().userDao()
    }
    singleOf(::DatabaseHelper)
}