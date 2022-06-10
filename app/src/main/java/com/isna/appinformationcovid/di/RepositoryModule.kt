package com.isna.appinformationcovid.di

import com.isna.appinformationcovid.data.Repository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::Repository)
}