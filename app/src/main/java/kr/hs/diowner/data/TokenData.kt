package kr.hs.diowner.data

data class TokenData(
    val expired_time: String,
    val issued_time: String,
    val subject: String,
    val token: String
)