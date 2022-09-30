package com.a9934527599.myplayer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class allowAcessActivity extends AppCompatActivity {

    Button allow_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_acess);

        SharedPreferences shared= getSharedPreferences("AllowAccess",MODE_PRIVATE);
        String value= shared.getString("Allow","");

        if(value.equals("Ok")){
            startActivity(new Intent(allowAcessActivity.this, folderActivity.class));
            finish();
        }

        allow_btn = findViewById(R.id.allow_button);
        allow_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
//ContextCompat.checkSelfPermission(getApplicationContext(),
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                if (Environment.isExternalStorageManager()) {

                    SharedPreferences.Editor editor= shared.edit();
                    editor.putString("Allow","Ok");
                    editor.apply();
                    startActivity(new Intent(allowAcessActivity.this, folderActivity.class));
                    finish();
                } else {
                    ActivityCompat.requestPermissions(allowAcessActivity.this,
                            new String[]{Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION}, 1);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            String per = permissions[0];
          //  if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()){
                    SharedPreferences shared= getSharedPreferences("AllowAccess",MODE_PRIVATE);
                    SharedPreferences.Editor editor= shared.edit();
                    editor.putString("Allow","Ok");
                    editor.apply();
                    startActivity(new Intent(allowAcessActivity.this, folderActivity.class));
                    finish();
                } else {
                    boolean showRationale = shouldShowRequestPermissionRationale(per);
                    if (!showRationale) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("App Permission")
                                .setMessage("For Playing videos, you must allow this app to access video files on your device"
                                        + "\n\n" + "Now follow the following steps" + "\n\n" + "Open Settings from below Button" + "\n"
                                        + "Click on Permissions"
                                        + "\n" + "Allow access for storage")
                                .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                                        intent.setData(uri);
                                        startActivityForResult(intent, 12);

                                    }
                                }).create().show();

                    } else {
                     //   ActivityCompat.requestPermissions(allowAcessActivity.this,
                      //          new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        ActivityCompat.requestPermissions(allowAcessActivity.this,
                                new String[]{Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION}, 1);
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onResume() {
        super.onResume();
        if (Environment.isExternalStorageManager()){
            SharedPreferences shared= getSharedPreferences("AllowAccess",MODE_PRIVATE);
            SharedPreferences.Editor editor= shared.edit();
            startActivity(new Intent(allowAcessActivity.this, folderActivity.class));
            finish();
        }
    }
}