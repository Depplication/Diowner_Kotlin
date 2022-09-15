package kr.hs.diowner

import kr.hs.diowner.data.LoginResponseData
import kr.hs.diowner.data.OwnerData
import kr.hs.diowner.data.OwnerLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("owner/sign-up")
    fun JoinPost(@Body ownerData: OwnerData) : Call<Void>

    @POST("owner/sign-in")
    fun LoginPost(@Body ownerLoginData: OwnerLoginData) : Call<LoginResponseData>
}