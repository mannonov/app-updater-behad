package uz.behadllc.app_updater_behad.core

interface LoaderCallback<T> {
    fun onComplete(obj: T)
}