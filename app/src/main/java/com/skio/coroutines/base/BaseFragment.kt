package com.skio.coroutines.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider


/**
 * @author LiuYX
 *
 */
abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel?> : Fragment() {

  protected lateinit var mBinding: V
  abstract var mViewModel: VM
  protected val bindingList: MutableList<ViewDataBinding> = mutableListOf()

  // 是否切换了横竖屏
  protected var diffOrientation = false

  var initializer: (() -> Unit)? = null


  protected var onCreated = false
  protected var lazyCreated = false

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mBinding = DataBindingUtil.inflate(inflater, bindLayout(), container, false)
    mBinding.lifecycleOwner = this
    mViewModel?.let {
      lifecycle.addObserver(it as LifecycleObserver)
      mBinding.setVariable(bindViewModel(), it)
    }
//    initViewModel()

    mBinding.root.isClickable = true
    onCreated = true
    return mBinding.root
  }

//  /**
//   * 初始化ViewModel
//   */
//  private fun initViewModel() {
//    mViewModel = ViewModelProvider(this).get(viewModelClass())
//  }


  override fun onDestroyView() {
    super.onDestroyView()
    bindingList.forEach { it.unbind() }
    mBinding.unbind()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    init(savedInstanceState)
    initializer?.invoke()
  }

  protected fun lazyInit() {
    lazyCreated = true
    mViewModel?.let {
      if (it != null) {
        if (!it.lazyCreated) {
          it.lazyInit()
        }
      }
    }

  }

  abstract fun init(savedInstanceState: Bundle?)


  protected fun addSubBinding(binding: ViewDataBinding) {
    binding.lifecycleOwner = this
    bindingList.add(binding)
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    diffOrientation = !diffOrientation
  }


  @LayoutRes
  abstract fun bindLayout(): Int

//  /**
//   * 获取ViewModel的class
//   */
//  protected abstract fun viewModelClass(): Class<VM>

  open fun bindViewModel() : Int = BR.vm

}
