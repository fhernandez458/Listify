package com.fhzapps.listify.domain

sealed class ScreenType() {
    object ListPage : ScreenType()
    object QuotesPage : ScreenType()

}