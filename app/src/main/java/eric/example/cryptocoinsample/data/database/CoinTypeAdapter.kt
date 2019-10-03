package eric.example.cryptocoinsample.data.database

import androidx.room.TypeConverter
import eric.example.cryptocoinsample.data.Quote

class CoinTypeAdapter {
    @TypeConverter
    fun fromQuote(quote: Quote): String {
        return quote.USD.price
    }

    @TypeConverter
    fun fromString(price: String): Quote {
        return Quote(
            Quote.Currency(
                price
            )
        )
    }
}