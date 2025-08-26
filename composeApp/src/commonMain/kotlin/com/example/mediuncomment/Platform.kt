package com.example.mediuncomment

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform