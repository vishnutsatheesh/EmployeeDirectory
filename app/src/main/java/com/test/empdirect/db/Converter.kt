package com.test.empdirect.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.empdirect.model.Address
import com.test.empdirect.model.Company
import com.test.empdirect.model.Employee
import com.test.empdirect.model.Geo


class Converter {


    @TypeConverter
    fun stringtoEmployeItem(data: String?): ArrayList<Employee> {
        val gson = Gson()

        val listType = object : TypeToken<ArrayList<Employee>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun EmployeItemtoString(someObjects: ArrayList<Employee>): String {
        val gson = Gson()
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringtoAddress(data: String?): Address {
        val gson = Gson()

        val listType = object : TypeToken<Address>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun addresstoString(someObjects: Address): String {
        val gson = Gson()
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringtoGeo(data: String?): Geo {
        val gson = Gson()

        val listType = object : TypeToken<Geo>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun geotoString(someObjects: Geo): String {
        val gson = Gson()
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringtoCompany(data: String?): Company? {
        val gson = Gson()

        val listType = object : TypeToken<Company>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun companytoString(someObjects: Company?): String {
        val gson = Gson()
        return gson.toJson(someObjects)
    }


}