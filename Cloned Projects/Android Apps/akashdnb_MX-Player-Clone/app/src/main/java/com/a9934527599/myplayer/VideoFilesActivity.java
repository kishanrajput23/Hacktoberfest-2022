package com.a9934527599.myplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.exoplayer2.source.ConcatenatingMediaSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoFilesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String MY_PREF = "my pref";
    private ArrayList<MediaFiles> videoFilesArrayList;
    VideoFilesAdapter adapter;
    RecyclerView recyclerView;
    String folder_path;
    String sortorder;
    SwipeRefreshLayout swipeRefreshLayout;
    private static VideoFilesActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_files);
        instance=this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.SKY_BLUE));
        }


        folder_path= getIntent().getStringExtra("folderPath");

        int indexPath=folder_path.lastIndexOf("/");
        String nameOfFolder= folder_path.substring(indexPath+1);
        getSupportActionBar().setTitle(nameOfFolder);

        recyclerView= findViewById(R.id.videos_RV);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);

        showVideoFiles();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showVideoFiles();
                //adapter.notifyAll();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void showVideoFiles(){
        videoFilesArrayList=fetchVideos(folder_path);
        adapter= new VideoFilesAdapter(videoFilesArrayList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter.notifyDataSetChanged();
    }

   private ArrayList<MediaFiles> fetchVideos(String folderPath) {
        SharedPreferences sharedPreferences= getSharedPreferences(MY_PREF,MODE_PRIVATE);
        String sort_value = sharedPreferences.getString("sort","abcd");

        ArrayList<MediaFiles> videoFiles= new ArrayList<>();
       Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
       if (sort_value.equals("sortName")){
           sortorder=MediaStore.MediaColumns.DISPLAY_NAME+ " ASC";
       }
       else if (sort_value.equals("sortSize")){
           sortorder=MediaStore.MediaColumns.SIZE+ " DESC";
       }
       else if (sort_value.equals("sortLength")){
           sortorder=MediaStore.MediaColumns.DURATION+ " DESC";
       }
       else  {
           sortorder=MediaStore.MediaColumns.DATE_ADDED+ " ASC";
       }

       String selecion=MediaStore.Video.Media.DATA+" like ?";
       String[] selectionArg= new String[]{"%"+folderPath+"%"};

       Cursor cursor= getContentResolver().query(uri,null,selecion,selectionArg,
               sortorder);

       try {
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

                   videoFiles.add(mediaFiles);
               }while (cursor.moveToNext());
           }
       }catch (Exception e){e.printStackTrace();}
       Collections.reverse(videoFiles);
       return videoFiles;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==111&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "\nPermission Granted", Toast.LENGTH_SHORT).show();
        }
        else {
            boolean ratinabe= shouldShowRequestPermissionRationale(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            if (!ratinabe){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("App Permission")
                        .setMessage("For , you must allow this app to acess video files on your device"
                                + "\n\n" + "Now follow the following steps" + "\n\n" + "Open Settings from below Button" + "\n"
                                + "Click on Permissions"
                                + "\n" + "Allow access for storage  " + ratinabe)
                        .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, 112);

                            }
                        }).create().show();
            }else {
                ActivityCompat.requestPermissions(VideoFilesActivity.this,
                        new String[]{Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION}, 111);
            }
           // Toast.makeText(this, "Required \n"+grantResults[0], Toast.LENGTH_SHORT).show();
        }
    }


    public static VideoFilesActivity getInstance() {
        return instance;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vid_menu,menu);
        MenuItem menuItem= menu.findItem(R.id.search_vide);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences preferences = getSharedPreferences(MY_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int id= item.getItemId();
        switch (id) {
            case R.id.refresh_files:
                finish();
                startActivity(getIntent());
                break;
            case R.id.sort_by:
               // AlertDialog.Builder= new AlertDialog.Builder(this);
                android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
                alertDialog.setTitle("Sort By");
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.apply();
                        finish();
                        startActivity(getIntent());
                        dialog.dismiss();
                    }
                });
                String[] items = {"Name (A to Z)", "Size (Small to Big)", "Date (New to Old)" ,
                        "Length (Short to Long)"};
                alertDialog.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                editor.putString("sort","sortName");
                                break;
                            case 1:
                                editor.putString("sort","sortSize");
                                break;
                            case 2:
                                editor.putString("sort","sortDate");
                                break;
                            case 3:
                                editor.putString("sort","sortLength");
                                break;
                        }
                    }
                });
                alertDialog.create().show();
                  break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String inputs =newText.toLowerCase();
        ArrayList<MediaFiles> mediaFiles = new ArrayList<>();
        for (MediaFiles media: videoFilesArrayList){
            if (media.getTitle().toLowerCase().contains(inputs)){
                mediaFiles.add(media);
            }
        }
        adapter.updateVideoFiles(mediaFiles);
        return false;
    }
}