package ru.gb.veber.toplibrary

interface MainView {
    fun setTextButtonOne(text: String)
    fun setTextButtonTwo(text: String)
    fun setTextButtonThree(text: String)
    fun saveCounters(counters: MutableList<Int>)
}