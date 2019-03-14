package com.thunder.ktv.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        doHttpRequest();
    }

    private void doHttpRequest() {
        HttpManager.getInstance()
                .with(this)
                .setObservable(RetrofitManager.getService().getExpress("yuantong", "200382770316"))
                .setDataListener(new HttpDataListener<List<Express>>() {
                    @Override
                    public void onNext(List<Express> list) {
                        //这里对返回数据进行处理
                        String result = "";
                        for (int i = 0; i < list.size(); i++) {
                            result = result + list.get(i).toString();
                        }
                        Log.d(TAG, "onNext: " + result);
                    }
                })
                .call();
    }
}
