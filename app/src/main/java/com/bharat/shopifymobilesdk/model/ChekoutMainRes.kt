package com.bharat.shopifymobilesdk.model

class ChekoutMainRes {
    private var response: ChekoutRespose? = null

    fun getResponse(): ChekoutRespose? {
        return response
    }

    fun setResponse(response: ChekoutRespose?) {
        this.response = response
    }

    override fun toString(): String {
        return "ClassPojo [response = $response]"
    }
}