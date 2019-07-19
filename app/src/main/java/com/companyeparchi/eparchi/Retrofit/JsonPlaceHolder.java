package com.companyeparchi.eparchi.Retrofit;

import com.companyeparchi.eparchi.Models.DccmModel;
import com.companyeparchi.eparchi.Models.DccmnewModel;
import com.companyeparchi.eparchi.Models.DendcuttingModel;
import com.companyeparchi.eparchi.Models.DmillReportsModel;
import com.companyeparchi.eparchi.Models.MillReportingModel1;
import com.companyeparchi.eparchi.Models.MillReportingModel2;
import com.companyeparchi.eparchi.Models.MillReportingModel4;
import com.companyeparchi.eparchi.Models.MillStopModel;
import com.companyeparchi.eparchi.Models.BilletModel;
import com.companyeparchi.eparchi.Models.CNNFinalModel;
import com.companyeparchi.eparchi.Models.CNNFormModel;
import com.companyeparchi.eparchi.Models.DbilletModel;
import com.companyeparchi.eparchi.Models.DinductionModel;
import com.companyeparchi.eparchi.Models.DqualityModel;
import com.companyeparchi.eparchi.Models.EndCuttingModel;
import com.companyeparchi.eparchi.Models.InductionModel;
import com.companyeparchi.eparchi.Models.LoginModel;
import com.companyeparchi.eparchi.Models.QualityModel;
import com.companyeparchi.eparchi.Models.SequenceModel;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface JsonPlaceHolder {

    interface Service {
        @Multipart
        @POST("/add_pic/")
        Call<ResponseBody> postImage(@Part MultipartBody.Part photo);
    }

    @GET("report/get_induction_report/{id}/{date}")
    Call<DinductionModel> getinductiondetail(@Header("AUTHORIZATION") String header, @Path("id") String id,@Path("date") String date);

    @GET("report/get_ccm_summary/{date}")
    Call<DccmnewModel> getccmreportsfull(@Header("AUTHORIZATION") String header, @Path("date") String date);

    @GET("report/get_quality_report/{id}/{sample_type}/{date}")
    Call<DqualityModel> getqualitydetail(@Header("AUTHORIZATION") String header, @Path("id") String id, @Path("sample_type") String sample,@Path("date") String date);

    @GET("report/get_billet_report/{date}")
    Call<DbilletModel> getbilletdetail(@Header("AUTHORIZATION") String header,@Path("date") String date);

    @GET("report/get_ccm_sequence/{date}")
    Call<DccmModel> getccmsequence(@Header("AUTHORIZATION") String header,@Path("date") String date);


    @GET("report/get_mill_reports/{mill_no}/{date}")
    Call<DmillReportsModel> getmillreportsdashboard(@Header("AUTHORIZATION") String header, @Path("mill_no") String mill,@Path("date") String date);

    @GET("report/get_end_cutting_report/{mill}/{date}")
    Call<DendcuttingModel> getendcuttingdetail(@Header("AUTHORIZATION") String header, @Path("mill") String mill,@Path("date") String date);

    @GET("report/get_ccm_report/{sequence_no}/{date}")
    Call<DccmModel> getccmreports(@Header("AUTHORIZATION") String header, @Path("sequence_no") String mill,@Path("date") String date);

    @GET("report/get_billet_id")
    Call<MillStopModel> getbilletid(@Header("AUTHORIZATION") String header);

    @GET("report/get_ccm_id")
    Call<MillStopModel> getccmid(@Header("AUTHORIZATION") String header);

    @POST("login/")
    Call<LoginModel> loginuser(@Body LoginModel loginModel);

    @POST("report/add_mill_stop_report/")
    Call<MillStopModel> milldetailaddnow(@Header("AUTHORIZATION") String header, @Body MillStopModel inductionModel);

    @POST("report/add_induction_report/")
    Call<InductionModel> inductiondetail(@Header("AUTHORIZATION") String header,@Body InductionModel inductionModel);

    @POST("report/add_induction_electrical_report/")
    Call<SequenceModel> sequencedetail(@Header("AUTHORIZATION") String header,@Body SequenceModel sequenceModel);

    @POST("report/add_quality_report/")
    Call<QualityModel> qualitydetail(@Header("AUTHORIZATION") String header,@Body QualityModel quality);

    @POST("report/add_ccm_report/")
    Call<CNNFormModel> cnnformdetail(@Header("AUTHORIZATION") String header,@Body CNNFormModel cnnFormModel);

    @POST("report/add_ccm_main_report/")
    Call<CNNFinalModel> cnnsubmitdetail(@Header("AUTHORIZATION") String header, @Body CNNFinalModel cnnFinalModel);

    @POST("report/add_billet_report/")
    Call<BilletModel> billetdetail(@Header("AUTHORIZATION") String header,@Body BilletModel billetModel);

    @POST("report/add_end_cutting_report/")
    Call<EndCuttingModel> endcuttingtwodetail(@Header("AUTHORIZATION") String header,@Body EndCuttingModel endCuttingModel);

    @POST("report/start_mill_report/")
    Call<MillReportingModel1> millreporting1detail(@Header("AUTHORIZATION") String header, @Body MillReportingModel1 endCuttingModel);

    @POST("report/add_mill_breakdown_report/")
    Call<MillReportingModel2>millbreakdownreport2(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/get_mill_report2_count/")
    Call<MillReportingModel2> getbilletcountermill(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/delete_mill_report2/")
    Call<MillReportingModel2> reducebilletcountermill(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/add_mill_report2/")
    Call<MillReportingModel2> setbilletcountermill(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/add_mill_report2_sub1/")
    Call<MillReportingModel2> setnocountermill(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/get_mill_report2_sub1_count/")
    Call<MillReportingModel2> getnocountermill(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/add_half_hour_mill_report/")
    Call<MillReportingModel2> reason30mill(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/add_hour_mill_report/")
    Call<MillReportingModel2> reason1mill(@Header("AUTHORIZATION") String header, @Body MillReportingModel2 endCuttingModel);

    @POST("report/add_final_mill_report/")
    Call<MillReportingModel4> millreporting4(@Header("AUTHORIZATION") String header, @Body MillReportingModel4 endCuttingModel);


}
