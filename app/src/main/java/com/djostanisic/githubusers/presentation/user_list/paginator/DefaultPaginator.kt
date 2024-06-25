package com.djostanisic.githubusers.presentation.user_list.paginator

class DefaultPaginator<Key, GithubUser>(
    private var initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<GithubUser>>,
    private inline val getNextKey: suspend (List<GithubUser>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<GithubUser>, newKey: Key) -> Unit
) : Paginator<Key, GithubUser> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            isMakingRequest = false
            return
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        isMakingRequest = false
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}