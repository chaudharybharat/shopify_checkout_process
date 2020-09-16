package com.bharat.shopifymobilesdk.model

class CheckoutCreate {
    private var responseData: ResponCreate? = null

    fun getResponseData(): ResponCreate? {
        return responseData
    }

    fun setResponseData(response: ResponCreate?) {
        this.responseData = response
    }

    override fun toString(): String {
        return "ClassPojo [response = $responseData]"
    }
}