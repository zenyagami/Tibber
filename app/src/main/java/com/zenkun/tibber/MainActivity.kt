package com.zenkun.tibber

import com.zenkun.tibber.common.base.BaseActivity
import com.zenkun.tibber.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate)
