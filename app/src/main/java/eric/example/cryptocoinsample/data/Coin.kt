package eric.example.cryptocoinsample.data

data class Coin(val id: Int, val name: String, val symbol: String, val quote: Quote) {
    class Quote(val USD: Currency) {
        class Currency(val price: String)
    }
}