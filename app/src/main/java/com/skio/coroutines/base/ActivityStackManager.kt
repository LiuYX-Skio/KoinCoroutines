package com.skio.coroutines.base

import android.app.Activity

/**
 * @author LiuYX
 * @description activity 栈管理
 */
object ActivityStackManager {

    private val activities = mutableListOf<Activity>()

    fun addActivity(activity: Activity) = activities.add(activity)

    fun removeActivity(activity: Activity) {
        if (activities.contains(activity)) {
            activities.remove(activity)
            activity.finish()
        }
    }

    fun getTopActivity(): Activity? =
        if (activities.isEmpty()) null else activities[activities.size - 1]

    fun finishAll() =
        activities.filter { it.isFinishing }.forEach { it.finish() }
}
