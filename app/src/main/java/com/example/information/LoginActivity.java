package com.example.information;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LoginActivity extends Activity implements LoadJson.OnFinishLoadJSonListener {
    EditText edtUser, edtPass;
    CheckBox checkBox;
    Context context;
    LoadJson loadJson;
    ProgressDialog progressDialog;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        edtPass = findViewById(R.id.edtPass);
        edtUser = findViewById(R.id.edtUser);
        checkBox = findViewById(R.id.checkbox);
        context = this;
        connectView();


        loadJson = new LoadJson();
        loadJson.setOnFinishLoadJSonListener(this);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getResources().getString(R.string.wait));

    }

    public void login(View v) {
        String user = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        if (user.length() == 0 || pass.length() == 0) {
            edtPass.requestFocus();
            edtUser.requestFocus();
            Toast.makeText(getApplicationContext(), "Để trống là sai tác phong nha bạn :)))", Toast.LENGTH_SHORT).show();
        }
        if (checkBox.isChecked()) {
            Var.save(context, Var.KEY_USER, user);
            Var.save(context, Var.KEY_PASS, pass);
        } else {
            Var.save(context, Var.KEY_USER, null);
            Var.save(context, Var.KEY_PASS, null);
        }

        HashMap<String, String> map = new HashMap<>();
        map.put(Var.KEY_USER, user);
        map.put(Var.KEY_PASS, pass);

        loadJson.sendDataToServer(Var.METHOD_LOGIN, map);
        progressDialog.show();

    }

    public void connectView() {
        user = Var.get(context, Var.KEY_USER);
        pass = Var.get(context, Var.KEY_PASS);

        if (user != null && pass != null) {
            edtUser.setText(user);
            edtPass.setText(pass);
            checkBox.setChecked(true);
        }

    }

    @Override
    public void finishLoadJSon(String error, String json) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        try {
            if (json != null) {

                int jsonStart = json.indexOf("{");
                int jsonEnd = json.lastIndexOf("}");
                if (jsonStart >= 0 && jsonEnd >= 0 && jsonEnd > jsonStart) {
                    json = json.substring(jsonStart, jsonEnd + 1);
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getBoolean(Var.KEY_LOGIN)) {
                        Var.showToast(context, context.getResources().getString(R.string.login_success));
                        Intent intent = new Intent(context, MainWindow.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Var.showToast(context, context.getResources().getString(R.string.login_fail));
                    }
                } else {
                    Var.showToast(context, error);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
