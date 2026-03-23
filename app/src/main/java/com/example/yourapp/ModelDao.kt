package com.example.yourapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ModelDao {

    @Insert
    suspend fun insert(model: Model3D)

    @Update
    suspend fun update(model: Model3D)

    @Query("SELECT * FROM model3d WHERE id = :id")
    suspend fun getModelById(id: Int): Model3D?

    @Query("DELETE FROM model3d")
    suspend fun deleteAll()
}