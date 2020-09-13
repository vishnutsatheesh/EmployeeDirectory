package com.test.empdirect.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.exercise.app.ui.main.adapters.EmployeeRecyclerViewAdapter
import com.test.empdirect.EmployeeApp
import com.test.empdirect.controller.RecyclerViewInterface
import com.test.empdirect.model.Employee
import com.test.empdirect.rest.ApiClient
import com.test.empdirect.rest.ApiInterface
import com.test.empdirect.util.LogsUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
//    private val factRecyclerViewAdapter = lazy { FactsRecyclerViewAdapter() }

    private val TAG = javaClass.simpleName
    private val employeeRecyclerViewAdapter = lazy { EmployeeRecyclerViewAdapter() }
    private val loading = lazy { ObservableInt(android.view.View.GONE) }
    private val employeeListLiveData = lazy { EmployeeApp.instance.appDAO.getAllEmployees() }


    fun getLoading(): ObservableInt {
        return loading.value
    }

    /**
     * get LiveData<ArrayList<Employee>> object uses for unit test purpose
     */
    fun getEmployees(): LiveData<List<Employee>> {
        return employeeListLiveData.value
    }


    fun fetchEmployees() {

        //creating retrofit instance
        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
        val call = apiInterface.getEmployees()

        call.enqueue(object : Callback<ArrayList<Employee>> {

            override fun onResponse(
                call: Call<ArrayList<Employee>>?,
                response: Response<ArrayList<Employee>>
            ) {

                LogsUtils.makeLogE(TAG, "onResponse>> ${response.body()!!.size}")
                //Checking the response is success or not
                if (response.isSuccessful) {
                    response.body()?.let { EmployeeApp.instance.appDAO.InsertEmployees(it) }
                }
            }

            override fun onFailure(call: Call<ArrayList<Employee>>?, t: Throwable?) {
                // mutlive_facts.value.postValue(null)
                LogsUtils.makeLogE(TAG, "onFailure>> ${t.toString()}")
            }

        })


    }

    /**
     * set interface
     */
    fun setRecInterface(recyclerViewInterface: RecyclerViewInterface) {
        employeeRecyclerViewAdapter.value.recInterface = recyclerViewInterface
    }

    /**
     * set factor list data in adapter
     */
    fun setFactData(factDatas: List<Employee>) {
        employeeRecyclerViewAdapter.value.employeeDatas = factDatas
        employeeRecyclerViewAdapter.value.notifyDataSetChanged()
    }

    /**
     * get FactsRecyclerViewAdapter object
     */
    fun getAdapter(): EmployeeRecyclerViewAdapter {
        return employeeRecyclerViewAdapter.value
    }
}