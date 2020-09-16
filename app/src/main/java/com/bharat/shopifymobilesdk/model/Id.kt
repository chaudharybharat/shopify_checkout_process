package com.bharat.shopifymobilesdk.model

class Id {
    private var id: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    override fun toString(): String {
        return "ClassPojo [id = $id]"
    }
}