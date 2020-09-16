package com.bharat.shopifymobilesdk.model

class OrderRes {
    private var order: CheckoutOrderDetail? = null


    fun getOrder(): CheckoutOrderDetail? {
        return order
    }

    fun setOrder(order: CheckoutOrderDetail?) {
        this.order = order
    }

}