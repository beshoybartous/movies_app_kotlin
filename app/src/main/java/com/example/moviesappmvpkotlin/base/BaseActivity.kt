package com.example.moviesappmvpkotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<p : BasePresenter, v : ViewBinding> : AppCompatActivity() {
    lateinit var presenter: p
    lateinit var viewBinding: v

    abstract fun setViewBinding(): v
    abstract fun setPresenter(): p
    abstract fun onPostCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = setViewBinding()
        setContentView(viewBinding.root)
        presenter = setPresenter()
        onPostCreated()
    }
}