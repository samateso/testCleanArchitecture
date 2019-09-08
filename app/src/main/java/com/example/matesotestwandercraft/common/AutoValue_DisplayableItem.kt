package com.example.matesotestwandercraft.common

class AutoValue_DisplayableItem<T>(type: Int?, model: T?) : DisplayableItem<T>() {

    private var _type: Int? = null
    private var _model: T? = null

    private fun AutoValue_DisplayableItem(
        type: Int?,
        model: T?
    ) {
        this._type = type
        this._model = model
    }


    override fun model(): T? {
        return _model
    }

    override fun type(): Int? {
        return _type
    }

    override fun toString(): String {
        return ("DisplayableItem{"
                + "type=" + _type + ", "
                + "model=" + _model
                + "}")
    }

    override fun equals(o: Any?): Boolean {
        if (o === this) {
            return true
        }
        return if (o is DisplayableItem<*>) {
            this._type == o.type() && this._model == o.model()
        } else false
    }

    override fun hashCode(): Int {
        var h = 1
        h *= 1000003
        h = h xor this._type!!
        h *= 1000003
        h = h xor this._model.hashCode()
        return h
    }

    internal class Builder<T> : DisplayableItem.Builder<T>() {


        private var type: Int? = null
        private var model: T? = null
        override fun type(type: Int): DisplayableItem.Builder<T> {
            this.type = type
            return this
        }

        override fun model(model: T): DisplayableItem.Builder<T> {
            if (model == null) {
                throw NullPointerException("Null model")
            }
            this.model = model
            return this
        }

        override fun build(): DisplayableItem<T> {
            var missing = ""
            if (this.type == null) {
                missing += " type"
            }
            if (this.model == null) {
                missing += " model"
            }
            check(missing.isEmpty()) { "Missing required properties:$missing" }
            return AutoValue_DisplayableItem(
                this.type,
                this.model
            )
        }
    }

}
