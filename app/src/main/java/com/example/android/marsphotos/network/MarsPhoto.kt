package com.example.android.marsphotos.network

import com.squareup.moshi.Json

/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class MarsPhoto(
    val id: String,
    /*
    "img_src" when converting between Java objects and
    JSON. This is useful when the property names in the
     kotlin class do not match the field names in the JSON
     document.
     */
    @Json(name = "img_src") val imgSrcUrl: String
)
