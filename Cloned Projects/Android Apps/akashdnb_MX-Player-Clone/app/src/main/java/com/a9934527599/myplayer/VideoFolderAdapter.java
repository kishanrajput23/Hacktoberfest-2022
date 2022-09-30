package com.a9934527599.myplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoFolderAdapter extends RecyclerView.Adapter<VideoFolderAdapter.ViewHolder> {
    private ArrayList<MediaFiles> mediaFiles;
    private ArrayList<String> folderPath;
    private Context context;

    public VideoFolderAdapter(ArrayList<MediaFiles> mediaFiles, ArrayList<String> folderPath, Context context) {
        this.mediaFiles = mediaFiles;
        this.folderPath = folderPath;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.folder_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int indexPath=folderPath.get(position).lastIndexOf("/");
        String nameOfFolder= folderPath.get(position).substring(indexPath+1);
        holder.folderName.setText(nameOfFolder);
        holder.folderPath.setText(folderPath.get(position));
        holder.noOfFiles.setText(numberOfFiles(folderPath.get(position)) +" Videos");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,VideoFilesActivity.class);
                intent.putExtra("folderPath",folderPath.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return folderPath.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView folderName, folderPath, noOfFiles;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folderName= itemView.findViewById(R.id.folderName);
            folderPath= itemView.findViewById(R.id.folderPath);
            noOfFiles= itemView.findViewById(R.id.noOfFiles);
        }
    }
    public int numberOfFiles(String folder_name){
        int fn =0;
        String s="";
        try {
            for (MediaFiles mediaFiles1 : mediaFiles){
                s= mediaFiles1.getPath().substring(0,mediaFiles1.getPath().lastIndexOf("/"));
                if (s.endsWith(folder_name))
                    fn++;
                }
        }catch (Exception e){
           Toast.makeText(context, " "+e, Toast.LENGTH_SHORT).show();
        }
//folderPath.get(position).substring(indexPath+1);
        return fn;
    }
}
