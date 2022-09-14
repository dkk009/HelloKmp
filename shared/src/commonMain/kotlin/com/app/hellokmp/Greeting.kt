package com.app.hellokmp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}