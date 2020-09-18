package com.example.sampleapp.util

class LiveDataResult<T>(val status: STATUS, val data: T? = null, val error: Throwable? = null) {

    enum class STATUS {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> success(data: T) = LiveDataResult<T>(STATUS.SUCCESS, data)
        fun <T> error(error: Throwable) = LiveDataResult<T>(STATUS.ERROR, null, error)
        fun <T> loading() = LiveDataResult<T>(STATUS.LOADING)
    }
}