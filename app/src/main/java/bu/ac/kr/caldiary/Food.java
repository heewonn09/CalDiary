package bu.ac.kr.caldiary;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import androidx.annotation.Nullable;

public class Food extends TabActivity {
    Button btnBMI, btnCal, btnDia, btnFood, btnInfo, btnLogOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        TabHost tabHost = getTabHost();

        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnCal = (Button) findViewById(R.id.btnCal);
        btnDia = (Button) findViewById(R.id.btnDia);
        btnFood = (Button) findViewById(R.id.btnFood);
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        TabHost.TabSpec tabSpec1=tabHost.newTabSpec("Ob").setIndicator("비만");
        tabSpec1.setContent(new Intent(getApplicationContext(),Ob.class));
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2=tabHost.newTabSpec("HBP").setIndicator("고혈압");
        tabSpec2.setContent(new Intent(getApplicationContext(),HBP.class));
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3=tabHost.newTabSpec("HL").setIndicator("고지혈증");
        tabSpec3.setContent(new Intent(getApplicationContext(),HL.class));
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4=tabHost.newTabSpec("FL").setIndicator("지방간");
        tabSpec4.setContent(new Intent(getApplicationContext(),FL.class));
        tabHost.addTab(tabSpec4);

        tabHost.setCurrentTab(0);

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
