package com.space.spacedreamspace.items

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "level_table")
data class Level(
    @PrimaryKey(autoGenerate = true)
    val levelID: Int,
    val speed: Int,
    val time: Int,
    @ColumnInfo(name = "is_opened")
    var isOpened: Boolean = false
): Parcelable