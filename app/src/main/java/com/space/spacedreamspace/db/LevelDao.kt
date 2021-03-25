package com.space.spacedreamspace.db

import androidx.room.*
import com.space.spacedreamspace.items.Level

@Dao
interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(level: Level)

    @Update
    fun update(level: Level)


    @Query("SELECT * from level_table")
    fun getAll(): List<Level>

}