package com.example.matesotestwandercraft.data

import com.example.matesotestwandercraft.common.UnwrapOptionTransformer
import com.example.matesotestwandercraft.data.models.Departement
import com.example.matesotestwandercraft.domain.ReactiveInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single.just
import io.reactivex.Single
import polanski.option.Option
import javax.inject.Inject

public class RetreiveDepartementsList  @Inject constructor(departementRepository: DepartementRepository) :
    ReactiveInteractor<Void, List<Departement>> {

    private  var _depRepository : DepartementRepository = departementRepository

    fun getBehaviorStream(params : Option<Void> ) : Observable<List<Departement>> {
        return _depRepository
            .getAllDepartements()!!.
                flatMapSingle { dep -> fetchWhenNoneAndThenDrafts(dep) }.
                compose(UnwrapOptionTransformer.create())
    }

    fun fetchWhenNoneAndThenDrafts( deps : Option<List<Departement>> )  : Single<Option<List<Departement>>> {
        return fetchWhenNone(deps).andThen(just(deps))
    }

    fun fetchWhenNone(depList : Option<List<Departement>> ) : Completable {
        if(depList.isNone()) {
            return  _depRepository.fetchDepartements()
        }
        else {
            return Completable.complete();
        }
    }
}