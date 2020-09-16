package com.bharat.shopifymobilesdk.model

class ResponseData {
    private var checkoutCreate: CheckoutCreate? = null

    fun getCheckoutCreate(): CheckoutCreate? {
        return checkoutCreate
    }

    fun setCheckoutCreate(response: CheckoutCreate?) {
        this.checkoutCreate = response
    }

    override fun toString(): String {
        return "ClassPojo [response = $checkoutCreate]"
    }
}