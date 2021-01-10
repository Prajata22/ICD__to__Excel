package com.applex.icd__to__excel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICDInterface {

    @GET("icd/release/10/2019/{id}")
    Call<ResponseModel> get_ICD_codes_part1(@Path("id") String id);

    @GET("icd/release/10/2019/{id}")
    Call<ICDCodeModel> get_ICD_codes_part2(@Path("id") String id);
}
