package com.calculator.fnine.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //数字键
    private Button num_0;
    private Button num_1;
    private Button num_2;
    private Button num_3;
    private Button num_4;
    private Button num_5;
    private Button num_6;
    private Button num_7;
    private Button num_8;
    private Button num_9;
    //内存操作键
    private Button memory_plus;
    private Button memory_clean;
    private Button memory_show;
    //四则运算键
    private Button plus;
    private Button minus;
    private Button multiply;
    private Button divide;
    //其他操作键
    private Button clean;
    private Button delete;
    private Button point;
    private Button equals;
    private Button opposite;
    //显示文本框
    TextView tv;
    //显示文本
    String str;
    //记录运算数
    Float firstNum;
    Float secondNum;
    Float resultNum;
    Float resultText;
    String oldoperator;//记录上一个运算符，用作等号处使用
    //标识
    int opeflag; // 0 没使用过任何运算符，1 使用过四则运算符号， 2 使用过等号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    private void initViews(){//初始化控件
        //初始化数字按键
        num_0 = (Button) findViewById(R.id.num_0);
        num_1 = (Button) findViewById(R.id.num_1);
        num_2 = (Button) findViewById(R.id.num_2);
        num_3 = (Button) findViewById(R.id.num_3);
        num_4 = (Button) findViewById(R.id.num_4);
        num_5 = (Button) findViewById(R.id.num_5);
        num_6 = (Button) findViewById(R.id.num_6);
        num_7 = (Button) findViewById(R.id.num_7);
        num_8 = (Button) findViewById(R.id.num_8);
        num_9 = (Button) findViewById(R.id.num_9);
        //初始化四则运算按键
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        //初始化其他操作按钮
        point = (Button) findViewById(R.id.point);
        equals = (Button) findViewById(R.id.equals);
        clean = (Button) findViewById(R.id.clean);
        opposite = (Button) findViewById(R.id.opposite);
        delete = (Button) findViewById(R.id.delete);
        //初始化内存操作按钮
        memory_clean = (Button) findViewById(R.id.m_clean);
        memory_plus = (Button) findViewById(R.id.m_plus);
        memory_show = (Button) findViewById(R.id.m_memory);
        //初始化显示文本框
        tv = (TextView) findViewById(R.id.result_tv);
        //初始化显示文本
        str = tv.getText().toString();
        //初始化数据
        firstNum = 0.0f;
        secondNum = 0.0f;
        resultNum = 0.0f;
        resultText = 0.0f;
        opeflag = 0;
    }

    private void initEvents(){//初始化事件
        //为数字按键初始化事件
        num_0.setOnClickListener(this);
        num_1.setOnClickListener(this);
        num_2.setOnClickListener(this);
        num_3.setOnClickListener(this);
        num_4.setOnClickListener(this);
        num_5.setOnClickListener(this);
        num_6.setOnClickListener(this);
        num_7.setOnClickListener(this);
        num_8.setOnClickListener(this);
        num_9.setOnClickListener(this);
        //为四则运算按键初始化事件
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        //为其他操作按键初始化事件
        point.setOnClickListener(this);
        clean.setOnClickListener(this);
        opposite.setOnClickListener(this);
        equals.setOnClickListener(this);
        //为内存处理按键初始化事件
        memory_clean.setOnClickListener(this);
        memory_show.setOnClickListener(this);
        memory_plus.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(str.equals("error")){
            str = "";
        }
        switch (v.getId()){
            //如果是数字则显示在屏幕上
            case R.id.num_0:
                inputNumber(0); break;
            case R.id.num_1:
                inputNumber(1); break;
            case R.id.num_2:
                inputNumber(2); break;
            case R.id.num_3:
                inputNumber(3); break;
            case R.id.num_4:
                inputNumber(4); break;
            case R.id.num_5:
                inputNumber(5); break;
            case R.id.num_6:
                inputNumber(6); break;
            case R.id.num_7:
                inputNumber(7); break;
            case R.id.num_8:
                inputNumber(8); break;
            case R.id.num_9:
                inputNumber(9); break;
            //删除键
            case R.id.delete:
                if(str.length()>0){
                    str = str.substring(0, str.length()-1); break;
                }
                break;
            //清空键
            case R.id.clean:
                str = "";
                break;
            //小数点
            case R.id.point:
                if(!str.contains(".")){
                    str += ".";
                }
                break;
            //相反数
            case R.id.opposite:
                if(str.length()>0){
                    str = String.valueOf(-Float.valueOf(str));
                    break;
                }
            case R.id.plus:
                //oldoperator = "+";
                //每次点击四则运算按键时就更新了oldoperator变量的值，这对=按键没有什么影响，因为按下=时不更新这个值，但是如果连续混合算时，是无法正确得到的oldoperator的值的
                calculate("+");
                break;
            case R.id.minus:
                calculate("-");
                break;
            case R.id.multiply:
                calculate("*");
                break;
            case R.id.divide:
                calculate("/");
                break;
            case R.id.equals:
                calculate("=");
                break;
            case R.id.m_memory:
                str = String.valueOf(resultText);
                opeflag = 2;
                break;
            case R.id.m_clean:
                resultText = 0.0f;
                str = "";
                break;
            case R.id.m_plus:
                if(str.length()>0){
                    resultText += Float.valueOf(str);
                }else{
                    resultText += 0.0f;
                }
                str = String.valueOf(resultText);
                opeflag = 2;
                break;
            default:
                str = "error";
        }
        tv.setText(str);
    }

    private void inputNumber(int num){
        if(opeflag == 2){
            str = "";
            str += num;
            opeflag = 0;
        }
        else{
            if(str.contains(".")){
                int index = str.indexOf(".");
                if(str.length() >= index+3){
                    //已经到了小数点后两位，不可以再输入
                }else {
                    str += num;
                }
            }else{
                str += num;
            }
        }
    }

    private void calculate(String operator){
        if(operator.equals("+")){
            if(opeflag == 0 || opeflag ==2){
                oldoperator = "+";
                if(str.length() > 0){
                    firstNum = Float.valueOf(str);
                    str = "";
                    opeflag = 1;
                } else{
                    //在长度为0的情况下直接按乘号，相当于0+几
                    firstNum =0.0f;
                    str = "";
                    opeflag = 1;
                }
            }else{//用作连加
                if(oldoperator.equals("+")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum + secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("-")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum - secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("*")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum * secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("/")){
                    if (str.length() > 0) {
                        if(firstNum == 0.0f){
                            str = "error";
                        }else{
                            secondNum = Float.valueOf(str);
                            resultNum = firstNum / secondNum;
                            firstNum = resultNum;
                            secondNum = 0.0f;
                            str = "";
                            opeflag = 1;
                        }
                    }
                }
                oldoperator = "+";
            }
        }
        if(operator.equals("-")){
            if(opeflag == 0 || opeflag ==2){
                oldoperator = "-";
                if(str.length() > 0){
                    firstNum = Float.valueOf(str);
                    str = "";
                    opeflag = 1;
                }
                else{
                    //在长度为0的情况下直接按乘号，相当于0-几
                    firstNum =0.0f;
                    str = "";
                    opeflag = 1;
                }
            }else{//用作连减
                if(oldoperator.equals("+")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum + secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("-")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum - secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("*")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum * secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("/")){
                    if (str.length() > 0) {
                        if(firstNum == 0.0f){
                            str = "error";
                        }else{
                            secondNum = Float.valueOf(str);
                            resultNum = firstNum / secondNum;
                            firstNum = resultNum;
                            secondNum = 0.0f;
                            str = "";
                            opeflag = 1;
                        }
                    }
                }
                oldoperator = "-";
            }
        }
        if(operator.equals("*")){
            if(opeflag == 0 || opeflag ==2){
                oldoperator = "*";
                if(str.length() > 0){
                    firstNum = Float.valueOf(str);
                    str = "";
                    opeflag = 1;
                }else{
                    //在长度为0的情况下直接按乘号，相当于0乘几
                    firstNum =0.0f;
                    str = "";
                    opeflag = 1;
                }
            }else{//用作连乘
                if(oldoperator.equals("+")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum + secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("-")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum - secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("*")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum * secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("/")){
                    if (str.length() > 0) {
                        if(firstNum == 0.0f){
                            str = "error";
                        }else{
                            secondNum = Float.valueOf(str);
                            resultNum = firstNum / secondNum;
                            firstNum = resultNum;
                            secondNum = 0.0f;
                            str = "";
                            opeflag = 1;
                        }
                    }
                }
                oldoperator = "*";
            }
        }
        if(operator.equals("/")){
            if(opeflag == 0 || opeflag ==2){
                oldoperator = "/";
                if(str.length() > 0){
                    firstNum = Float.valueOf(str);
                    if(firstNum == 0.0f){
                        str = "error";
                    }
                    else{
                        str = "";
                        opeflag = 1;
                    }
                }else{
                    //在长度为0的情况下直接按除号，默认没什么作用
                }
            }else{//用作连除
                if(oldoperator.equals("+")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum + secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("-")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum - secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("*")){
                    if (str.length() > 0) {
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum * secondNum;
                        firstNum = resultNum;
                        secondNum = 0.0f;
                        str = "";
                        opeflag = 1;
                    }
                }else if(oldoperator.equals("/")){
                    if (str.length() > 0) {
                        if(firstNum == 0.0f){
                            str = "error";
                        }else{
                            secondNum = Float.valueOf(str);
                            resultNum = firstNum / secondNum;
                            firstNum = resultNum;
                            secondNum = 0.0f;
                            str = "";
                            opeflag = 1;
                        }
                    }
                }
                oldoperator = "/";
            }
        }
        if(operator.equals("=")){
            //点击等于号之后也要先判断当前运算符，用作点击等号时的运算
            if(oldoperator.equals("+")){
                if(str.length() > 0){
                    secondNum = Float.valueOf(str);
                    resultNum = firstNum + secondNum;
                    resultText = 0.0f;
                    resultText = resultNum;
                    str = String.valueOf(resultText);
                    opeflag = 2;
                }else{
                    str = String.valueOf(resultText);
                    firstNum = resultText;
                    opeflag = 2;
                }
            }else if(oldoperator.equals("-")){
                if(str.length() > 0){
                    secondNum = Float.valueOf(str);
                    resultNum = firstNum - secondNum;
                    resultText = 0.0f;
                    resultText = resultNum;
                    str = String.valueOf(resultText);
                    opeflag = 2;
                }else{
                    str = String.valueOf(resultText);
                    firstNum = resultText;
                    opeflag = 2;
                }
            }else if(oldoperator.equals("*")) {
                if (str.length() > 0) {
                    secondNum = Float.valueOf(str);
                    resultNum = firstNum * secondNum;
                    resultText = 0.0f;
                    resultText = resultNum;
                    str = String.valueOf(resultText);
                    opeflag = 2;
                }else{
                    str = String.valueOf(resultText);
                    firstNum = resultText;
                    opeflag = 2;
                }
            }else if(oldoperator.equals("/")) {
                if (str.length() > 0) {
                    if(firstNum == 0.0f){//判断如果连续计算过程中出现了0，则不可作为除数，显示error并重新开始新一轮计算
                        str = "error";
                        opeflag = 2;
                    }else{
                        secondNum = Float.valueOf(str);
                        resultNum = firstNum / secondNum;
                        resultText = 0.0f;
                        resultText = resultNum;
                        str = String.valueOf(resultText);
                        opeflag = 2;
                    }
                }else{
                    str = String.valueOf(resultText);
                    firstNum = resultText;
                    opeflag = 2;
                }
            }
            oldoperator = "=";
        }
    }
}
