package com.bharat.shopifymobilesdk.model

class ResponsMain {
    private var response: Response? = null

    fun getResponse(): Response? {
        return response
    }

    fun setResponse(response: Response?) {
        this.response = response
    }

    override fun toString(): String {
        return "ClassPojo [response = $response]"
    }
}