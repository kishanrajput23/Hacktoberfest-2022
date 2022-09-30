package com.a9934527599.myplayer;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoFilesAdapter extends RecyclerView.Adapter<VideoFilesAdapter.ViewHolder> {
    private ArrayList<MediaFiles> videoList;
    private Context context;
    BottomSheetDialog bottomSheetDialog;
   // public int DELETE_REQUEST_CODE= 101;
   // public int RENAME_REQUEST_CODE= 102;
   // Bundle dataBundle= new Bundle();
   // Uri ruri;
   // public int pos=0;
    ContentResolver contentResolver;
    VideoFilesActivity activity = new VideoFilesActivity();

    public VideoFilesAdapter(ArrayList<MediaFiles> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.videoName.setText(videoList.get(position).getDisplayName());
        String size= android.text.format.Formatter.formatFileSize(context,Long.parseLong(videoList.get(position).getSize()));
        holder.videoSize.setText(size);
        double milisecond= Double.parseDouble(videoList.get(position).getUration());
        holder.videoDuration.setText(timeConversion((long) milisecond));
        Glide.with(context).load(videoList.get(position).getPath()).into(holder.thumbnail);

        holder.menu_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog= new BottomSheetDialog(context,R.style.BottomSheetTheme);
                View bsView= LayoutInflater.from(context).inflate(R.layout.video_bs_layout,
                        v.findViewById(R.id.bottom_sheet));

                bsView.findViewById(R.id.bs_play).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.itemView.performClick();
                        bottomSheetDialog.dismiss();
                    }
                });
                bsView.findViewById(R.id.bs_rename).setOnClickListener(new View.OnClickListener(){

                   @RequiresApi(api = Build.VERSION_CODES.R)
                   @Override
                   public void onClick(View v) {
                       boolean check= checkPermission();

                       if (check){
                           AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                           alertDialog.setTitle("Rename to");
                           EditText editText= new EditText(context);
                           String path= videoList.get(position).getPath();
                           final File file= new File(path);
                           file.setWritable(true);
                           String videoName= file.getName();
                           videoName= videoName.substring(0,videoName.lastIndexOf("."));
                           editText.setText(videoName);
                           alertDialog.setView(editText);
                           editText.requestFocus();

                           alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @RequiresApi(api = Build.VERSION_CODES.R)
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   if (TextUtils.isEmpty(editText.getText().toString())){
                                       Toast.makeText(context, "Can't rename empty file", Toast.LENGTH_SHORT).show();
                                       return;
                                   }
                                   String OnlyPath= file.getParentFile().getAbsolutePath();
                                   String ext= file.getAbsolutePath();
                                   ext= ext.substring(ext.lastIndexOf("."));
                                   String newPath= OnlyPath+"/"+editText.getText().toString()+ext;
                                   File newFile = new File(newPath);
                                   Uri contentUri = ContentUris
                                           .withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                                   Long.parseLong(videoList.get(position).getId()));
                                   String path =videoList.get(position).getPath();
                                   File file= new File(videoList.get(position).getPath());

                                   if (file.renameTo(newFile)){
                                       if (path.equals(newPath)){
                                       }else {
                                           contentResolver= context.getContentResolver();
                                           contentResolver.delete(contentUri,null,null);
                                           Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                           intent.setData(Uri.fromFile(newFile));
                                           context.getApplicationContext().sendBroadcast(intent);
                                       }
                                       SystemClock.sleep(20);
                                       ((Activity)context).recreate();
                                       VideoFilesActivity.getInstance().showVideoFiles();
                                       notifyDataSetChanged();
                                       notifyItemRemoved(position);
                                       notifyItemInserted(0);
                                       dialog.dismiss();
                                       Toast.makeText(context, "Video Renamed", Toast.LENGTH_SHORT).show();
                                   }
                                   VideoFilesActivity.getInstance().showVideoFiles();
                               }

                           });
                           alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dialog.dismiss();
                               }
                           });
                           alertDialog.create().show();
                           bottomSheetDialog.dismiss();
                       }
                       else {
                           Toast.makeText(context, "Permission Required to Rename a File", Toast.LENGTH_SHORT).show();
                           bottomSheetDialog.dismiss();
                       }

                   }

                });
                bsView.findViewById(R.id.bs_share).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri= Uri.parse(videoList.get(position).getPath());
                        Intent shareIntent=new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("video/*");
                        shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
                        context.startActivity(Intent.createChooser(shareIntent,"Share video via"));
                        bottomSheetDialog.dismiss();
                    }
                });
                bsView.findViewById(R.id.bs_delete).setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View v) {
                        boolean check=checkPermission();
                        if (check){
                            Uri contentUri = ContentUris
                                    .withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                            Long.parseLong(videoList.get(position).getId()));
                            File file= new File(videoList.get(position).getPath());

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                            alertDialog.setTitle("Delete");
                            alertDialog.setMessage("Do you want to delete this Video");

                            alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (file.delete()) {
                                        contentResolver=context.getContentResolver();
                                        contentResolver.delete(contentUri,null,null);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, videoList.size());
                                        notifyDataSetChanged();
                                        VideoFilesActivity.getInstance().showVideoFiles();
                                       // ((Activity)context).recreate();
                                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.show();
                            //deleteVideo(contentUri,context);
                            bottomSheetDialog.dismiss();
                        }
                        else {
                            Toast.makeText(context, "Permission Required", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        }
                    }
                });
                bsView.findViewById(R.id.bs_properties).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                        alertDialog.setTitle("Properties");

                        String first= "File: " + videoList.get(position).getPath();
                        String path= videoList.get(position).getPath();
                        int i= path.lastIndexOf("/");
                        String secnd= "Path: " + path.substring(0,i);
                        String third = "Size: " + android.text.format.Formatter
                                .formatFileSize(context, Long.parseLong(videoList.get(position).getSize()));
                        String furth= "length: " + timeConversion((long) milisecond);
                        String nameWithFrmat =videoList.get(position).getDisplayName();
                        int index = nameWithFrmat.lastIndexOf(".");
                        String frmat = nameWithFrmat.substring(index+1);
                        String fifth = "Format: " +frmat;

                        MediaMetadataRetriever metadataRetriever= new MediaMetadataRetriever();
                        metadataRetriever.setDataSource(videoList.get(position).getPath());
                        String height = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
                        String width = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
                        String sixth = "Resolution: "+ width +"x"+ height;

                        alertDialog.setMessage(first+"\n\n"+secnd +"\n\n"+ third+ "\n\n" +
                                furth+"\n\n"+ fifth+ "\n\n"+ sixth);

                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                }
                        });
                        alertDialog.create().show();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bsView);
                bottomSheetDialog.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoPlayerActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("Video_title",videoList.get(position).getDisplayName());
                Bundle bundle= new Bundle();
                bundle.putParcelableArrayList("videoArrayList",videoList);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public boolean checkPermission(){
       // boolean sh = Environment.isExternalStorageManager();
        if (Environment.isExternalStorageManager()){
            return true;
        }else {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION}, 111);
            return false;
     }
    }
  /*  @RequiresApi(api = Build.VERSION_CODES.R)
    private void renameVideo(Uri uri, String path,String newPath,int pos, Context context) {

        contentResolver = context.getContentResolver();
        List<Uri> uriList = new ArrayList<>();
        Collections.addAll(uriList,uri);
        PendingIntent pendingIntent= MediaStore.createWriteRequest(contentResolver,uriList);

        dataBundle.putString("path",path);
        dataBundle.putString("newPath",newPath);
        ruri= uri;

        try {
            ((Activity)context).startIntentSenderForResult(pendingIntent.getIntentSender(),
                    RENAME_REQUEST_CODE,null,0,0,0);
            //rename();

        }catch (IntentSender.SendIntentException e){
            e.printStackTrace();
        }
    }

    public void rename() {
        String path= dataBundle.getString("path");
        String newPath= dataBundle.getString("newPath");

        String nameExt= newPath.substring(newPath.lastIndexOf("/")+1);
        String name = nameExt.substring(0,nameExt.lastIndexOf("."));

        ContentValues contentValues= new ContentValues();
        contentValues.put(MediaStore.Video.VideoColumns.IS_PENDING,1);
        contentResolver.update(ruri,contentValues,null,null);
        contentValues.clear();
        contentValues.put(MediaStore.Video.VideoColumns.DISPLAY_NAME,name);
        contentValues.put(MediaStore.Video.VideoColumns.IS_PENDING,0);
        contentResolver.update(ruri,contentValues,null,null);

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void deleteVideo(Uri uri, Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        List<Uri> uriList = new ArrayList<>();
        Collections.addAll(uriList,uri);
        PendingIntent pendingIntent=MediaStore.createDeleteRequest(contentResolver,uriList);

        try {
            ((Activity)context).startIntentSenderForResult(pendingIntent.getIntentSender(),
                    DELETE_REQUEST_CODE,null,0,0,0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    public Bundle getDataBundle()
    {
        String path= dataBundle.getString("path");
        String newPath= dataBundle.getString("newPath");
        return dataBundle;
    }

    public int getPos() {
        return pos;
    }*/

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail,menu_more;
        TextView videoName,videoSize,videoDuration;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=itemView.findViewById(R.id.thumbnail);
            menu_more=itemView.findViewById(R.id.video_menu_more);
            videoName=itemView.findViewById(R.id.video_name);
            videoSize=itemView.findViewById(R.id.video_size);
            videoDuration=itemView.findViewById(R.id.duration);
        }
    }
    public String timeConversion(long value){
        String videoTime;
        int duration= (int) value;
        int hrs= duration/3600000;
        int min= ((duration/60000)%60);
        int sec= (duration%60000/1000);
        if(hrs>0)
        {
            videoTime= String.format("%02d:%02d:%02d",hrs,min,sec);
        }else {
            videoTime=String.format("%02d:%02d",min,sec);
        }
        return videoTime;
    }

    void updateVideoFiles(ArrayList<MediaFiles> files){
        videoList = new ArrayList<>();
        for (MediaFiles mediaFiles: files){
            videoList.add(mediaFiles);
        }
        notifyDataSetChanged();
    }
}
