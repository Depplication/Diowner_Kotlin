package kr.hs.diowner.data

data class AdvertisingResponseData(
    val approve: Boolean,
    val category: String,
    val endDate: String,
    val id: Int,
    val name: String,
    val ownerData: OwnerResponseData,
    val productList: List<ProductData>,
    val startDate: String,
    val storeExplain: String,
    val timeEnd: String,
    val title: String
)
