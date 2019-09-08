package com.example.matesotestwandercraft.presentation

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matesotestwandercraft.R
import com.example.matesotestwandercraft.common.DisplayableItem
import java.text.FieldPosition

class RecyclerViewDepAdapter(private  var departements: ArrayList<DisplayableItem<Any>>) : RecyclerView.Adapter<RecyclerViewDepAdapter.DepHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerViewDepAdapter.DepHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.item_departement_dashboard, parent, false)
        return DepHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return  departements.size
    }

    override fun onBindViewHolder(p0: RecyclerViewDepAdapter.DepHolder, position: Int) {
       var item = departements[position]

    }

    fun update(updateDep : ArrayList<DisplayableItem<Any>>) {
        this.departements = updateDep
    }

    class DepHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var  view : View = v
        private var deps: DisplayableItem<Any>? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView", "CLICK!")

        }

        fun bindDep(item : DisplayableItem<Any>) {
            this.deps = item
            //view. itemDate.text = item.humanDate
            //view.itemDescription.text = item.explanation
        }
    }
}