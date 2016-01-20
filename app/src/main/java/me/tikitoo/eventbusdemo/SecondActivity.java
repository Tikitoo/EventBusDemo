package me.tikitoo.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Random;

import de.greenrobot.event.EventBus;
import me.tikitoo.eventbusdemo.model.MessageEvent;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn).setOnClickListener(this);

    }

    private void postObj(Object str) {
        EventBus.getDefault().post(str);
    }


    private int randInt() {
        return new Random().nextInt(100);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                String id = String.valueOf(randInt());
                postObj(id);
                postObj(new MessageEvent(id, "tikitoo", "18"));
                finish();
                break;
        }
    }
}
