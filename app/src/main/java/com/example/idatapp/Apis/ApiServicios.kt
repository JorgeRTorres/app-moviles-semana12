package com.example.idatapp.Apis

import retrofit2.http.GET

interface ApiServicios {
    //VAN A IR TODOS LOS ENDPOINTS DEL SERVIDOR
    //O SEA DE LA URL BASE
    @GET("posts")
    fun getPosts()
}