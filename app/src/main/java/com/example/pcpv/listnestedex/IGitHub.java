package com.example.pcpv.listnestedex;


import com.example.pcpv.listnestedex.model.Job;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IGitHub {

    @GET("positions.json")
    Observable<List<Job>> getJobs(@Query("search") String searchKey);
}
