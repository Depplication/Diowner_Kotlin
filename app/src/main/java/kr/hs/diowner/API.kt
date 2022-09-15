package kr.hs.diowner

import kr.hs.diowner.data.OwnerData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("owner/sign-in")
    fun JoinPost(@Body ownerData: OwnerData) : Call<Void>
}