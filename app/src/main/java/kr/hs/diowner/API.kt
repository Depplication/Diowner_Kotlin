package kr.hs.diowner

import kr.hs.diowner.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {
    @POST("owner/sign-up")
    fun JoinPost(@Body ownerData: OwnerData) : Call<Void>

    @POST("owner/sign-in")
    fun LoginPost(@Body ownerLoginData: OwnerLoginData) : Call<LoginResponseData>

    @GET("owner/{id}")
    fun getOwnerData(@Path("id") id: Int): Call<OwnerResponseData>
}