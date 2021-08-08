package com.temper.myapplication.services.helpers

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.temper.myapplication.annotations.SkipSerialization

class SkipSerializedStrategy {

    companion object{
        fun getStrategy() : ExclusionStrategy {
            return object : ExclusionStrategy {
                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }

                override fun shouldSkipField(f: FieldAttributes): Boolean {
                    return f.getAnnotation(SkipSerialization::class.java) != null
                }

            }
        }
    }

}