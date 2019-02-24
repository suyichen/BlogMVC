package com.syc.normal;

import android.os.Bundle;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.syc.bean.UserBean;
import com.syc.blogmvc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author suyichen
 * @date 2019/2/18
 */

public class NormalLogin extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NormalLogin";
    private static final String URL = "https://www.apiopen.top/login?";

    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_normal_activity);

        usernameEdit = this.findViewById(R.id.edit_username);
        passwordEdit = this.findViewById(R.id.edit_password);
        login = this.findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.login:
                String username = usernameEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                goLogin(username,password);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param username  get to usernameEdit
     * @param password  get to passwordEdit
     */
    private void goLogin(String username,String password) {

        boolean isEditOK = isEditOK(username,password);

        if(!isEditOK){
            return;
        }

        startLogin(username,password);

    }

    private void startLogin(String username,String password) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL+"key="+"00d91e8e0cca2b76f515926a36db68f5"+"&phone="+username+"&passwd="+password)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"client is failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG,"client is response,and message: + " + response.body().string());

                 parseJsonWithJsonObject(response);
//                Gson gson = new Gson();
//                UserBean userBean = gson.fromJson(response);
            }
        });
    }

    private void parseJsonWithJsonObject(Response response) throws IOException {
        String responseData = response.body().string();
        try{
            JSONArray jsonArray=new JSONArray(responseData);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("code");
                String name=jsonObject.getString("phone");
                Log.e(TAG,"id:" + id + "; phone: " + name);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isEditOK(String username, String password) {
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"username can not be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"password can not be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
