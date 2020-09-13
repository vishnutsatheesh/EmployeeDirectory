package com.test.empdirect.rest

import com.test.empdirect.model.Employee
import retrofit2.Call
import retrofit2.http.GET


/**
 *
 * @Path – variable substitution for the API endpoint. For example movie id will be swapped for{id} in the URL endpoint.
 * @Query – specifies the query key name with the value of the annotated parameter.
 * @Body – payload for the POST call
 * @Header – specifies the header with the value of the annotated parameter
 *
 */

interface ApiInterface {

    /**
     * get Facts
     */
    @GET("v2/5d565297300000680030a986")
    fun getEmployees(): Call<ArrayList<Employee>>

}

