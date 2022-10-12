package kr.hs.diowner

import kr.hs.diowner.data.*
import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("owner/sign-up")
    fun JoinPost(@Body ownerData: OwnerData) : Call<Void>

    @POST("owner/sign-in")
    fun LoginPost(@Body ownerLoginData: OwnerLoginData) : Call<LoginResponseData>

    @GET("owner/{id}")
    fun getOwnerData(@Path("id") id: Int): Call<OwnerResponseData>

    @POST("advertising/")
    fun postAdvertising(@Body postAdvertisingData: PostAdvertisingData) : Call<AdvertisingResponseData>

    @PATCH("owner/{id}")
    fun modifyOwner(@Path("id") id: Int, @Body ownerData: OwnerData) : Call<OwnerResponseData>
}