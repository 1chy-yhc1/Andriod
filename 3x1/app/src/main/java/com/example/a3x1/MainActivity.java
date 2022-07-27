package com.example.a3x1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private static String convertDoubleToString(String val) {
        BigDecimal bd = new BigDecimal(String.valueOf(val));
        return bd.stripTrailingZeros().toPlainString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setText("API版本: " + android.os.Build.VERSION.SDK + ",安卓版本: " + android.os.Build.VERSION.RELEASE);

        EditText n_get=(EditText) findViewById(R.id.input_n);

        Button delete=(Button) findViewById(R.id.delete);
        delete.setOnClickListener(view -> {
            if(TextUtils.isEmpty(n_get.getText()) || n_get.getText().toString().trim().length()==0){
                Toast.makeText(MainActivity.this,"你没有输入数字，请输入任意大小的数字",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "已清除", Toast.LENGTH_SHORT).show();
                n_get.setText(null);
            }
        });


        Button confirm=(Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(view -> {

            try{
                if (TextUtils.isEmpty(n_get.getText()) || n_get.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_SHORT).show();

                    TextView tv1 = (TextView) findViewById(R.id.tv1);
                    TextView tv2 = (TextView) findViewById(R.id.tv2);
                    TextView tv3 = (TextView) findViewById(R.id.tv3);
                    tv1.setText(" n = 0");
                    tv2.setText(" i  = 0 ");
                    tv3.setText("你没有输入数字");
                }

                else {

                    TextView tv1 = (TextView) findViewById(R.id.tv1);
                    TextView tv2 = (TextView) findViewById(R.id.tv2);

                    int i = 0, j = 0;
                    double n= Double.parseDouble(n_get.getText().toString());
                    String n_s;
                    String n_s_f;

                    TextView tv3 = (TextView) findViewById(R.id.tv3);
                    String n_l=String.valueOf(n_get.length());
                    tv3.setText("你输入了 "+n_l+" 位数字");

                    String[] k=new String[2147486];
                    while (n != 1) {
                        {
                            if (n % 2 == 0) {
                                n /= 2;
                                n_s=String.valueOf(n);
                                n_s_f=convertDoubleToString(n_s);
                                k[j] = n_s_f;
                                j++;
                            } else {
                                n = n * 3 + 1;
                            }
                        }
                        i++;
                    }
                    String st = "";
                    for (int l = 0; l < j; l++) {
                        st = st + " " + k[l];
                    }
                    tv1.setText(" n = " + st + " ");
                    tv2.setText(" i  =  " + i + " ");
                }
            }catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "数字太大不支持这么大的数字，请输个更小的数字", Toast.LENGTH_SHORT).show();
            }catch (ArrayIndexOutOfBoundsException e){
                Toast.makeText(MainActivity.this,"数字太大不支持这么大的数字，请输个更小的数字 arr",Toast.LENGTH_SHORT).show();
            }
        });

    }
}