package com.skio.coroutines.base;

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.databinding.BaseObservable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import org.jetbrains.annotations.NotNull


/**
 * @author LiuYX
 *
 * @date 2019/3/16
 *
 * @desc
 */
abstract class BaseViewModel : BaseObservable(), LifecycleObserver,
        CoroutineScope by CoroutineScope(Dispatchers.Main + SupervisorJob()) {

    protected val mDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected val disposableList by lazy { mutableListOf<Disposable>() }
    /**
     * 子viewModel list
     */
    protected val mSubViewModelList by lazy { mutableListOf<BaseViewModel>() }

    var onCreated = false
    var lazyCreated = false

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate(@NotNull owner: LifecycleOwner) {
        onCreated = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart(@NotNull owner: LifecycleOwner) {

    }

    open fun lazyInit() {
        lazyCreated = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy(@NotNull owner: LifecycleOwner) {
        mSubViewModelList.forEach { it.onDestroy(owner) }
        //mDisposable.dispose()
        disposableList.forEach { it.dispose() }
        coroutineContext.cancelChildren()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun onLifecycleChanged(@NotNull owner: LifecycleOwner, @NotNull event: Lifecycle.Event) {

    }

    operator fun plus(vm: BaseViewModel?): BaseViewModel {
        vm?.let { mSubViewModelList.add(it) }
        return this
    }

    /**
     * 自动取消
     */
    protected fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            //mDisposable.add(it)
            disposableList.add(it)
        }
    }

}
