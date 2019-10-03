package eric.example.cryptocoinsample.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import eric.example.cryptocoinsample.data.Coin

@Dao
interface CoinDAO {

    @Insert(onConflict = REPLACE)
    fun insertCoin(coin: Coin)

    @Insert(onConflict = REPLACE)
    fun insertCoinList(coinList: List<Coin>)

    @Query("SELECT * FROM Coin WHERE Coin.id=:id")
    fun getCoin(id: String): Coin
}