package com.github.ganquan.layoutinjectutils;


import com.github.ganquan.library.VLFrame.helper.BindLayout;

import android.app.Activity;
import android.os.Bundle;

@BindLayout(R.layout.activity_main)
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
