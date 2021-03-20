package com.mvgreen.maptest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mvgreen.maptest.databinding.FragmentMainMenuBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment {

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super()

    protected lateinit var binding: FragmentMainMenuBinding

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val globalListeners by lazy { mutableListOf<ViewTreeObserver.OnGlobalLayoutListener>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater)
        setupViewModel()
        return binding.root
    }

    override fun onPause() {
        globalListeners.unsubscribeAll()
        compositeDisposable.clear()
        super.onPause()
    }

    protected abstract fun setupViewModel()

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