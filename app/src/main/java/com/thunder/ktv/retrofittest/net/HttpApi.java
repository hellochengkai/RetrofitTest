package com.thunder.ktv.retrofittest.net;


import com.thunder.ktv.retrofittest.Express;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HttpApi {

    @GET("query")
    Observable<ResultModel<List<Express>>> getExpress(
            @Query("type") String type,
            @Query("postid") String postid);
}