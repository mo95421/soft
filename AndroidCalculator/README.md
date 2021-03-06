# 安卓计算器

> 一个安卓学习初期练手项目

## 功能简介：

* 基本的数字输入、显示，以及小数点个数控制以及小数点后位数控制
* 基本的加减乘除功能
* 连续计算功能（未实现乘法除法优先计算的功能）

## 功能实现细节：

连续计算时，采用了点击运算符或等于号是就根据前一个运算符，对前两个数字按该运算符进行运算。

比如：
`1+2*3=...`
这个式子会在点击加号时，保留数字1；在点击乘号时，计算`1+2`；再点击等于号时，计算`(1+2)*3`。

因此，本项目并没有考虑乘法和除法与加减法并列时的优先级。而是顺序进行计算。

## 后期目标

* 对界面进行优化
* 对代码进行整理，并考虑是否有执行效率更高的实现方法
* 实现连续计算时的乘除法优先功能