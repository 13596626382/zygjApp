package com.llx.common.util

import android.content.Context
import android.content.pm.PackageInfo

//获取手机已安装应用
val Context.appPageNameList: MutableList<PackageInfo> get() = packageManager.getInstalledPackages(0)

//获取系统路径
val Context.filePath: String get() = filesDir.path
