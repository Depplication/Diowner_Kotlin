package kr.hs.diowner.data

data class LoginResponseData(
    val ownerData: OwnerResponseData,
    val tokenData: TokenData
)