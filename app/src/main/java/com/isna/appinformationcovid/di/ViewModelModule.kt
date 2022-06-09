package com.isna.appinformationcovid.di

import com.than.covidapp_challengeschapter7.ui.detail.DetailFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.edit.EditFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.favorite.FavoriteFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.home.HomeFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.login.LoginFragmentViewModel
import com.than.covidapp_challengeschapter7.ui.register.RegisterFragmentViewModel
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