package com.isna.appinformationcovid.di

import com.isna.appinformationcovid.ui.detail.DetailFragmentViewModel
import com.isna.appinformationcovid.ui.edit.EditFragmentViewModel
import com.isna.appinformationcovid.ui.favorite.FavoriteFragmentViewModel
import com.isna.appinformationcovid.ui.home.HomeFragmentViewModel
import com.isna.appinformationcovid.ui.login.LoginFragmentViewModel
import com.isna.appinformationcovid.ui.register.RegisterFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeFragmentViewModel)
    viewModelOf(::DetailFragmentViewModel)
    viewModelOf(::FavoriteFragmentViewModel)
    viewModelOf(::RegisterFragmentViewModel)
    viewModelOf(::LoginFragmentViewModel)
    viewModelOf(::EditFragmentViewModel)
}