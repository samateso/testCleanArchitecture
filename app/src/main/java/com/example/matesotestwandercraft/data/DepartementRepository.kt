package com.example.matesotestwandercraft.data

import com.example.matesotestwandercraft.data.models.Departement
import com.example.matesotestwandercraft.data.store.ReactiveStore
import com.google.gson.reflect.TypeToken
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DepartementRepository @Inject constructor (
    @NonNull dep: ReactiveStore<String, Departement>,
    depService: DepartementService,
    depMapper: DepartementMapper,
    networkCall: NetworkCall
) {

    private var _networkCall : NetworkCall = networkCall

    var retrofit = _networkCall.getDataApi()


    val type: Type = object : TypeToken<ReactiveStore<String, Departement>>() {}.type

    //private var _depService : DepartementService = retrofit.create(DepartementService::class.java)
    private var _depService : DepartementService = depService

    private var _dep : ReactiveStore<String, Departement> = dep


    private var _depMapper : DepartementMapper = depMapper


    //GET
    fun getAllDepartements() : Observable<Option<List<Departement>>>? {
        return _dep.getAll()
    }
    //FETCH
    fun  fetchDepartements() : Completable {


        //_depService = retrofit.create(DepartementService.class)


        return _depService.
            getDepartement().
            subscribeOn(Schedulers.io()).
            observeOn(Schedulers.computation()).
            flatMapObservable{ contributors -> Observable.fromIterable(contributors)  }.
            map{ _depMapper.apply(it)}
            .toList()
            .doOnSuccess{ dep ->  _dep.replaceAll(dep) }
            .toCompletable()
    }
    //REQUEST
    //PUSH
    //DELETE


}