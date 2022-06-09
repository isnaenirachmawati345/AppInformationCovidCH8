package com.isna.appinformationcovid.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id_favorite: Int?,
    var id_user: Int,
    var country_name: String,
    var cases: Int,
    var image: String,
    val active: Int,
    val death: Int,
    val recovered: Int
): Parcelable
