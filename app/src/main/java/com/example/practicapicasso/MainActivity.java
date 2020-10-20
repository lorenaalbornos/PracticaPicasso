package com.example.practicapicasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button addPermissions, save, download;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1 ;
    private ImageView mImagen;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private int currentTime = 0;
    private static final String URL1 = "https://imagenes.20minutos.es/files/image_656_370/files/fp/uploads/imagenes/2019/10/29/estopa-1.r_d.2052-1554.jpeg";
    private static final String URL2 = "https://musicamalaga.com/wp-content/uploads/2019/05/los-moles2.jpg";
    private static final String URL3 = "https://www.orquestasdegalicia.es/img/portada/orquestas/id456portada.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPermissions = (Button) findViewById(R.id.addPermissions);
        addPermissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    } else {

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    }
                }
            }
        });


        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImagen = (ImageView)findViewById(R.id.imagen);
                chronometer();
                mImagen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveImage(URL1);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }
    /*public void saveImage(String image) {
        Picasso.get().load(image).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                try {
                    File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(directory,
                            new Date().toString().concat(String.valueOf(countBanner)).concat(".jpg")));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }*/


    public void chronometer(){
        mTimerTask = new TimerTask() {

            @Override
            public void run() {
                //evita que bloquees la interfaz gráfica
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch(currentTime){
                            case 1:
                                Picasso.get().load(URL1).into(mImagen);
                                mImagen.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Uri url = Uri.parse("https://www.youtube.com/watch?v=JmP89cIGJZM");
                                        Intent mIntent = new Intent(Intent.ACTION_VIEW, url);
                                        startActivity(mIntent);
                                    }
                                });
                                break;
                            case 2:
                                Picasso.get().load(URL2).into(mImagen);
                                mImagen.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Uri url = Uri.parse("https://www.youtube.com/watch?v=9ZViyXTyZwo");
                                        Intent mIntent = new Intent(Intent.ACTION_VIEW, url);
                                        startActivity(mIntent);
                                    }
                                });
                                break;
                            case 3:
                                Picasso.get().load(URL3).into(mImagen);
                                mImagen.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Uri url = Uri.parse("https://www.facebook.com/205046313016747/videos/2098160973782749");
                                        Intent mIntent = new Intent(Intent.ACTION_VIEW, url);
                                        startActivity(mIntent);
                                    }
                                });
                                break;
                        }
                        currentTime++;
                        if(currentTime == 5){
                            currentTime = 0;
                        }
                    }
                });

            }
        };
        mTimer = new Timer();
        mTimer.schedule(mTimerTask,1,5000);//cada 10 segundos cambiará la imagen
    }
}

