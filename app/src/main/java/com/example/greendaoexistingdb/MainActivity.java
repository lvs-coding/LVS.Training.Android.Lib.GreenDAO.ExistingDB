package com.example.greendaoexistingdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.greendaoexistingdb.data.local.database.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = ((App)getApplication()).getDaoSession().getUserDao().load(1L);

        if(user != null) {
            Log.d("TAG", user.getName());
        }
   }
}
