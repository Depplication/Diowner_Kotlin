package kr.hs.diowner.data

import java.io.Serializable

data class PointLogData(
    val date: Int,
    val time: String,
    val id: String,
    val point: Int
) : Serializable
