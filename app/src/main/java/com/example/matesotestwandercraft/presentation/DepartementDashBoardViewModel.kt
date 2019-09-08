package com.example.matesotestwandercraft.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.matesotestwandercraft.common.DisplayableItem
import com.example.matesotestwandercraft.domain.RetreiveDepartementsList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import polanski.option.Option.none

class DepartementDashBoardViewModel(interactor: RetreiveDepartementsList, mapper: DepartementDisplayItemMapper) :
    ViewModel() {


    var _interactor : RetreiveDepartementsList? = interactor
    var _mapper : DepartementDisplayItemMapper? = mapper
    var compositeDisposable : CompositeDisposable? = null
    val departementListLiveData = MutableLiveData<List<DisplayableItem<Any>>>()


    init {
        compositeDisposable = CompositeDisposable()
        compositeDisposable!!.add(bindToDepartements())
    }

    protected override fun onCleared() {
        super.onCleared()
        compositeDisposable!!.dispose()
    }

    internal fun getDepartementLiveData(): LiveData<List<DisplayableItem<Any>>> {
        return departementListLiveData
    }

    private fun bindToDepartements(): Disposable {
        return _interactor!!.getBehaviorStream(none<Void>())
            .observeOn(Schedulers.computation())
            .map { t -> _mapper!!.apply(t) }
            .subscribe{ t -> departementListLiveData.postValue(t) }
    }
}