package kr.hs.diowner.data

data class PostAdvertisingData(
    val businessNumber: String,
    val category: String,
    val email: String,
    val endData: String,
    val explain: String,
    val name: String,
    val product: List<ProductData>,
    val startData: String,
    val store: String,
    val title: String,
    val type: String
)
