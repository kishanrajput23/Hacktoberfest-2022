package com.a9934527599.myplayer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class folderActivity extends AppCompatActivity {

    private ArrayList<MediaFiles> mediaFiles= new ArrayList<>();
    private ArrayList<String> allFolderList= new ArrayList<>();
    RecyclerView recyclerView;
    VideoFolderAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.SKY_BLUE));

      //  if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()){
                Toast.makeText(getApplicationContext(),"Permission Required",Toast.LENGTH_SHORT).show();
                SharedPreferences shared= getSharedPreferences("AllowAccess",MODE_PRIVATE);
                SharedPreferences.Editor editor= shared.edit();
                editor.putString("Allow","");
                editor.apply();
                startActivity(new Intent(folderActivity.this, allowAcessActivity.class));
                finish();
            }
        }

        recyclerView= findViewById(R.id.folders_RV);
        swipeRefreshLayout= findViewById(R.id.srl);
        showFolders();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        }

    private void showFolders() {
        mediaFiles=fetchMedia();
        adapter= new VideoFolderAdapter(mediaFiles,allFolderList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter.notifyDataSetChanged();
    }

    private ArrayList<MediaFiles> fetchMedia() {
        ArrayList<MediaFiles> mediaFilesArrayList=new ArrayList<>();
        Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String sortorder= MediaStore.Video.Media.DATE_ADDED+" ASC";

        Cursor cursor= getContentResolver().query(uri,null,null,null,sortorder);
        if(cursor!=null && cursor.moveToNext()){
            do{
                String  id= cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._ID));
                String  title= cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                String  dispayName= cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                String  size= cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                String  duration= cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                String  path= cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                String  dateAdded= cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATE_ADDED));

               MediaFiles mediaFiles= new MediaFiles(id,title,dispayName,size,duration,path,dateAdded);

               int index= path.lastIndexOf("/");
               String subString= path.substring(0,index);
               if(!allFolderList.contains(subString)){
                   allFolderList.add(subString);
               }
               mediaFilesArrayList.add(mediaFiles);
            }while (cursor.moveToNext());
        }
        return mediaFilesArrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.folder_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.rate_us:
                Uri uri= Uri.parse("https://play.google.com/store/apps/dtails?id="
                +getApplicationContext().getPackageName());
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            break;
            case R.id.refresh:
                finish();
                startActivity(getIntent());
                break;
            case R.id.share_app:
                Intent share_intent=new Intent(Intent.ACTION_SEND);
                share_intent.putExtra(Intent.EXTRA_TEXT,"Check this App via"+"\n"+
                        "https://play.google.com/store/apps/dtails?id="
                                +getApplicationContext().getPackageName());
                share_intent.setType("text/plain");
                startActivity(Intent.createChooser(share_intent,"Share app via"));
                break;
        }
        return true;
    }
}