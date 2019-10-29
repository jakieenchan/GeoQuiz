package com.example.jakieenchan.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, false),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, true),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
    };

    private int mCurrentIndex = 0 ;

    //更新题目
    private void updateQuestion(){
        int quesetion = mQuestionBank[mCurrentIndex].getmQuestion();
        mQuestionTextView.setText(quesetion);
    }

    //接收用户点击了哪个按钮，并判断回答是否正确，反馈答题结果
    private void checkAnswer(boolean userPressedTrue){
        boolean answerTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();

        int messageResId = 0 ;
        if(userPressedTrue == answerTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //引用TextView，将文本内容设置为当前数组索引执行的问题
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view) ;

        //引用指定按钮
        mTrueButton = (Button)findViewById(R.id.true_button);//接收组件的资源ID作为参数，返回一个视图对象.必须将返回的View转为Button
        //设置监听器
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建提示消息（Toast），
                //参数： Activity实例 + 字符串资源ID + 显示持续时间
               checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        //引用prev按钮
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        //实现跳转上一题
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex<=1){
                    mCurrentIndex = (mQuestionBank.length-1) % mQuestionBank.length;
                }else{
                    mCurrentIndex = (mCurrentIndex - 1 ) % mQuestionBank.length;
                }
               updateQuestion();
            }
        });

        //引用next按钮
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        //点击跳转下一题
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1 ) % mQuestionBank.length;
               updateQuestion();
            }
        });

        //为文本设置监听器,实现点击文本就跳转下一题的功能
        mQuestionTextView = findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1 ) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }
}
