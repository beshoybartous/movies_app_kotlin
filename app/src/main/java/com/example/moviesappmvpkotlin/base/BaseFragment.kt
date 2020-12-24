package com.example.moviesappmvpkotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<P:BasePresenter,V:ViewBinding> : Fragment() {
    lateinit var presenter: P
    lateinit var viewBinding: V

    abstract fun setViewBinding(): V
    abstract fun setPresenter(): P
    abstract fun onPostCreated()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = setViewBinding()
        presenter = setPresenter()
        onPostCreated()
        return viewBinding.root
    }
}