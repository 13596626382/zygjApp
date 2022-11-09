package com.tx.zygj.util

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.llx.common.util.logE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.*

/**
 * 倒计时的实现
 */
@ExperimentalCoroutinesApi
fun FragmentActivity.countDown(
    time: Int = 5,
    start: CoroutineScope.() -> Unit,
    end: () -> Unit,
    next: Int.() -> Unit
) {

    lifecycleScope.launch {
        // 在这个范围内启动的协程会在Lifecycle被销毁的时候自动取消

        flow {
            (time downTo 0).forEach {
                delay(1000)
                emit(it)
            }
        }.onStart {
            // 倒计时开始 ，在这里可以让Button 禁止点击状态
            start.invoke(this@launch)

        }.onCompletion {
            // 倒计时结束 ，在这里可以让Button 恢复点击状态
            end.invoke()

        }.catch {

        }.collect {
            // 在这里 更新值来显示到UI
            next.invoke(it)
        }

    }
}


fun stringToFile(fileContent: String, absolutePath: String): Boolean {
    logE("stringToFile:start")
    var result = false
    val absolutePathFileName = File(absolutePath)
    var os: OutputStream? = null
    try {
        if (absolutePathFileName.exists()) {
            absolutePathFileName.delete()
        }
        os = FileOutputStream(absolutePathFileName)
        os.write(fileContent.toByteArray())
        os.flush()
        result = true
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        try {
            os?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    logE("stringToFile:end")
    return result
}
