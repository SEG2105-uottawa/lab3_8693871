package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView DATA_IN;
    TextView display;
    boolean clr;
    

    //INIT
    private void initView() {

        //LISTENERS
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_pt).setOnClickListener(this);
        findViewById(R.id.btn_neg).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_min).setOnClickListener(this);
        findViewById(R.id.btn_mult).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_clr).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
       	findViewById(R.id. btn_eq).setOnClickListener(this);
    }

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }


    public void onClick(View v) {
        String empty = "";
        String hasSpace = " ";
        String dataInStr = DATA_IN.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            
            case R.id.btn_pt:
               
                if (clr) {
                    clr = false;
                    dataInStr = empty;
                    DATA_IN.setText(empty);
                }


                DATA_IN.setText(dataInStr + ((Button) v).getText());
                break;
            case R.id.btn_neg:
                if (clr) {
                    clr = false;
                    dataInStr = empty;
                    DATA_IN.setText(empty);
                }

                DATA_IN.setText(dataInStr);
                break;
            case R.id.btn_add:
            case R.id.btn_min:
            case R.id.btn_div:
            case R.id.btn_mult:
                if (clr) {
                    clr = false;
                    dataInStr = empty;
                    DATA_IN.setText(empty);
                }
                
                if (dataInStr.contains("+") || (dataInStr.contains("-") && !dataInStr.contains("(")) || dataInStr.contains("x") || dataInStr.contains("÷")) {
                    dataInStr = dataInStr.substring(0, dataInStr.indexOf(empty));
                }
                DATA_IN.setText(dataInStr + empty + ((Button) v).getText() + empty);
                break;
            case R.id.btn_clr:
                if (clr) {
                    clr = false;
                }
                dataInStr = empty;
                DATA_IN.setText(empty);
                break;
            case R.id.btn_del:
                if (clr) {
                    clr = false;
                    dataInStr = empty;
                    DATA_IN.setText(empty);
                } else if (dataInStr != null && !dataInStr.equals(empty)) {
                    DATA_IN.setText(dataInStr.substring(0, dataInStr.length() - 1));
                }
                break;
            case R.id.btn_eq:
                Result();
                break;
        }
    }

    private void Result() {

        String digit1, digit2; 
        boolean fNeg=false;
        String empty = "";
        String hasNeg = "(-";


        if((DATA_IN.getText().toString()==null||DATA_IN.getText().toString().equals(empty)||!DATA_IN.getText().toString().contains(empty))) return ;

        if(clr){
            clr=false;
            return;}
        clr=true;

        //CHECKING FOR NEGATIVE
         fNeg = DATA_IN.getText().toString().contains(hasNeg);

         if(fNeg) {
            digit1 = DATA_IN.getText().toString().substring(DATA_IN.getText().toString().indexOf("(")+1, DATA_IN.getText().toString().indexOf(")"));
        } else {
            digit1 = DATA_IN.getText().toString().substring(0,DATA_IN.getText().toString().indexOf(empty));
        }

        //SPLIT
        String op = DATA_IN.getText().toString().substring(DATA_IN.getText().toString().indexOf(empty)+1,DATA_IN.getText().toString().indexOf(empty)+2);

        if(DATA_IN.getText().toString().substring(4).contains(hasNeg) && fNeg) {
            digit2 = DATA_IN.getText().toString().substring(DATA_IN.getText().toString().indexOf("(")+8, DATA_IN.getText().toString().indexOf(")")+7);
        } else if(DATA_IN.getText().toString().contains(hasNeg) && !fNeg){
            digit2 = DATA_IN.getText().toString().substring(DATA_IN.getText().toString().indexOf("(")+1, DATA_IN.getText().toString().indexOf(")"));
        } else { 
            digit2 = DATA_IN.getText().toString().substring(DATA_IN.getText().toString().indexOf(empty)+3);
        }

        double runningCount = 0;

        if(!digit1.equals(empty)&&!digit2.equals(empty)){
            double d1=Double.parseDouble(digit1);
            double d2=Double.parseDouble(digit2);


            //=============================================================
            //NUMBERICAL OPERATIONS============
            //=============================================================

            //ADD==========
            if(op.equals("+")){
                runningCount=d1+d2;
            }

            //SUBTRACT==========
            if(op.equals("-")){
                runningCount=d1-d2;
            }


            //MUTLIPLY==========
            if(op.equals("X")){
                runningCount=d1*d2;
            }
            //DIVIDE==========
            if(op.equals("÷")){ // division
                if(d2==0) runningCount=0;
                else runningCount=d1/d2;
            }
            if(!digit1.contains(".")&&!digit2.contains(".")&&!op.equals("÷")) {
                int res = (int) runningCount;
                DATA_IN.setText(res+empty);
            }else {
                DATA_IN.setText(runningCount+empty);}
        }

        else if(!digit1.equals(empty)&&digit2.equals(empty)){
            double d1=Double.parseDouble(digit1);
            if(op.equals("+")){
                runningCount=d1;
            }
            if(op.equals("-")){
                runningCount=d1;
            }
            if(op.equals("X")){
                runningCount=0;
            }
            if(op.equals("÷")){
                runningCount=0;
            }
            if(!digit1.contains(".")) {
                int res = (int) runningCount;
                DATA_IN.setText(res+empty);
            }else {
                DATA_IN.setText(runningCount+empty);}
        }

        else if(digit1.equals(empty)&&!digit2.equals(empty)){ // for only the second operand
            double d2=Double.parseDouble(digit2);
            if(op.equals("+")){
                runningCount=d2;
            }
            if(op.equals("-")){
                runningCount=0-d2;
            }
            if(op.equals("X")){
                runningCount=0;
            }
            if(op.equals("÷")){
                runningCount=0;
            }
            if(!digit2.contains(".")) {
                int res = (int) runningCount;
                DATA_IN.setText(res+empty);
            }else {
                DATA_IN.setText(runningCount+empty);}
        }
        else {
            DATA_IN.setText(empty);
        }
    }
}


