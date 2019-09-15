package com.example.matesotestwandercraft.presentation

import com.example.matesotestwandercraft.common.DisplayableItem
import com.example.matesotestwandercraft.common.DisplayableItem.Companion.toDisplayableItem
import com.example.matesotestwandercraft.data.models.Departement
import io.reactivex.Observable
import io.reactivex.internal.util.ObservableQueueDrain
import java.util.function.Function
import javax.inject.Inject

/***
 * Mapper qui transforme le modele de la vue en modele de la Data
 */
class DepartementViewModelMapper @Inject constructor() : Function<Departement, DepartementViewModel> {
    override fun apply(dep: Departement): DepartementViewModel {
        return DepartementViewModel.builder()
            .code(dep.code())
            .nom(dep.nom())
            .build()
    }

}