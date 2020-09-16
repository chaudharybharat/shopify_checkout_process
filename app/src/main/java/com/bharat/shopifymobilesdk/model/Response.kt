package com.bharat.shopifymobilesdk.model

class Response {
    private var hasErrors: String? = null

    private var data: Data? = null


    fun getHasErrors(): String? {
        return hasErrors
    }

    fun setHasErrors(hasErrors: String?) {
        this.hasErrors = hasErrors
    }

    fun getData(): Data? {
        return data
    }

    fun setData(data: Data?) {
        this.data = data
    }



    override fun toString(): String {
        return "ClassPojo [hasErrors = $hasErrors, data = $data]"
    }
}