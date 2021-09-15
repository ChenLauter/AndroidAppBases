package com.lauter.androidappbases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lauter.androidappbases.common.base.BaseLoadingActivity

class MainActivity : BaseLoadingActivity() {

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun getLayoutId(): Int = R.layout.activity_main

}