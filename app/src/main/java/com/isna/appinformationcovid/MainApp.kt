package com.isna.appinformationcovid

import android.app.Application
import com.isna.appinformationcovid.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    dataStoreModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}