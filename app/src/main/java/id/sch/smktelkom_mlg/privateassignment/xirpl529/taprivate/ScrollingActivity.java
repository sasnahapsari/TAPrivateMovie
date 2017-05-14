package id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ScrollingActivity extends AppCompatActivity {
    public String url = "http://image.tmdb.org/t/p/w500";
    public String title, desc, pic, gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        title = intent.getStringExtra("movie_title");
        pic = intent.getStringExtra("poster_path");
        desc = intent.getStringExtra("description");
        setTitle(title);
        gambar = url + pic;
        ImageView detail = (ImageView) findViewById(R.id.imageViewDetail);
        TextView deskripsi = (TextView) findViewById(R.id.DetailDescription);
        Glide.with(this).load(gambar)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_error)
                .into(detail);
        deskripsi.setText(desc);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
