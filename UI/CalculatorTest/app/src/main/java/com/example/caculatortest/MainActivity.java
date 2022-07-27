package com.example.caculatortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 为了获取
    private TextView calculator_edit_text;

    // 第一个操作数
    private String firstNum = "";
    // 操作符
    private String operator = "";
    // 第二个操作数
    private String secondNum = "";
    // 当前的计算结果
    private String result = "";
    // 显示的文本内容
    private String showText = "";


    // 图片相关
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 为结果设置监听事件
        calculator_edit_text = findViewById(R.id.caculator_edit_text);


        // 为按钮设置监听事件
        findViewById(R.id.remove).setOnClickListener(this);
        findViewById(R.id.divison).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.subtraction).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.wan).setOnClickListener(this);
        findViewById(R.id.derivative).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.point).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);

        // 图片
//        imageView = (ImageView) findViewById(R.id.one);

    }

    @Override
    public void onClick(View v) {
        String inputText;
        // 获取按钮
        inputText = ((TextView) v).getText().toString();

        switch (v.getId()){
            // 点击了回退按钮
            case R.id.remove:

                break;

            // 点击了清除按钮
            case R.id.delete:
                clear();
                break;

            // 点击了等号按钮
            case R.id.equal:
                double calculator_result = calculatorFour();
                refreshOperate(String.valueOf(calculator_result));
                refreshText(showText + "=" + result);
                break;

            // 求倒数按钮
            case R.id.derivative:
                double derivative_result = 1.0 / Double.parseDouble(firstNum);
                refreshOperate(String.valueOf(derivative_result));
                refreshText(showText + "/=" + result);
                break;

            // 四则运算按钮
            case R.id.add:
            case R.id.subtraction:
            case R.id.multiply:
            case R.id.divison:

                operator = inputText;
                refreshText(showText + operator);

                break;

            //  wan
            case R.id.wan:
                Intent intent = new Intent(MainActivity.this,PictureActivity.class);
                startActivity(intent);
                break;


            // 数字或者点按钮
            default:

                // 已经有东西显示在屏幕上了，但是却忘了清除，这时候就需要自动去清除内容
                if (result.length() > 0 && operator.equals("")) {
                    clear();
                }

                // 无运算符输入之前
                if (operator.equals("")) {
                    firstNum = firstNum + inputText;
                } else {
                    // 有运算符，则把输入的内容放在第二个数中
                    secondNum = secondNum + inputText;
                }

                // 当前面的数字是零，并且后面的没有输入小数点时，则不在0的后面添加内容
                if (showText.equals("0") && !inputText.equals(".")) {
                    refreshText(inputText);
                } else {
                    refreshText(showText + inputText);
                }

                break;
        }
    }

    private double calculatorFour() {
        // 计算传入的数值，并且返回计算结果
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "x":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            case "÷":
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
            default:
                break;
        }
        return 0;
    }

    // 清除内容
    private void clear() {
        refreshOperate("");
        refreshText("");
    }

    // 刷新运算结果
    private void refreshOperate(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    // 刷新文字显示
    private void refreshText (String text) {
        showText = text;
        calculator_edit_text.setText(showText);
    }
}