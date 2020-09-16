package com.bharat.shopifymobilesdk.model

class Data {

    private var responseData: ResponseData? = null


    fun getResponseData(): ResponseData? {
        return responseData
    }

    fun setResponseData(responseData: ResponseData?) {
        this.responseData = responseData
    }


    override fun toString(): String {
        return "ClassPojo [responseData = $responseData]"
    }
}