package kr.hs.diowner.data

data class OwnerResponseData(
    val address: String,
    val id: Int,
    val name: String,
    val number: String,
    val ownerId: String,
    val point: Int,
    val signInDate: String,
    val signUpDate: String,
    val store: String
)