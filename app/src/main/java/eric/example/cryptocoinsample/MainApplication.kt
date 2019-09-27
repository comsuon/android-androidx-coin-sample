package eric.example.cryptocoinsample

import android.app.Application

class MainApplication: Application() {

    companion object {
        lateinit var INSTANCE: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}