package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    //Adding the moshi Json to kotlin converter. Different dependency is needed to add the converter.
    //From what I understood.
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getPhotos] method
 */
interface MarsApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("photos")
    //Suggesting to put the received response in MarsPhoto
    // data structure with List<MarsPhoto>
    suspend fun getPhotos() : List<MarsPhoto>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    /*
    Lazy initialization can be useful when a property value
    is expensive to compute, or when it is not needed until it
    is actually accessed. It can help to improve the performance
     of a program by delaying the computation of the property value
     until it is actually needed.

     It is the same as val retrofitService: MarsApiService = retrofit.create(MarsApiService::class.java)
     but it is intialized again and again when called.

     But Lazy Initialization is called when needed and when first accessed.
     */
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
