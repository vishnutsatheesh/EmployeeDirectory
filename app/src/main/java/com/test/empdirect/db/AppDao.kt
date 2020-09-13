package com.test.empdirect.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.test.empdirect.model.Employee


@Dao
interface AppDao {
    @Query("SELECT * from Employee ")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertEmployees(data: List<Employee>)
}