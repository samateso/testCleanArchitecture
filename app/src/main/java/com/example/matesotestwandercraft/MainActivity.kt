package com.example.matesotestwandercraft

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import com.example.matesotestwandercraft.common.DisplayableItem
import com.example.matesotestwandercraft.data.RetreiveDepartementsList
import com.example.matesotestwandercraft.presentation.DepartementDashBoardViewModel
import com.example.matesotestwandercraft.presentation.DepartementDisplayItemMapper
import com.example.matesotestwandercraft.presentation.RecyclerViewDepAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

   var listDepartements : RecyclerView? = null
    var searchView : SearchView? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapter: RecyclerViewDepAdapter? = null


    @Inject lateinit var _mapper : DepartementDisplayItemMapper

    @Inject lateinit var depdashbordviewmodel : DepartementDashBoardViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.create().inject(this)


        listDepartements = findViewById(R.id.listDepRecyclerView)
        //searchView = findViewById(R.id.searchview)


        linearLayoutManager = LinearLayoutManager(this)
        listDepartements!!.layoutManager = linearLayoutManager


        depdashbordviewmodel.getDepartementLiveData()
            .observe(this, Observer<List<DisplayableItem<Any>>> { t-> updateListView(t!!) })


    }


    private fun updateListView(items: List<DisplayableItem<Any>>) {
        adapter = RecyclerViewDepAdapter(ArrayList(items))
        //adapter!!.update()

        if (items.isEmpty()) {
            listDepartements?.setVisibility(View.GONE)
            //emptyDashboardLayout.setVisibility(View.VISIBLE)
        } else {
            //emptyDashboardLayout.setVisibility(View.GONE)
            listDepartements?.setVisibility(View.VISIBLE)
        }
    }
}
