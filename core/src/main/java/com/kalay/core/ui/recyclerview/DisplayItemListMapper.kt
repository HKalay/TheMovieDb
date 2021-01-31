package com.kalay.core.ui.recyclerview

interface DisplayItemListMapper<T> {
    fun map(items: List<T>): List<DisplayItem>
}