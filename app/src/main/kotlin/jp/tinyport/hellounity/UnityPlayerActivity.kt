package jp.tinyport.hellounity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.unity3d.player.UnityPlayer

class UnityPlayerActivity : Activity() {
    @Suppress("ProtectedInFinal")
    @JvmField
    protected var mUnityPlayer: UnityPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mUnityPlayer = UnityPlayerLayout(this)
        setContentView(mUnityPlayer)
        mUnityPlayer!!.requestFocus()
    }

    override fun onDestroy() {
        mUnityPlayer!!.destroy()

        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()

        mUnityPlayer!!.resume()
    }

    override fun onPause() {
        mUnityPlayer!!.pause()

        super.onPause()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setIntent(intent)
        mUnityPlayer!!.newIntent(intent)
    }

    override fun onLowMemory() {
        super.onLowMemory()

        mUnityPlayer!!.lowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        if (level == TRIM_MEMORY_RUNNING_CRITICAL) {
            mUnityPlayer!!.lowMemory()
        }
    }
}

@SuppressLint("ViewConstructor")
class UnityPlayerLayout(activity: Activity) : UnityPlayer(activity) {
    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)

        windowFocusChanged(hasWindowFocus)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        configurationChanged(newConfig)
    }

    override fun onUnityPlayerUnloaded() {
        currentActivity.moveTaskToBack(true)

        super.onUnityPlayerUnloaded()
    }
}
