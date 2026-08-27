package com.rhuertas.filamentscenedemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform