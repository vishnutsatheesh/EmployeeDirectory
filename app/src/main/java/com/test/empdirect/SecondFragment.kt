package com.test.empdirect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.test.empdirect.databinding.FragmentSecondBinding
import com.test.empdirect.model.Employee

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var fragmentSecondBinding: FragmentSecondBinding
    val dataargs: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSecondBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_second, container, false
        )

        fragmentSecondBinding.employee = dataargs.employeArgs
        // Inflate the layout for this fragment
        return fragmentSecondBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}