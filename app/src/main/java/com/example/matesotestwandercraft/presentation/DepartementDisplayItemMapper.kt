package com.example.matesotestwandercraft.presentation

import com.example.matesotestwandercraft.common.DisplayableItem
import com.example.matesotestwandercraft.common.DisplayableItem.Companion.toDisplayableItem
import com.example.matesotestwandercraft.data.models.Departement
import io.reactivex.Observable
import java.util.function.Function


/***
 * Mapper qui transforme le modele data en
 */
class DepartementDisplayItemMapper : Function<List<Departement>, List<DisplayableItem<Any>>> {
    private var _depViewModelMapper: DepartementViewModelMapper? = null

    internal fun CreditDisplayableItemMapper(depViewModelMapper: DepartementViewModelMapper) {
        this._depViewModelMapper = depViewModelMapper
    }

    @Throws(Exception::class)
    override fun apply(deps: List<Departement>): List<DisplayableItem<Any>> {
        return Observable.fromIterable(deps)
            .map { t ->  _depViewModelMapper!!.apply(t) }
            .map { t ->  this.wrapInDisplayableItem(t) }
            .toList()
            .blockingGet()
    }

    private fun wrapInDisplayableItem(viewEntity: DepartementViewModel): DisplayableItem<Any> {
        return toDisplayableItem(viewEntity, 0)
    }
}