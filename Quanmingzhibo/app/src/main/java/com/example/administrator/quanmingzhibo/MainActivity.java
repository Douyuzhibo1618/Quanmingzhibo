package com.example.administrator.quanmingzhibo;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.quanmingzhibo.fragment.LanmuFragment;
import com.example.administrator.quanmingzhibo.fragment.MainFragment;
import com.example.administrator.quanmingzhibo.fragment.WodeFragment;
import com.example.administrator.quanmingzhibo.fragment.ZhiBoFragment;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rg= (RadioGroup) findViewById(R.id.rg);
        manager=getSupportFragmentManager();
        ((RadioButton)rg.getChildAt(0)).setChecked(true);
        manager.beginTransaction().replace(R.id.ll,new MainFragment()).commit();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_main:
                        manager.beginTransaction().replace(R.id.ll,new MainFragment()).commit();
                         break;
                    case R.id.rb_lanmu:
                        manager.beginTransaction().replace(R.id.ll,new LanmuFragment()).commit();
                        break;
                    case R.id.rb_zhiBo:
                        manager.beginTransaction().replace(R.id.ll,new ZhiBoFragment()).commit();
                        break;
                    case R.id.rb_wode:
                        manager.beginTransaction().replace(R.id.ll,new WodeFragment()).commit();
                        break;
                }
            }
        });

    }
}
