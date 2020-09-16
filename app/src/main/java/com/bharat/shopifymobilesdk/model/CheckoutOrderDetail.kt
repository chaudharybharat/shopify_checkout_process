package com.bharat.shopifymobilesdk.model

class CheckoutOrderDetail {
    private var responseData: ResOrderLast? = null


    fun getResponseData(): ResOrderLast? {
        return responseData
    }

    fun setResponseData(order: ResOrderLast?) {
        this.responseData = order
    }
}