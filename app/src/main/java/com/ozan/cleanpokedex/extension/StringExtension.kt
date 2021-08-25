package com.ozan.cleanpokedex.extension

fun String.capitalizeFirstChar(): String {
    return replaceFirstChar {
        it.uppercaseChar()
    }
}