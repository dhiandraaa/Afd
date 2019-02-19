package com.dicoding.dhiandraaa.afd;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dicoding.dhiandraaa.afd.api.ApiRequest;
import com.dicoding.dhiandraaa.afd.api.Retroserver;
import com.dicoding.dhiandraaa.afd.model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText kdAfd, alias, kdUnit;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kdAfd = (EditText) findViewById(R.id.kd_afd);
        alias = (EditText) findViewById(R.id.alias);
        kdUnit = (EditText) findViewById(R.id.kd_unit);
        Button btnSimpan = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Send Data..");
                pd.setCancelable(false);
                pd.show();

                String skdAfd = kdAfd.getText().toString();
                String salias = alias.getText().toString();
                String skdUnit = kdUnit.getText().toString();

                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);

                Call<ResponseModel> sendAfd = api.sendAfdeling(skdAfd, salias, skdUnit);
                sendAfd.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        pd.hide();
                        Log.d("RETRO", "retro" + response.body().toString());
                        String code = response.body().getKode();

                        if (code.equals("1"))
                        {
                            Toast.makeText(MainActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Data Gagal Disimpan", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO","Failure : " + "Mengirim Request");
                    }
                });

            }
        });
    }
}
