package com.isna.appinformationcovid.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailCountryCases(
    val country: String,
    val flag: String,
    val cases: Int,
    val active: Int,
    val death: Int,
    val recovered: Int
):Parcelable
