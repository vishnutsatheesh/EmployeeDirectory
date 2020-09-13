package com.test.empdirect

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.test.empdirect.controller.RecyclerViewInterface
import com.test.empdirect.databinding.FragmentFirstBinding
import com.test.empdirect.model.Employee
import com.test.empdirect.util.LogsUtils
import com.test.empdirect.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.content_main.*
import kotlin.concurrent.fixedRateTimer


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), RecyclerViewInterface {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainFragmentBinding: FragmentFirstBinding

    private var employeList = ArrayList<Employee>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_first, container, false
        )

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainFragmentBinding.mainViewModel = viewModel
        viewModel.setRecInterface(this)
        populateData()




        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    fun populateData() {
        viewModel.fetchEmployees()
        viewModel.getLoading()!!.set(View.VISIBLE)
        viewModel.getEmployees().observe(viewLifecycleOwner, Observer {
            viewModel.getLoading()!!.set(View.GONE)
            when (it.size == 0) {
                true -> {
                    viewModel.setFactData(emptyList())
                }
                else -> {
                    //   activity?.title = it.title
                    employeList.clear()
                    employeList.addAll(it)
                    viewModel.setFactData(employeList)
                    //  LogsUtils.showToast(activity, resources.getString(R.string.data_refreshed))
                }
            }

        })


        mainFragmentBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setFactData(employeList.filter {
                    it.name?.toLowerCase()?.contains(
                        mainFragmentBinding.etSearch.text.toString().toLowerCase()
                    )!! || it.email?.toLowerCase()!!
                        .contains(mainFragmentBinding.etSearch.text.toString().toLowerCase())
                })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    override fun onItemClicked(position: Int, data: Any) {
        when (data) {
            is Employee -> {
                LogsUtils.makeLogE(">>>", "setFactData>> ${data.name}")
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(data)
                findNavController().navigate(action)

            }
        }

    }


}