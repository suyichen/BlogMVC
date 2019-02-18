package com.syc.normal;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syc.blogmvc.R;

/**
 * Created by suyichen on 2019/2/18.
 */

public class NormalLogin extends AppCompatActivity implements View.OnClickListener {

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
