package bu.ac.kr.caldiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Bmi extends AppCompatActivity {
    EditText edit1, edit2;
    TextView textResult;
    String height, weight, BMI, result2;
    double result1;
    Button btn1, btnBMI, btnCal, btnDia, btnFood, btnInfo, btnLogOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);
        btn1 = (Button) findViewById(R.id.BtnAdd);
        textResult = (TextView) findViewById(R.id.TextResult);
        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnCal = (Button) findViewById(R.id.btnCal);
        btnDia = (Button) findViewById(R.id.btnDia);
        btnFood = (Button) findViewById(R.id.btnFood);
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                height = edit1.getText().toString();
                weight = edit2.getText().toString();
                result1 = Double.parseDouble(weight) / ((Double.parseDouble(height) / 100) * (Double.parseDouble(height) / 100));
                if (result1 <= 18.5) {
                    result2 = "저체중입니다 살을 찌우셔야 겠어요!";
                } else if (result1 <= 23 && result1 > 18.5) {
                    result2 = "정상입니다 지금 잘하고 계세요!";
                } else if (result1 <= 25 && result1 > 23) {
                    result2 = "과체중입니다 섭취량을 조금 줄여보는건 어떨까요?";
                } else {
                    result2 = "비만입니다.... 다이어트는 필수!";
                }
                String BMI = String.format("%.2f", result1);
                textResult.setText("회원님의 BMI는 = " + BMI + "이며, " + result2 );
                return false;
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bmi.class);
                startActivity(intent);
            }
        });

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cal.class);
                startActivity(intent);
            }
        });

        btnDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dia.class);
                startActivity(intent);
            }
        });

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Food.class);
                startActivity(intent);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Info.class);
                startActivity(intent);
            }
        });
    }
}
