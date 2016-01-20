package me.tikitoo.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import me.tikitoo.eventbusdemo.model.MessageEvent;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EVENT_DEFAULT = "event_default: ";
    public static final String EVENT_MAIN_THREAD = "event_main_thread: ";
    public static final String EVENT_BACKGROUND = "event_background: ";
    public static final String EVENT_ASYNC = "event_async: ";
    private TextView mTextView;
    private Button mButton;
    private TextView mTvId, mTvName, mTvAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.text);

        mTvId = (TextView) findViewById(R.id.tv_id);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvAge = (TextView) findViewById(R.id.tv_age);
        findViewById(R.id.event_btn).setOnClickListener(this);
        findViewById(R.id.event_main_btn).setOnClickListener(this);
        findViewById(R.id.event_bg_btn).setOnClickListener(this);
        findViewById(R.id.event_async_btn).setOnClickListener(this);
    }


    /*public void onEvent(String str) {
        mTextView.setText(EVENT_DEFAULT + str);
    }

    public void onEventMainThread(String str) {
        mTextView.setText(EVENT_MAIN_THREAD + str);
    }*/

    /*public void onEventBackgroundThread(String str) {
        mTextView.setText(EVENT_BACKGROUND + str);
    }*/

    public void onEventAsync(String str) {
        mTextView.setText(EVENT_ASYNC + str);
    }

    public void onEvent(MessageEvent msgEvent) {
        mTvId.setText(msgEvent.getId());
        mTvName.setText(msgEvent.getName());
        mTvAge.setText(msgEvent.getAge());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.event_btn:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case R.id.event_main_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post("event post thread to main");
                    }
                }).start();

                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post("event post main thread to main");
                    }
                });*/
                break;
            case R.id.event_bg_btn:
                EventBus.getDefault().post("event post on bg");
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post("event post on bg");
                    }
                }).start();*/

                break;
            case R.id.event_async_btn:
                EventBus.getDefault().post("event post on async");
                break;
        }
    }
}
