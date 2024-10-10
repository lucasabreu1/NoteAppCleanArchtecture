package com.example.noteapplicationapp.feature_note.domain.util

sealed class OrderType {
    object Asceding: OrderType()
    object Descending: OrderType()
}
