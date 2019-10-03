package eric.example.cryptocoinsample

import android.app.Application
import androidx.room.Room
import eric.example.cryptocoinsample.data.database.CoinDB

class MainApplication : Application() {

    val db by lazy { Room.databaseBuilder(this, CoinDB::class.java, "coin-database").build() }

    companion object {
        lateinit var INSTANCE: MainApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}