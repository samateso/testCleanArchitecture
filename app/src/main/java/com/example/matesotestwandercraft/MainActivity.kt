package com.example.matesotestwandercraft

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.LinearLayout
import com.example.matesotestwandercraft.common.DisplayableItem
import com.example.matesotestwandercraft.domain.RetreiveDepartementsList
import com.example.matesotestwandercraft.presentation.DepartementDashBoardViewModel
import com.example.matesotestwandercraft.presentation.DepartementDisplayItemMapper
import com.example.matesotestwandercraft.presentation.RecyclerViewDepAdapter

class MainActivity : AppCompatActivity() {

   var listDepartements : RecyclerView? = null
    var searchView : SearchView? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapter: RecyclerViewDepAdapter? = null


    var _interactor : RetreiveDepartementsList = RetreiveDepartementsList()
    var _mapper : DepartementDisplayItemMapper = DepartementDisplayItemMapper()

    var depdashbordviewmodel : DepartementDashBoardViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listDepartements = findViewById(R.id.listDepRecyclerView)
        //searchView = findViewById(R.id.searchview)


        linearLayoutManager = LinearLayoutManager(this)
        listDepartements!!.layoutManager = linearLayoutManager

        depdashbordviewmodel = DepartementDashBoardViewModel(_interactor, _mapper)

        depdashbordviewmodel!!.getDepartementLiveData()
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
