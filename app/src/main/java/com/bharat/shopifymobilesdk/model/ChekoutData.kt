package com.bharat.shopifymobilesdk.model

class ChekoutData {
    private var responseData: CheckoutResponseData? = null


    fun getResponseData(): CheckoutResponseData? {
        return responseData
    }

    fun setResponseData(responseData: CheckoutResponseData?) {
        this.responseData = responseData
    }



    override fun toString(): String {
        return "ClassPojo [responseData = $responseData]"
    }
}