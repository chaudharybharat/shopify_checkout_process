package com.bharat.shopifymobilesdk.model

class CheckoutResponseData {
    private var node: ChekoutNode? = null


    fun getNode(): ChekoutNode? {
        return node
    }

    fun setNode(responseData: ChekoutNode?) {
        this.node = responseData
    }



    override fun toString(): String {
        return "ClassPojo [node = $node]"
    }
}