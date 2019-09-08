package com.example.matesotestwandercraft.common

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import polanski.option.Option
import polanski.option.OptionUnsafe

class UnwrapOptionTransformer<T> :  ObservableTransformer<Option<T>, T>{
    override fun apply(upstream: Observable<Option<T>>): ObservableSource<T> {
        return upstream.filter { t-> t.isSome }.map{ t -> OptionUnsafe.getUnsafe(t)}
    }

    companion object {
        fun <T> create(): UnwrapOptionTransformer<T> {
            return UnwrapOptionTransformer<T>()
        }
    }
}