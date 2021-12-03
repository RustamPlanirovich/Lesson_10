package presenter

interface PhotoViewContract {
    fun showError(t: Throwable?)
    fun newData(s: String)
}
