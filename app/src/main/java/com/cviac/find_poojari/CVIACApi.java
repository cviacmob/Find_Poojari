package com.cviac.find_poojari;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

public interface CVIACApi {

    @POST("/CVIACAPI/cviacdbop.php/sendemail")
    Call<SendEmailResponse> sendEmail(@Body EmailInfo emailInfo);

}