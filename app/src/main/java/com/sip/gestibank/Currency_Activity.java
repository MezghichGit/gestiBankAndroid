package com.sip.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sip.gestibank.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.sip.gestibank.remote.CurrencyService;
import com.sip.gestibank.models.Currency;

import org.json.JSONException;
import org.json.JSONObject;

public class Currency_Activity extends AppCompatActivity {
    CurrencyService currencyService;
    double cours;
    double input;
    double res;

    EditText myData;
    TextView myRes;

    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        currencyService =  APIUtils.getCurrencyService();

         myData = (EditText) findViewById(R.id.myInput);
         myRes =(TextView ) findViewById(R.id.textViewRes);
         radioGroup = (RadioGroup) findViewById(R.id.devise);

    }

    public void getCurrency(View v){

        input = Double.parseDouble(myData.getText().toString());

        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
        String to = (String) radioButton.getText();

        String rul = "live?access_key=c7dd6f03f1ee260993147bfc06ab9673&currencies="+to+"&format=1/";
        Call<Currency> call = currencyService.getData(rul);
        call.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                if(response.isSuccessful()){
                    String quotes = response.body().getQuotes().toString();
                    // récupération du cours
                    String rate = quotes.substring(quotes.indexOf("=")+1,quotes.indexOf("}"));
                    cours = Double.parseDouble(rate);
                    res = cours*input;
                    res = (double)((int)(res*100))/100;
                    myRes.setText("Résultat conversion = "+Double.valueOf(res).toString());
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Log.e("Problem: ", t.getMessage());
            }
        });

    }

}