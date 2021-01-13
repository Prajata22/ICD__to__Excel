package com.applex.icd__to__excel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.HttpsURLConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.applex.icd__to__excel.Constants.CLIENT_ID;
import static com.applex.icd__to__excel.Constants.CLIENT_SECRET;
import static com.applex.icd__to__excel.Constants.GRANT_TYPE;
import static com.applex.icd__to__excel.Constants.SCOPE;
import static com.applex.icd__to__excel.Constants.TOKEN_ENDPOINT;

public class MainActivity extends AppCompatActivity {

    private SharedPref sharedPref;
    private DatabaseHelper_0 databaseHelper_0;
    private DatabaseHelper_1 databaseHelper_1;
    private DatabaseHelper_1_half databaseHelper_1_half;
    private DatabaseHelper_2 databaseHelper_2;
    private DatabaseHelper_3 databaseHelper_3;
    private DatabaseHelper_4 databaseHelper_4;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button get_token = findViewById(R.id.get_token);
        Button get_icd_part_0 = findViewById(R.id.get_icd_codes_0);
        Button get_icd_part_1 = findViewById(R.id.get_icd_codes_1);
        Button get_icd_part_1_half = findViewById(R.id.get_icd_codes_1_half);
        Button get_icd_part_2 = findViewById(R.id.get_icd_codes_2);
        Button get_icd_part_3 = findViewById(R.id.get_icd_codes_3);
        Button get_icd_part_4 = findViewById(R.id.get_icd_codes_4);

        sharedPref = new SharedPref(MainActivity.this);
        databaseHelper_0 = new DatabaseHelper_0(MainActivity.this);
        databaseHelper_1 = new DatabaseHelper_1(MainActivity.this);
        databaseHelper_1_half = new DatabaseHelper_1_half(MainActivity.this);
        databaseHelper_2 = new DatabaseHelper_2(MainActivity.this);
        databaseHelper_3 = new DatabaseHelper_3(MainActivity.this);
        databaseHelper_4 = new DatabaseHelper_4(MainActivity.this);

        get_token.setOnClickListener(v -> new Token().execute());
        get_icd_part_0.setOnClickListener(v -> new ICD_Codes_Part_0().execute());
        get_icd_part_1.setOnClickListener(v -> new ICD_Codes_Part_1().execute());
        get_icd_part_1_half.setOnClickListener(v -> new ICD_Codes_Part_1_half().execute());
        get_icd_part_2.setOnClickListener(v -> new ICD_Codes_Part_2().execute());
        get_icd_part_3.setOnClickListener(v -> new ICD_Codes_Part_3().execute());
        get_icd_part_4.setOnClickListener(v -> new ICD_Codes_Part_4().execute());
    }

    @SuppressLint("StaticFieldLeak")
    public class Token extends AsyncTask<Void, Void, Void> {

        String token;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(TOKEN_ENDPOINT);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("POST");

                // set parameters to post
                String urlParameters =
                        "client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") +
                        "&client_secret=" + URLEncoder.encode(CLIENT_SECRET, "UTF-8") +
                        "&scope=" + URLEncoder.encode(SCOPE, "UTF-8") +
                        "&grant_type=" + URLEncoder.encode(GRANT_TYPE, "UTF-8");
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // parse JSON response
                JSONObject jsonObj = new JSONObject(response.toString());
                token = jsonObj.getString("access_token");
            } catch (Exception e) {
                Log.e("BAMCHIKI", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
            sharedPref.setAccessToken(token);
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ICD_Codes_Part_0 extends AsyncTask<Void, Void, Void> {
        int i, j, k, l;
        int prev_i = -1, prev_j = -1, prev_k = -1, prev_l = -1;
        String releaseID;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Processing your code");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //2, 13, 19, 20
            switch (20) {
                case 1: releaseID = "I"; break;
                case 2: releaseID = "II"; break;
                case 3: releaseID = "III"; break;
                case 4: releaseID = "IV"; break;
                case 5: releaseID = "V"; break;
                case 6: releaseID = "VI"; break;
                case 7: releaseID = "VII"; break;
                case 8: releaseID = "VIII"; break;
                case 9: releaseID = "IX"; break;
                case 10: releaseID = "X"; break;
                case 11: releaseID = "XI"; break;
                case 12: releaseID = "XII"; break;
                case 13: releaseID = "XIII"; break;
                case 14: releaseID = "XIV"; break;
                case 15: releaseID = "XV"; break;
                case 16: releaseID = "XVI"; break;
                case 17: releaseID = "XVII"; break;
                case 18: releaseID = "XVIII"; break;
                case 19: releaseID = "XIX"; break;
                case 20: releaseID = "XX"; break;
                case 21: releaseID = "XXI"; break;
                case 22: releaseID = "XXII"; break;
            }

            Call<ResponseModel> call1 = RetrofitHelper.getInstance(MainActivity.this).getIcdInterface().get_ICD_codes_part1(releaseID);
            call1.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                    if(response.body() == null) {
                        Log.d("BAMCHIKI", "Why?");
                    }
                    else {
                        List<String> list1 = Objects.requireNonNull(response.body()).child;
                        String code = response.body().code;
                        String value = response.body().title.value;
                        for(j = 0; j < list1.size(); j++) {
                            String link1 = list1.get(j);
                            int index_1 = link1.lastIndexOf('/');
                            String id_1 = link1.substring(index_1 + 1);
                            Log.d("BAMCHIKI", "hi0_" + id_1);
                            databaseHelper_0.addData(code, value, id_1);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                    Log.d("BAMCHIKI1", t.getMessage());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(MainActivity.this, "Part 0 Completed " , Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ICD_Codes_Part_1 extends AsyncTask<Void, Void, Void> {
        int i, j, k, l;
        int prev_i = -1, prev_j = -1, prev_k = -1, prev_l = -1;
        String releaseID;
        ProgressDialog progressDialog;
        ArrayList<String> part_0 = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            part_0 = databaseHelper_0.getCode();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Processing your code");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(i = 0; i < part_0.size(); ) {
                if(prev_i != i && i < part_0.size()) {
                    prev_i = i;
                    Call<ResponseModel> call2 = RetrofitHelper.getInstance(MainActivity.this).getIcdInterface().get_ICD_codes_part1(part_0.get(i));
                    call2.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                            List<String> list2 = Objects.requireNonNull(response.body()).child;
                            String code = response.body().code;
                            String value = response.body().title.value;
                            for(k = 0; k < list2.size(); k++) {
                                String link2 = list2.get(k);
                                int index_2 = link2.lastIndexOf('/');
                                String id_2 = link2.substring(index_2 + 1);
                                ArrayList<String> temp = databaseHelper_0.getData(code);
                                if(id_2.contains("-")) {
                                    Log.d("BAMCHIKI", "hi1_" + temp.get(0) + "_" + temp.get(1) + "_" + id_2);
                                    databaseHelper_1.addData(temp.get(0), temp.get(1), id_2);
                                }
                                else {
                                    Log.d("BAMCHIKI", "hi1_" + temp.get(0) + "_" + temp.get(1) + "_" + code);
                                    databaseHelper_1.addData(temp.get(0), temp.get(1), code);
                                    i++;
                                    break;
                                }
                            }
                            if(k == list2.size()) {
                                i++;
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                            Log.d("BAMCHIKI2", t.getMessage());
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            part_0.clear();
            Toast.makeText(MainActivity.this, "Part 1 Completed " + databaseHelper_1.getCode().size(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ICD_Codes_Part_1_half extends AsyncTask<Void, Void, Void> {
        int i, j, k, l;
        int prev_i = -1, prev_j = -1, prev_k = -1, prev_l = -1;
        String releaseID;
        ProgressDialog progressDialog;
        ArrayList<String> part_0_half = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            part_0_half = databaseHelper_1.getCode();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Processing your code");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(i = 0; i < part_0_half.size(); ) {
                if(prev_i != i && i < part_0_half.size()) {
                    prev_i = i;
                    Call<ResponseModel> call2 = RetrofitHelper.getInstance(MainActivity.this).getIcdInterface().get_ICD_codes_part1(part_0_half.get(i));
                    call2.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                            List<String> list2 = Objects.requireNonNull(response.body()).child;
                            String code = response.body().code;
                            String value = response.body().title.value;
                            for(k = 0; k < list2.size(); k++) {
                                String link2 = list2.get(k);
                                int index_2 = link2.lastIndexOf('/');
                                String id_2 = link2.substring(index_2 + 1);
                                ArrayList<String> temp = databaseHelper_1.getData(code);
                                if(id_2.contains("-")) {
                                    Log.d("BAMCHIKI", "hi1_half_" + temp.get(0) + "_" + temp.get(1) + "_" + id_2);
                                    databaseHelper_1_half.addData(temp.get(0), temp.get(1), id_2);
                                }
                                else {
                                    Log.d("BAMCHIKI", "hi1_half_" + temp.get(0) + "_" + temp.get(1) + "_" + code);
                                    databaseHelper_1_half.addData(temp.get(0), temp.get(1), code);
                                    i++;
                                    break;
                                }
                            }
                            if(k == list2.size()) {
                                i++;
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                            Log.d("BAMCHIKI2", t.getMessage());
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            part_0_half.clear();
            Toast.makeText(MainActivity.this, "Part 1 half Completed " + databaseHelper_1_half.getCode().size(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ICD_Codes_Part_2 extends AsyncTask<Void, Void, Void> {
        int i, j, k, l;
        int prev_i = -1, prev_j = -1, prev_k = -1, prev_l = -1;
        String releaseID;
        ProgressDialog progressDialog;
        ArrayList<String> part_1 = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            part_1 = databaseHelper_1_half.getCode();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Processing your code");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("BAMCHIKI", part_1.size() + "");
            for(i = 0; i < part_1.size(); ) {
                if(prev_i != i && i < part_1.size()) {
                    prev_i = i;
                    Call<ResponseModel> call2 = RetrofitHelper.getInstance(MainActivity.this).getIcdInterface().get_ICD_codes_part1(part_1.get(i));
                    call2.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                            List<String> list2 = Objects.requireNonNull(response.body()).child;
                            String code = response.body().code;
                            String value = response.body().title.value;
                            for(k = 0; k < list2.size(); k++) {
                                String link2 = list2.get(k);
                                int index_2 = link2.lastIndexOf('/');
                                String id_2 = link2.substring(index_2 + 1);
                                ArrayList<String> temp = databaseHelper_1_half.getData(code);
                                Log.d("BAMCHIKI", "hi2_" + temp.get(0) + "_" + temp.get(1) + "_" + code + "_" + value + "_" + id_2);
                                databaseHelper_2.addData(temp.get(0), temp.get(1), code, value, id_2);
                            }
                            if(k == list2.size()) {
                                i++;
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                            Log.d("BAMCHIKI2", t.getMessage());
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            part_1.clear();
            Toast.makeText(MainActivity.this, "Part 2 Completed " + databaseHelper_2.getCode().size(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ICD_Codes_Part_3 extends AsyncTask<Void, Void, Void> {
        int i, j, k, l;
        int prev_i = -1, prev_j = -1, prev_k = -1, prev_l = -1;
        String releaseID;
        ProgressDialog progressDialog;
        ArrayList<String> part_2 = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            part_2 = databaseHelper_2.getCode();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Processing your code");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(i = 0; i < part_2.size(); ) {
                if(prev_i != i && i < part_2.size()) {
                    prev_i = i;
                    Call<ResponseModel> call3 = RetrofitHelper.getInstance(MainActivity.this).getIcdInterface().get_ICD_codes_part1(part_2.get(i));
                    call3.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                            List<String> list3 = Objects.requireNonNull(response.body()).child;
                            String code = response.body().code;
                            String value = response.body().title.value;
                            ArrayList<String> temp = databaseHelper_2.getData(code);

                            if (list3 == null) {
                                databaseHelper_3.addData(temp.get(0), temp.get(1), temp.get(2), temp.get(3), code, value, code);
                                Log.d("BAMCHIKI", "hi3_" + temp.get(0) + "_" + temp.get(1) + "_" + temp.get(2) + "_" + temp.get(3) + "_"+ code + "_" + value + "_" + "---");
                                i++;
                            }
                            else {
                                for (l = 0; l < list3.size(); l++) {
                                    String link3 = list3.get(l);
                                    int index_3 = link3.lastIndexOf('/');
                                    String id_3 = link3.substring(index_3 + 1);
                                    Log.d("BAMCHIKI", "hi3_" + temp.get(0) + "_" + temp.get(1) + "_" + temp.get(2) + "_" + temp.get(3) + "_"+ code + "_" + value + "_" + id_3);
                                    databaseHelper_3.addData(temp.get(0), temp.get(1), temp.get(2), temp.get(3), code, value, id_3);
                                }
                                if (l == list3.size()) {
                                    i++;
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                            Log.d("BAMCHIKI3", t.getMessage());
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            part_2.clear();
            Toast.makeText(MainActivity.this, "Part 3 Completed " + databaseHelper_3.getCode().size(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class ICD_Codes_Part_4 extends AsyncTask<Void, Void, Void> {
        int i, j, k, l;
        int prev_i = -1, prev_j = -1, prev_k = -1, prev_l = -1;
        String releaseID;
        ProgressDialog progressDialog;
        ArrayList<String> part_3 = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            part_3 = databaseHelper_3.getCode();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Processing your code");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(i = 0; i < part_3.size(); ) {
                if(prev_i != i && i < part_3.size()) {
                    prev_i = i;

                    Call<ICDCodeModel> call4 = RetrofitHelper.getInstance(MainActivity.this).getIcdInterface().get_ICD_codes_part2(part_3.get(i));
                    call4.enqueue(new Callback<ICDCodeModel>() {
                        @Override
                        public void onResponse(@NonNull Call<ICDCodeModel> call, @NonNull Response<ICDCodeModel> response) {
                            String code = Objects.requireNonNull(response.body()).code;
                            String value = response.body().title.value;
                            ArrayList<String> temp = databaseHelper_3.getData(code);
                            Log.d("BAMCHIKI", "hi3_" + temp.get(0) + "_" + temp.get(1) + "_" + temp.get(2) + "_" + temp.get(3) + "_" + temp.get(4) + "_" + temp.get(5) + "_"+ code + "_" + value);
                            databaseHelper_4.addData(temp.get(0), temp.get(1), temp.get(2), temp.get(3), temp.get(4), temp.get(5), code, value);
                            i++;
//                            new SendRequest(code, value).execute();
                        }

                        @Override
                        public void onFailure(@NonNull Call<ICDCodeModel> call, @NonNull Throwable t) {
                            Log.d("BAMCHIKI4", t.getMessage());
                        }
                    });
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(MainActivity.this, "Part 4 Completed", Toast.LENGTH_SHORT).show();
        }
    }

    public class SendRequest extends AsyncTask<Void, Void, Void> {

        String code, value;

        SendRequest(String code, String value) {
            this.code = code;
            this.value = value;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //INSERT SCRIPT URL
                URL url = new URL(Constants.SCRIPT_ID);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put(Constants.NAME, code);
                postDataParams.put(Constants.DESCRIPTION, value);

                //INSERT SHEET ID
                postDataParams.put(Constants.ID_SHEET, Constants.SHEET_ID);

                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }

                    in.close();
                } else {
                    Log.d("BAMCHIKI9", responseCode + "_");
                }
            } catch (Exception e) {
                Log.d("BAMCHIKI8", e.toString());
            }
            return null;
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}