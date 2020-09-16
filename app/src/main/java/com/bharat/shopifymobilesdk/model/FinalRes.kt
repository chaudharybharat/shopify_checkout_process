package com.bharat.shopifymobilesdk.model

class FinalRes {
    private var webUrl: String? = null

    private var id: Id? = null

    fun getWebUrl(): String? {
        return webUrl
    }

    fun setWebUrl(webUrl: String?) {
        this.webUrl = webUrl
    }

    fun getId(): Id? {
        return id
    }

    fun setId(id: Id?) {
        this.id = id
    }

    override fun toString(): String{
        return "ClassPojo [webUrl = $webUrl, id = $id]"
    }
}