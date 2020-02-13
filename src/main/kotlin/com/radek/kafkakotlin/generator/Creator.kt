package com.radek.kafkakotlin.generator

import kotlin.reflect.KClass

class Creator<T> {
    companion object{
        fun <T> getObj(obj: T): T{
            return obj
        }
    }
}