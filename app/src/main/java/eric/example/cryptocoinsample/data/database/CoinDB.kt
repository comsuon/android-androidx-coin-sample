package eric.example.cryptocoinsample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eric.example.cryptocoinsample.data.Coin

@Database(entities = [Coin::class], version = 1)
@TypeConverters(CoinTypeAdapter::class)
abstract class CoinDB : RoomDatabase() {
    abstract fun coinDao(): CoinDAO
}