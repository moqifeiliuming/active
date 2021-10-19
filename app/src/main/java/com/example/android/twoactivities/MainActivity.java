package com.example.android.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * TwoActivities应用程序包含两个活动并在他们之间发送消息（意图）。
 *
 */
public class MainActivity extends AppCompatActivity {
    // 日志标签的类名
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Intent Extra 需要的唯一标签
    public static final String EXTRA_MESSAGE
            = "com.example.android.twoactivities.extra.MESSAGE";
    // Intent回复的唯一标签
    public static final int TEXT_REQUEST = 1;

    // 信息的EditText 视图
    private EditText mMessageEditText;
    // 用来显示回答头部的TextView
    private TextView mReplyHeadTextView;
    // 用来显示回答主体的TextView
    private TextView mReplyTextView;

    /**
     * 初始化Activity.
     *
     * @param savedInstanceState 当前状态数据.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化所有的视图变量
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    /**
     * 处理onClick的“发送”按钮。 获取主EditText的值，
     * 创建一个意图，并使用该意图启动第二个活动。     *      
     * 第二个活动的返回意图是onActivityResult（）。
     *
     * @param view The view (Button) that was clicked.
     */
    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    /**
     * 处理来自SecondActivity的返回Intent中的数据。
     *
     * @param requestCode SecondActivity请求的代码。
     * @param resultCode 从SecondActivity返回的代码。
     * @param data 从SecondActivity发送回的Intent数据。
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 测试正确的Intent回复。
        if (requestCode == TEXT_REQUEST) {
            // 测试以确保Intent回复结果良好。
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                // 使回复头可见。
                mReplyHeadTextView.setVisibility(View.VISIBLE);

                // 设置回复并使其可见。
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
