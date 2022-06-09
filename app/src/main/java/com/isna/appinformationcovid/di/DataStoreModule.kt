package com.isna.appinformationcovid.di

import com.than.covidapp_challengeschapter7.data.DataStoreManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule = module {
    singleOf(::DataStoreManager)
}