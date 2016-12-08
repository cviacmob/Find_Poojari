package com.cviac.find_poojari;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PoojariApp extends MultiDexApplication {

    private boolean networkStatus = true;

    public boolean isNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(boolean networkStatus) {
        this.networkStatus = networkStatus;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }


    public void sendEmail(String emailid, String subject, String msgBody) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apps.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CVIACApi api = retrofit.create(CVIACApi.class);
        EmailInfo emailinfo = new EmailInfo(emailid, subject, msgBody);

        Call<SendEmailResponse> call = api.sendEmail(emailinfo);
        call.enqueue(new Callback<SendEmailResponse>() {
            @Override
            public void onResponse(Response<SendEmailResponse> response, Retrofit retrofit) {
                SendEmailResponse rsp = response.body();
//                Toast.makeText(PoojariApp.this,
//                        "Send Email Success", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Throwable t) {

//                Toast.makeText(PoojariApp.this,
//                        "Send Email Failed", Toast.LENGTH_LONG).show();

            }
        });


    }


//    private static class SendEmailTask extends AsyncTask<String, Integer, Long>{
//        @Override
//        protected Long doInBackground(String... params) {
//
//            String emailid=params[0];
//            String subject=params[1];
//            String body=params[2];
//
//            Retrofit retrofit=new Retrofit.Builder()
//                    .baseUrl("http://apps.cviac.com")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            CVIACApi api=retrofit.create(CVIACApi.class);
//            EmailInfo emailinfo=new EmailInfo();
//            emailinfo.setEmail(emailid);
//            emailinfo.setSubject(subject);
//            emailinfo.setMessage(body);
//
//            Call<SendEmailResponse> resp=api.sendEmail(emailinfo);
//            resp.
//
//
//            return null;
//        }
//    }

}
