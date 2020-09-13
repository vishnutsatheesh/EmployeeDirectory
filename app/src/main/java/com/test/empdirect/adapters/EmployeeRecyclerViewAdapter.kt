package com.exercise.app.ui.main.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.empdirect.BR
import com.test.empdirect.R
import com.test.empdirect.controller.RecyclerViewInterface
import com.test.empdirect.databinding.ItemEmployeBinding
import com.test.empdirect.model.Employee


class EmployeeRecyclerViewAdapter :
    RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolderCart>() {
    private val TAG = EmployeeRecyclerViewAdapter::class.java.simpleName

    var employeeDatas = emptyList<Employee>()
    lateinit var recInterface: RecyclerViewInterface


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
        val itemCartDatabind: ItemEmployeBinding
        itemCartDatabind = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_employe, parent, false
        )
        return ViewHolderCart(itemCartDatabind)

    }

    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
        employeeDatas[position]?.let { holder.bind(it, recInterface) }
    }

    override fun getItemCount(): Int {
        return employeeDatas.size
    }


    override fun getItemViewType(position: Int): Int {
        return 0
    }


    class ViewHolderCart(itemCartBinding: ItemEmployeBinding) :
        RecyclerView.ViewHolder(itemCartBinding.root) {
        val _itemCartBinding = itemCartBinding

        fun bind(obj: Employee, recIntefrace: RecyclerViewInterface) {
            _itemCartBinding.setVariable(BR.employee, obj)
            _itemCartBinding.setVariable(BR.recInterface, recIntefrace)
            _itemCartBinding.executePendingBindings()

        }
    }


    fun getItem(position: Int): Employee? {
        return employeeDatas.get(position)
    }


}

