package com.bharat.shopifymobilesdk.model

class ResOrderLast {
    private var orderNumber: String? = null

    private var totalPrice: String? = null

    private var id: Id? = null

    fun getOrderNumber(): String? {
        return orderNumber
    }

    fun setOrderNumber(orderNumber: String?) {
        this.orderNumber = orderNumber
    }

    fun getTotalPrice(): String? {
        return totalPrice
    }

    fun setTotalPrice(totalPrice: String?) {
        this.totalPrice = totalPrice
    }

    fun getId(): Id? {
        return id
    }

    fun setId(id: Id?) {
        this.id = id
    }

    override fun toString(): String {
        return "ClassPojo [orderNumber = $orderNumber, totalPrice = $totalPrice, id = $id]"
    }
}