package com.a9934527599.myplayer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.SensorDirectChannel;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;

public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<MediaFiles> mVideoFiles=new ArrayList<>();
    PlayerView playerView;
    SimpleExoPlayer player;
    ImageView videoBack, lock,unlock,scaling,rew,ffwd,ffwd_effect,rew_effect;
    RelativeLayout root;
    int position;
    String videoTitle;
    TextView title,ffwd_text,rew_text;
    private  ControlsMode controlsMode;

    public enum ControlsMode{
        LOCK,FULLSCREEN;
    }
    ConcatenatingMediaSource concatenatingMediaSource;
    ImageView nextButton,prevButton;
    private View decrorView;
    private Context context= this;

    //seekbar variabes
    LinearLayout s_bar,s_bar_brightness;
    Layout back_effect;
    SeekBar seekBarVol,seekBarBright;
    TextView vText,bText;
    ImageView vImage,bImage;
    AudioManager audioManager;
    int vProgress,bProgress,perBrightness;
    GestureDetector gestureDetector;
    //seekbar variabes

    //hriznta recycer view variabes
    RecyclerView recyclerView_icons;
    private  ArrayList<iconModel> iconModelArrayList= new ArrayList<>();
    PlaybackItemsAdapter  playbackItemsAdapter;
    OrientationEventListener morientationEventListener;
    boolean expand = false;
    View night_mde;
    boolean dark = false;
    boolean mute= false;
    boolean portrait = false,islock=false;

    PlaybackParameters parameters;
    float speed=1.0f;
    int checkedItem = 2;
    //hriznta recycer view variabes rotationListenerHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_video_player);
        getSupportActionBar().hide();
        decrorView= getWindow().getDecorView();
        decrorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility==0)
                    decrorView.setSystemUiVisibility(setInvisible());
            }
        });

        playerView= findViewById(R.id.exoPlayer_view);
        position=getIntent().getIntExtra("position",1);
        videoTitle=getIntent().getStringExtra("Video_title");
        mVideoFiles= getIntent().getExtras().getParcelableArrayList("videoArrayList");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            screen_orientation();
        }

        videoBack= findViewById(R.id.video_back);
        lock= findViewById(R.id.exo_lock);
        unlock= findViewById(R.id.unlock);
        scaling = findViewById(R.id.exo_sca);
        rew= findViewById(R.id.exo_rew);
        ffwd= findViewById(R.id.exo_ffwd);
        root=findViewById(R.id.root_layout);

        nextButton=findViewById(R.id.exo_next);
        prevButton=findViewById(R.id.exo_previous);
        nextButton.setOnClickListener(this::onClick);
        prevButton.setOnClickListener(this::onClick);
        title= findViewById(R.id.video_title);
        title.setText(videoTitle);

        recyclerView_icons = findViewById(R.id.recyclerView_icons);
        night_mde=findViewById(R.id.night_mde);
        parameters= new PlaybackParameters(speed);

        //seekbar custom_back
       // back_effect= (findViewById(R.id.back_ef).findViewById(R.id.)
        s_bar=findViewById(R.id.s_bar);
        s_bar_brightness= findViewById(R.id.s_bar_brightness);
        seekBarVol=s_bar.findViewById(R.id.seekBar);
        seekBarBright= s_bar_brightness.findViewById(R.id.seekBar);
        vText= s_bar.findViewById(R.id.vText);
        bText=s_bar_brightness.findViewById(R.id.vText);
        vImage= s_bar.findViewById(R.id.vImage);
        bImage= s_bar_brightness.findViewById(R.id.vImage);

        seekBarBright.setMax(255);
        int brightness = Settings.System.getInt(getApplicationContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS,0);
        perBrightness=brightness;
        audioManager= (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        int mediaVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int setVol= (int) Math.ceil((((double) mediaVol/(double) maxVol)*100));
        vText.setText(String.valueOf(setVol));
        seekBarVol.setProgress(setVol);


        gestureDetector=new GestureDetector(this, new MyGestureListener());

        View.OnTouchListener touchListener= new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               // Toast.makeText(context, ""+event.getActionMasked(), Toast.LENGTH_SHORT).show();
                if ((event.getActionMasked()==MotionEvent.ACTION_UP)
                        ||((event.getActionMasked()==MotionEvent.ACTION_DOWN))){
                    Log.i("event", String.valueOf(event.getAction()));
                    new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        s_bar.setVisibility(View.GONE);
                        s_bar_brightness.setVisibility(View.GONE);

                    }
                },3000);
                }

                return gestureDetector.onTouchEvent(event);
            }
        };
        playerView.setOnTouchListener(touchListener);

        seekBarVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int maxV= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int setVol= (int) Math.ceil(progress*maxV/100);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,setVol,0);
                //vProgress=progress;
                seekBarVol.setProgress(Math.abs(progress));
                vText.setText(String.valueOf(progress));
                // Toast.makeText(VideoPlayerActivity.this, ""+maxV, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int mediaVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int setVol= (int) Math.ceil((((double) mediaVol/(double) maxVol)*100));
                vText.setText(String.valueOf(setVol));
                seekBarVol.setProgress(setVol);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        s_bar.setVisibility(View.GONE);
//                    }
//                },3000);
            }
        });

        seekBarBright.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                boolean canWrite= Settings.System.canWrite(getApplicationContext());
                if (canWrite){
                    int sBrightness= progress*255/255;
                    bText.setText(sBrightness+"");
                    Settings.System.putInt(context.getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS_MODE,
                            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                    Settings.System.putInt(context.getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS,sBrightness);
                }else {
                    Toast.makeText(context, "unable to Write", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    intent.setData(Uri.parse("package:"+ context.getPackageName()));
                    startActivityForResult(intent,0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        s_bar_brightness.setVisibility(View.GONE);
//                    }
//                },3000);
            }
        });
        //seekbar

        videoBack.setOnClickListener(this);
        unlock.setOnClickListener(this);
        lock.setOnClickListener(this);

        scaling.setOnClickListener(firstListener);

        iconModelArrayList.add(new iconModel(R.drawable.ic_right," "));
        iconModelArrayList.add(new iconModel(R.drawable.ic_dark,"Night"));
        iconModelArrayList.add(new iconModel(R.drawable.ic_volume,"Mute"));
        iconModelArrayList.add(new iconModel(R.drawable.ic_rotate,"Rotate"));

        playbackItemsAdapter= new PlaybackItemsAdapter(iconModelArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
               RecyclerView.HORIZONTAL,true);
        recyclerView_icons.setLayoutManager(layoutManager);
        recyclerView_icons.setAdapter(playbackItemsAdapter);
        playbackItemsAdapter.notifyDataSetChanged();

        playbackItemsAdapter.setOnItemClickListener(new PlaybackItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position== 0){
                    if (expand){
                    iconModelArrayList.clear();
                    iconModelArrayList.add(new iconModel(R.drawable.ic_right," "));
                    iconModelArrayList.add(new iconModel(R.drawable.ic_dark,"Night"));
                    iconModelArrayList.add(new iconModel(R.drawable.ic_volume_off,"Mute"));
                    iconModelArrayList.add(new iconModel(R.drawable.ic_rotate,"Rotate"));
                    playbackItemsAdapter.notifyDataSetChanged();
                        expand=false;
                    }
                    else {

                        if (iconModelArrayList.size()==4){
                            iconModelArrayList.add(new iconModel(R.drawable.ic_volume_off,"Volume"));
                            iconModelArrayList.add(new iconModel(R.drawable.ic_brightness,"Brightness"));
                            iconModelArrayList.add(new iconModel(R.drawable.ic_speed,"Speed"));
                            iconModelArrayList.add(new iconModel(R.drawable.ic_subtitles,"Subtitles"));
                        }
                        iconModelArrayList.set(position,new iconModel(R.drawable.ic_left,""));
                        playbackItemsAdapter.notifyDataSetChanged();
                        expand=true;
                    }
                }
                if (position== 1)
                {
                    if (!dark){
                        night_mde.setVisibility(View.VISIBLE);
                        iconModelArrayList.set(position,new iconModel(R.drawable.ic_dark,"Night"));
                        playbackItemsAdapter.notifyDataSetChanged();
                        dark=true;
                    }else {
                        night_mde.setVisibility(View.GONE);
                        iconModelArrayList.set(position,new iconModel(R.drawable.ic_dark,"Day"));
                        playbackItemsAdapter.notifyDataSetChanged();
                        dark=false;
                    }
                }
                if (position== 2)
                {
                    if (!mute){
                        player.setVolume(0);
                        iconModelArrayList.set(position,new iconModel(R.drawable.ic_volume_off,"Unmute"));
                        playbackItemsAdapter.notifyDataSetChanged();
                        mute=true;
                    }else {
                        player.setVolume(100);
                        iconModelArrayList.set(position, new iconModel(R.drawable.ic_volume, "Mute"));
                        playbackItemsAdapter.notifyDataSetChanged();
                        mute = false;
                    }
                }
                if (position== 3)
                {
                    if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        playbackItemsAdapter.notifyDataSetChanged();
                    }else if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            playbackItemsAdapter.notifyDataSetChanged();
                        }

                    }
                if (position== 4)
                {
                    s_bar.setVisibility(View.VISIBLE);
                }
                if (position== 5)
                {
                    Toast.makeText(VideoPlayerActivity.this, "fifth", Toast.LENGTH_SHORT).show();
                }
                if (position== 6)
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(VideoPlayerActivity.this);
                    alertDialog.setTitle("Select Playback speed").setPositiveButton("Ok",null);
                   // alertDialog.setPositiveButton()
                    String[] items = {"0.5x","0.75x","1x Normal Speed","1.25x","1.5x","2x"};

                    alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0:
                                    speed = 0.5f;
                                    parameters= new PlaybackParameters(speed);
                                    player.setPlaybackParameters(parameters);
                                    checkedItem=0;
                                    break;
                                case 1:
                                    speed = 0.75f;
                                    parameters= new PlaybackParameters(speed);
                                    player.setPlaybackParameters(parameters);
                                    checkedItem=1;
                                    break;
                                case 2:
                                    speed = 1.0f;
                                    parameters= new PlaybackParameters(speed);
                                    player.setPlaybackParameters(parameters);
                                    checkedItem=2;
                                    break;
                                case 3:
                                    speed = 1.25f;
                                    parameters= new PlaybackParameters(speed);
                                    player.setPlaybackParameters(parameters);
                                    checkedItem=3;
                                    break;
                                case 4:
                                    speed = 1.5f;
                                    parameters= new PlaybackParameters(speed);
                                    player.setPlaybackParameters(parameters);
                                    checkedItem=4;
                                    break;
                                case 5:
                                    speed = 2.0f;
                                    parameters= new PlaybackParameters(speed);
                                    player.setPlaybackParameters(parameters);
                                    checkedItem=5;
                                    break;
                                default:
                                    parameters= new PlaybackParameters(speed);
                                    player.setPlaybackParameters(parameters);
                            }
                        }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.show();
                }
                if (position== 7)
                {
                    Toast.makeText(VideoPlayerActivity.this, "Sixth", Toast.LENGTH_SHORT).show();
                }
            }
        });

        try {
            playVideo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void playVideo() throws Exception {
        String path= mVideoFiles.get(position).getPath();
        Uri uri = Uri.parse(path);
        player = new SimpleExoPlayer.Builder(this).build();
        DefaultDataSourceFactory dataSourceFactory= new DefaultDataSourceFactory(
                this, Util.getUserAgent(this,"app"));
        concatenatingMediaSource= new ConcatenatingMediaSource();

        for (int i=0;i<mVideoFiles.size();i++){
            new File(String.valueOf(mVideoFiles.get(i)));
            MediaSource mediaSource= new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(String.valueOf(uri)));
            concatenatingMediaSource.addMediaSource(mediaSource);
        }

        playerView.setPlayer(player);
        playerView.setKeepScreenOn(true);
        player.setPlaybackParameters(parameters);
        player.prepare(concatenatingMediaSource);
        player.seekTo(position, C.TIME_UNSET);
        playError();
        try {
            autoRotate();
        }catch (Exception e){
            Toast.makeText(context, "rotate err", Toast.LENGTH_SHORT).show();
        }

    }

    private void autoRotate() {
         morientationEventListener= new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
                    @Override
                    public void onOrientationChanged(int orientation) {
                        switch (orientation){
                            case 0:
                              if (portrait)
                                  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                break;
                            case 90:
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                                break;
//                            case 180:
//                               // Toast.makeText(VideoPlayerActivity.this, "180", Toast.LENGTH_SHORT).show();
//                                break;
                            case 270:
                                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                break;
//                            case 360:
//                               // Toast.makeText(VideoPlayerActivity.this, "360", Toast.LENGTH_SHORT).show();
//                                break;
                        }
                    }
                };
        morientationEventListener.enable();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void screen_orientation(){
        try {

            MediaMetadataRetriever retriever= new MediaMetadataRetriever();
            Bitmap bitmap;
            String path = mVideoFiles.get(position).getPath();
            Uri uri=Uri.parse(path);
            retriever.setDataSource(this,uri);
            bitmap= retriever.getFrameAtTime();

            int vidWidth = bitmap.getWidth();
            int vidHeight = bitmap.getHeight();
            if (vidHeight<vidWidth){
                portrait=false;
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                portrait= true;
            }
        }catch (Exception e){
            Toast.makeText(this, "Exception  "+e, Toast.LENGTH_SHORT).show();
        }
    }

    private void playError() {
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Toast.makeText(VideoPlayerActivity.this,"Video playing Error!!",Toast.LENGTH_SHORT).show();
            }
        });
        player.setPlayWhenReady(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        morientationEventListener.disable();
        changeBrightnessDefaut();
        if(player.isPlaying()){
            player.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        changeBrightnessDefaut();
        morientationEventListener.disable();
        player.setPlayWhenReady(false);
        player.getPlaybackState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        morientationEventListener.enable();
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        morientationEventListener.enable();
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }

    private void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // this.getWindow().setAttributes(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,);
    }

    @Override
    protected void onStop() {
        super.onStop();
        changeBrightnessDefaut();
        morientationEventListener.disable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        changeBrightnessDefaut();
        morientationEventListener.disable();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exo_next:
                try {
                    player.stop();
                    position++;
                    playVideo();
                }catch (Exception e){
                    Toast.makeText(this,"No Next Video",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                case R.id.exo_previous:
                    try {
                        player.stop();
                        position--;
                        playVideo();
                    }catch (Exception e){
                        Toast.makeText(this,"No Previous Video",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                break;

            case R.id.video_back:
                if (player!=null){
                    player.release();
                }
                finish();
                break;

            case R.id.unlock:
                controlsMode = controlsMode.LOCK;
                root.setVisibility(View.INVISIBLE);
                lock.setVisibility(View.VISIBLE);
                islock=true;
               // Toast.makeText(this, "locked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.exo_lock:
                controlsMode = controlsMode.FULLSCREEN;
                root.setVisibility(View.VISIBLE);
                lock.setVisibility(View.INVISIBLE);
                islock=false;
               // Toast.makeText(this, "UNlocked", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decrorView.setSystemUiVisibility(setInvisible());
        }
    }
    public int setInvisible(){
                         return (
                                 View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                |View.SYSTEM_UI_FLAG_IMMERSIVE
                                |View.SYSTEM_UI_FLAG_FULLSCREEN
                                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                |View.SYSTEM_UI_FLAG_FULLSCREEN
                                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    View.OnClickListener firstListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            player.setVideoScalingMode(C.VIDEO_SCALING_MODE_DEFAULT);
            scaling.setImageResource(R.drawable.exo_controls_fullscreen_enter);
            Toast.makeText(VideoPlayerActivity.this, "Full Screen", Toast.LENGTH_SHORT).show();

            scaling.setOnClickListener(secondListener);
        }
    };
    View.OnClickListener secondListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
            player.setVideoScalingMode(C.VIDEO_SCALING_MODE_DEFAULT);
            scaling.setImageResource(R.drawable.exo_ic_fullscreen_exit);
            Toast.makeText(VideoPlayerActivity.this, "Stretch", Toast.LENGTH_SHORT).show();
            scaling.setOnClickListener(thirdListener);
        }
    };
    View.OnClickListener thirdListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
            player.setVideoScalingMode(C.VIDEO_SCALING_MODE_DEFAULT);
            scaling.setImageResource(R.drawable.ic_fit);
            Toast.makeText(VideoPlayerActivity.this, "fit", Toast.LENGTH_SHORT).show();
            scaling.setOnClickListener(firstListener);
        }
    };

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{

        public static final int SWIPE_THRESD=100;
        public static final int SWIPE_VECITY_THRESD=100;

        @Override
        public boolean onDown(MotionEvent e) {
            audioManager= (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            int mediaVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int setVol= (int) Math.ceil((((double) mediaVol/(double) maxVol)*100));
            vText.setText(String.valueOf(setVol));
            seekBarVol.setProgress(setVol);

            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            s_bar.setVisibility(View.GONE);
            s_bar_brightness.setVisibility(View.GONE);
                try {
                    if (playerView.isControllerVisible()){
                          playerView.hideController();
                        //playerView.setControllerShowTimeoutMs(4000);
                    }
                    else if (s_bar.getVisibility()==View.GONE){
                        playerView.showController();
                        playerView.setControllerShowTimeoutMs(4000);
                    }

                }catch (Exception exception){
                    Toast.makeText(context, ""+exception, Toast.LENGTH_SHORT).show();
                }

            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            audioManager= (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            int mediaVol= audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            int maxVol= audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int setVol= (int) Math.ceil((((double) mediaVol/(double) maxVol)*100));
            vText.setText(String.valueOf(setVol));
            seekBarVol.setProgress(setVol);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            try {
                float diffY= e2.getY()- e1.getY();
                float diffX= e2.getX()- e1.getX();
                if ((SWIPE_THRESD< Math.abs(diffY) && Math.abs(diffX)<250)
                        &&(Math.abs(diffX)<Math.abs(diffY))){

                    if ((getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE
                    && e1.getX()>1100)||(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT
                    && e1.getX()>550)) {
                        if (!islock){
                            s_bar.setVisibility(View.VISIBLE);
                            s_bar_brightness.setVisibility(View.GONE);
                            updateVolume(diffY);
                        }
                    }
                    else if(!islock){
                        updateBrightness(diffY);
                        s_bar.setVisibility(View.GONE);
                        s_bar_brightness.setVisibility(View.VISIBLE);

                    }
                }
                else if ((SWIPE_THRESD< Math.abs(diffX) && Math.abs(diffY)<250)
                        &&(Math.abs(diffY)<Math.abs(diffX))){
                    if ((diffX>0)&&(!islock)) {

                            s_bar.setVisibility(View.GONE);
                            s_bar_brightness.setVisibility(View.GONE);
                            player.seekTo(player.getCurrentPosition()+2500);
                    }
                    else if((diffX<0)&&(!islock)){
                        s_bar.setVisibility(View.GONE);
                        s_bar_brightness.setVisibility(View.GONE);
                        player.seekTo(player.getCurrentPosition()-2500);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            int x= (int) e.getX();
            try {
                    if ((getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE
                            && x>1100)||(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT
                            && x>550)) {
                        if (!islock){
                            player.seekTo(player.getCurrentPosition()+5000);
                            s_bar_brightness.setVisibility(View.GONE);
                            s_bar.setVisibility(View.GONE);
                        }
                    }
                    else if(!islock){
                        player.seekTo(player.getCurrentPosition()-5000);
                        s_bar.setVisibility(View.GONE);
                        s_bar.setVisibility(View.GONE);
                    }

            }catch (Exception exception){
                exception.printStackTrace();
            }
            return true;
        }
    }

    private void updateBrightness(float diffY) {

        bProgress= seekBarBright.getProgress() - (int) diffY/110;
        if (bProgress>255){
            bProgress=255;
        }
        else if (bProgress<=0){
            bProgress=0;
            bImage.setImageResource(R.drawable.ic_brightness_low);
        }else
            bImage.setImageResource(R.drawable.ic_brightness);

        s_bar_brightness.setVisibility(View.VISIBLE);
        seekBarBright.setProgress(Math.abs(bProgress));
        int perc= (int)(((double)bProgress/255)*100);
        bText.setText(String.valueOf(Math.abs(perc)));
    }

    public void updateVolume(float diffY){

         vProgress= seekBarVol.getProgress() - (int) diffY/180;
        if (vProgress>100)
            vProgress=100;
        else if (vProgress<=0){
            vProgress=0;
            vImage.setImageResource(R.drawable.ic_volume_off);
        }else
        vImage.setImageResource(R.drawable.ic_volume);

        s_bar.setVisibility(View.VISIBLE);
        seekBarVol.setProgress(Math.abs(vProgress));
        vText.setText(String.valueOf(Math.abs(vProgress)));

    }
    public void changeBrightnessDefaut(){
        try {
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS,perBrightness);
        }catch (Exception exception){}
    }
}