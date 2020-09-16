package com.bharat.shopifymobilesdk.model

class ResponCreate {
    private var checkout: Checkout? = null

    fun getCheckout(): Checkout? {
        return checkout
    }

    fun setCheckout(checkout: Checkout?) {
        this.checkout = checkout
    }

    override fun toString(): String {
        return "ClassPojo [response = $checkout]"
    }
}