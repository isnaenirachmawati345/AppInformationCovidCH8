package com.isna.appinformationcovid.di

import com.isna.appinformationcovid.data.DataStoreManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule = module {
    singleOf(::DataStoreManager)
}