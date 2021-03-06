package com.example.theba.java_naja;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Chap1_3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap1_3);

        TextView CopBODY = (TextView) findViewById(R.id.Body1);
        CopBODY.setTextIsSelectable(true);

        TextView CopBODY2 = (TextView) findViewById(R.id.Body2);
        CopBODY2.setTextIsSelectable(true);

        TextView CopHead = (TextView) findViewById(R.id.Header);
        CopHead.setTextIsSelectable(true);

        getSupportActionBar().setTitle("บทที่ 1 ภาษาคอมพิวเตอร์และการพัฒนาโปรแกรม");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        ImageView SavePIC1 = (ImageView) findViewById(R.id.Pic1);
        SavePIC1.setOnClickListener(new Chap1_3Activity.DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
            @Override
            public void onDoubleClick(View v) {

                ActivityCompat.requestPermissions(Chap1_3Activity.this ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

                Bitmap image= BitmapFactory.decodeResource(getResources(),R.drawable.p1_3);

                File path = Environment.getExternalStorageDirectory();

                File dir = new File(path+"/JAVA NaJa/saved/");
                dir.mkdirs();

                File file = new File(dir,"รูปที่ 1.3 Java.jpg");

                OutputStream out = null;

                try {
                    out = new FileOutputStream(file);
                    image.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                }   catch (java.io.IOException e) {
                    e.printStackTrace();
                }
//                        Drawable myDrawable = getResources().getDrawable(R.drawable.picchapter1_1);
//
//                        bitmap = ((BitmapDrawablele)myDrawable).getBitmap();
//
//                        ImagePath = MediaStore.Images.Media.insertImage(
//                                getContentResolver(),
//                                bitmap,
//                                "demo_image",
//                                "demo_image"
//                        );
//
//                        URI = Uri.parse(ImagePath);
//
                Toast.makeText(Chap1_3Activity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    public abstract class DoubleClickListener implements View.OnClickListener {

        private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

        long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
                onDoubleClick(v);
            } else {
                onSingleClick(v);
            }
            lastClickTime = clickTime;
        }

        public abstract void onSingleClick(View v);
        public abstract void onDoubleClick(View v);
    }

}
