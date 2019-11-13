package com.example.intenttest;
//隱含的意圖,沒有明確指定activity名稱,只提大概意圖,用於呼叫自己app以外的系統功能
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_url,btn_camera,btn_album,btn_call,btn_map;
    EditText edt_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_url = findViewById(R.id.btnUrl);
        btn_camera = findViewById(R.id.btnCamera);
        btn_album = findViewById(R.id.btnImg);
        btn_map = findViewById(R.id.btnMap);
        btn_call = findViewById(R.id.btnCall);

        edt_phone = findViewById(R.id.edtPhone);
//29-33-->38-66
        btn_url.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        btn_album.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_map.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Uri uri;
        Intent intent;
        switch (v.getId()){
            case  R.id.btnUrl:
                uri = Uri.parse("https://youtube.com");
                intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case  R.id.btnCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                   //老師複習android四大組件,媒體區塊照片影音都在provider
                startActivity(intent);
                break;
            case  R.id.btnMap:
                uri = Uri.parse("geo:24.948436, 121.229220");
                intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case  R.id.btnCall:
                uri = Uri.parse("tel:0955123456"+edt_phone.getText());
                                //一定要冒號,傳送郵件mailto也要(tel跟mailto同語法)
                intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case  R.id.btnImg:
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                                                  //取得內容
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                          //可以打開的類別
                intent.setType("image/*");
                startActivityForResult(intent,0);
                break;
        }
    }
}
