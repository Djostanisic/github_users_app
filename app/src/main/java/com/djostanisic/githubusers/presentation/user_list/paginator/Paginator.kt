package com.djostanisic.githubusers.presentation.user_list.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}