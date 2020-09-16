package com.bharat.shopifymobilesdk.model

class Checkout {
    private var responseData: FinalRes? = null

    fun getResponseData(): FinalRes? {
        return responseData
    }

    fun setResponseData(response: FinalRes?) {
        this.responseData = response
    }

    override fun toString(): String {
        return "ClassPojo [response = $responseData]"
    }
}