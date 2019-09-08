package com.example.matesotestwandercraft.domain

import com.example.matesotestwandercraft.common.UnwrapOptionTransformer
import com.example.matesotestwandercraft.data.*
import com.example.matesotestwandercraft.data.models.Departement
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single.just
import io.reactivex.Single
import polanski.option.Option

public class RetreiveDepartementsList() : ReactiveInteractor<Void, List<Departement>> {

    val depMapper: DepartementMapper = DepartementMapper()
    val networkCall: NetworkCall = NetworkCall()
    private  var _depRepository : DepartementRepository = DepartementRepository(depMapper, networkCall)

    fun getBehaviorStream(params : Option<Void> ) : Observable<List<Departement>> {
        return _depRepository!!
            .getAllDepartements()!!.
                flatMapSingle { dep -> fetchWhenNoneAndThenDrafts(dep) }.
                compose(UnwrapOptionTransformer.create())
    }

    fun fetchWhenNoneAndThenDrafts( deps : Option<List<Departement>> )  : Single<Option<List<Departement>>> {
        return fetchWhenNone(deps).andThen(just(deps))
    }

    fun fetchWhenNone(depList : Option<List<Departement>> ) : Completable {
        if(depList.isNone()) {
            return  _depRepository!!.fetchDepartements();
        }
        else {
            return Completable.complete();
        }
    }
}