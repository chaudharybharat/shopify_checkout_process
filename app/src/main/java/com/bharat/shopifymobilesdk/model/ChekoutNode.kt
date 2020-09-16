package com.bharat.shopifymobilesdk.model

class ChekoutNode {
    private var responseData: OrderRes? = null


    fun getResponseData(): OrderRes? {
        return responseData
    }

    fun setResponseData(responseData: OrderRes?) {
        this.responseData = responseData
    }



    override fun toString(): String {
        return "ClassPojo [responseData = $responseData]"
    }
}