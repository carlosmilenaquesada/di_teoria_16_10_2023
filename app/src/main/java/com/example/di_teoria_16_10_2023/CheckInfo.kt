package com.example.di_teoria_16_10_2023

data class CheckInfo(
    val titulo: String,
    var selected: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)
