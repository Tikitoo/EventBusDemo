package me.tikitoo.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.greenrobot.event.EventBus;

public class FirstStickyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_stricky);
        EventBus.getDefault().registerSticky(this);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.text);
        findViewById(R.id.event_btn).setOnClickListener(this);
        findViewById(R.id.event2_btn).setOnClickListener(this);
    }

    public void onEvent(String msg) {
        mTextView.setText(msg);
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
                EventBus.getDefault().postSticky(new String("Hello"));
                String str = EventBus.getDefault().getStickyEvent(String.class);
                EventBus.getDefault().removeStickyEvent(new String());
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                break;
            case R.id.event2_btn:
                EventBus.getDefault().postSticky(new String("Hello 2"));
                break;
        }
    }
}
