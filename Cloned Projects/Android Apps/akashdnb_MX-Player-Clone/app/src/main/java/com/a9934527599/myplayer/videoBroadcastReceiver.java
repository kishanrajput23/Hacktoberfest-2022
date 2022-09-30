package com.a9934527599.myplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.PathUtils;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class videoBroadcastReceiver extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_broadcast_receiver);

        textView= findViewById(R.id.tvdata);

        Intent intent = getIntent();
        String action= intent.getAction();

        if (Intent.ACTION_VIEW.equals(action)&& intent.getType().equals("text/plain")) {
            Uri uri = Uri.parse(intent.getStringExtra(Intent.EXTRA_TEXT));
            play(uri);
        }
        else if (Intent.ACTION_VIEW.equals(action)&& intent.getData()!=null){
            Uri uri = intent.getData();
            play(uri);
        }
        else {
            Intent intent1= new Intent(getApplicationContext(),folderActivity.class);
            startActivity(intent1);
            finish();
        }

    }

    void play(Uri uri){

       // String selecion= MediaStore.Video.Media.DATA+" like ?";
       // String[] selectionArg= new String[]{"%"+folderPath+"%"};
        ArrayList<MediaFiles> videoList =new ArrayList<>();

        Cursor cursor= getContentResolver().query(uri,null,null,null, null);

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

                    videoList.add(mediaFiles);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){e.printStackTrace();}
        textView.setText(videoList.get(0).getTitle());

        Intent i = new Intent(getApplicationContext(),VideoPlayerActivity.class);
        i.putExtra("position",0);
        i.putExtra("Video_title",videoList.get(0).getTitle());
        Bundle bundle= new Bundle();
        bundle.putParcelableArrayList("videoArrayList",videoList);
        i.putExtras(bundle);
         startActivity(i);

    }

    @Override
    protected void onRestart() {
        Intent intent1= new Intent(getApplicationContext(),folderActivity.class);
        startActivity(intent1);
        super.onRestart();
        finish();
    }
}