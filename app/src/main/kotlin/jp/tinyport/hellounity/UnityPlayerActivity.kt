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

        val cmdLine = intent.getStringExtra("unity")
        intent.putExtra("unity", cmdLine)

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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        mUnityPlayer!!.configurationChanged(newConfig)
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        mUnityPlayer!!.windowFocusChanged(hasFocus)
    }
}

@SuppressLint("ViewConstructor")
class UnityPlayerLayout(activity: Activity) : UnityPlayer(activity) {
    override fun onUnityPlayerUnloaded() {
        currentActivity.moveTaskToBack(true)

        super.onUnityPlayerUnloaded()
    }
}
