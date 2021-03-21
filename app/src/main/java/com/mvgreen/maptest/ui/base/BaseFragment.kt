package com.mvgreen.maptest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment <T: ViewBinding>: Fragment {

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super()

    protected lateinit var binding: T

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val globalListeners by lazy { mutableListOf<ViewTreeObserver.OnGlobalLayoutListener>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initBinding(inflater)
        return binding.root
    }

    abstract fun initBinding(inflater: LayoutInflater)

    override fun onPause() {
        globalListeners.unsubscribeAll()
        compositeDisposable.clear()
        super.onPause()
    }

    protected fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
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