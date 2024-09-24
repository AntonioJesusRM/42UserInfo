package com.example.a42userinfo.data.mapper

fun interface ResponseMapper<E, M> {
    fun fromResponse(response: E): M
}