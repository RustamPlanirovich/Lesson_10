package presenter

interface PhotoPresenterContract {
    fun registerView(view: PhotoViewContract)
    fun dataReady(s: String)
    fun getMoreData(tag: String)
    fun registerRepository(repository: RepositoryContract)
}
