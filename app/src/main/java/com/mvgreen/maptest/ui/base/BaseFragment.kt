package com.mvgreen.maptest.ui.base

import android.view.ViewTreeObserver
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment {

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super()

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val globalListeners by lazy { mutableListOf<ViewTreeObserver.OnGlobalLayoutListener>() }

    override fun onPause() {
        globalListeners.unsubscribeAll()
        compositeDisposable.clear()
        super.onPause()
    }

    protected fun bindToFragmentLifecycle(listener: ViewTreeObserver.OnGlobalLayoutListener) {
        globalListeners.add(listener)
        view?.viewTreeObserver?.addOnGlobalLayoutListener(listener)
    }

    protected fun Disposable.disposeOnDestroy(): Disposable {
        compositeDisposable.add(this)
        return this
    }

    private fun MutableList<ViewTreeObserver.OnGlobalLayoutListener>.unsubscribeAll() {
        view?.let { root ->
            for (listener in this) {
                root.viewTreeObserver.removeOnGlobalLayoutListener(listener)
            }
        }
        this.clear()
    }

}