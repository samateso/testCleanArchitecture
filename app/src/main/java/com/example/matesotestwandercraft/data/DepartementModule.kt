package com.example.matesotestwandercraft.data

import android.content.Context
import com.example.matesotestwandercraft.data.cache.Cache
import com.example.matesotestwandercraft.data.models.Departement
import com.example.matesotestwandercraft.data.store.MemoryReactiveStore
import com.example.matesotestwandercraft.data.store.ReactiveStore
import com.example.matesotestwandercraft.data.store.Store
import com.google.gson.Gson
import com.google.gson.TypeAdapterFactory
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import dagger.multibindings.IntoSet
import io.reactivex.functions.Function
import polanski.option.Option
import polanski.option.function.Func1
import java.security.Key


@Module
class DepartementModule {

    private val CACHE_MAX_AGE = (5 * 60 * 1000).toLong()
    val dep : Departement.Builder = Departement.builder()


    @Provides
    fun provideMockCreditService(): DepartementService {
        return MockDepartementService()
    }

    @Provides
    @Singleton
    fun provideCache(): Store.MemoryStore<String, Departement> {

        val f : Function<Departement, String> = Function { t -> t.code() }
        return Cache( f, CACHE_MAX_AGE)
    }


    @Provides
    @Singleton
    fun provideReactiveStore(cache: Store.MemoryStore<String, Departement>): ReactiveStore<String, Departement> {
        return MemoryReactiveStore( Func1 { t: Departement -> t.code() } , cache)
    }
}