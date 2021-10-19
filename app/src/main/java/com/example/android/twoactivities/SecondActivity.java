package com.example.android.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * SecondActivity定义了应用程序中的第二个活动。
 * 它是从带有消息的意图启动，然后发送意图返回第二条消息。
 */
public class SecondActivity extends AppCompatActivity {
    // Intent Extra中回复信息的唯一标签
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivities.extra.REPLY";

    // 回复EditText
    private EditText mReply;

    /**
     * 初始化Activity.
     *
     * @param savedInstanceState 当前状态数据
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 初始化视图变量
        mReply = findViewById(R.id.editText_second);
        // 获取启动此Activity的Intent，并得到Intent中的Extra消息。
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // 将text_message消息放入TextView中
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    /**
     * 处理“回答”按钮的onClick事件。 从第二个EditText获取消息，
     * 创建一个Intent，然后将该消息返回给主Activity。
     *
     * @param view view (Button) 被点击.
     */
    public void returnReply(View view) {
        // 从编辑文本中获取回复消息。
        String reply = mReply.getText().toString();

        // 创建用于答复的新Intent，将答复消息作为Extra消息添加到其中，
        // 设置Intent结果，然后关闭Activity。
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
