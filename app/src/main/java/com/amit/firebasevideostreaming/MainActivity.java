package com.amit.firebasevideostreaming;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.videoView)
    VideoView videoView;
    MediaController mediaController;
    ProgressDialog progressDialog;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("fetching Video...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Buffering...");
        progressDialog.show();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-videostreaming-1f430.appspot.com/o/Ae%20Watan%20(Raazi)%20HQ%20MP4.mp4?alt=media&token=27113882-e889-4ab7-9007-986fe6b65263");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
                progressDialog.cancel();
            }
        });


    }
}
