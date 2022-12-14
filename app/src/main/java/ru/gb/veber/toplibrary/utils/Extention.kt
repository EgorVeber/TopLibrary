package ru.gb.veber.toplibrary.utils

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_DATE_DTO = "yyyy-MM-dd"

fun Date.formatDefault(): String =
    SimpleDateFormat(FORMAT_DATE_DTO, Locale.getDefault()).format(this)

fun stringFromData(dateString: String) =
    SimpleDateFormat(FORMAT_DATE_DTO, Locale.getDefault()).parse(dateString)