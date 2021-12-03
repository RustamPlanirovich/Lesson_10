package presenter

interface RepositoryContract {
    fun getMoreData(tag: String): String
}