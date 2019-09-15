package com.example.matesotestwandercraft.data

import java.util.function.Function
import com.example.matesotestwandercraft.data.models.Departement
import javax.inject.Inject


class DepartementMapper
@Inject constructor() :  Function<DepartementRaw, Departement> {
    override fun apply(t: DepartementRaw): Departement {
        return Departement.builder()
            .code(t.code())
            .nom(t.nom())
            .build()
    }
}

