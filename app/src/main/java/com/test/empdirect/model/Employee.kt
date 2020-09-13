package com.test.empdirect.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable


@Entity
class Employee(
    @PrimaryKey var id: Int?,
    var name: String?,
    var username: String?,
    var email: String?,
    var address: Address?,
    var phone: String?,
    var website: String?,
    var company: Company?,
    var profile_image: String?
) : Serializable

class Address() : Serializable {
    var street = ""
    var suite = ""
    var city = ""
    var zipcode = ""
    var geo: Geo? = null

    fun getFullAddress():String
    {
        return "${suite}\n${street}, ${city}\n${zipcode}"
    }
}

class Geo() : Serializable {
    var lat = ""
    var lng = ""
}

class Company(var name: String?, var catchPhrase: String?, var bs: String?) : Serializable
