package bu.ac.kr.caldiary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {
    Button btnobesity, btnobesityyt, btnhbp, btnhbpyt, btnhylip, btnhylipyt, bntfatliver, bntfatliveryt;
    Button btnBMI, btnCal, btnDia, btnFood, btnInfo, btnLogOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        btnobesity = (Button) findViewById(R.id.BtnObesity);
        btnobesityyt = (Button) findViewById(R.id.BtnObesityYT);
        btnhbp = (Button) findViewById(R.id.BtnHBP);
        btnhbpyt = (Button) findViewById(R.id.BtnHBPYT);
        btnhylip = (Button) findViewById(R.id.BtnHyLip);
        btnhylipyt = (Button) findViewById(R.id.BtnHyLipYT);
        bntfatliver = (Button) findViewById(R.id.BtnFatLiver);
        bntfatliveryt = (Button) findViewById(R.id.BtnFatLiverYT);

        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnCal = (Button) findViewById(R.id.btnCal);
        btnDia = (Button) findViewById(R.id.btnDia);
        btnFood = (Button) findViewById(R.id.btnFood);
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnobesity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://namu.wiki/w/%EB%B9%84%EB%A7%8C");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnobesityyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/results?search_query=%EB%B9%84%EB%A7%8C+%EC%98%88%EB%B0%A9");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnhbp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://namu.wiki/w/%EA%B3%A0%ED%98%88%EC%95%95");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnhbpyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/results?search_query=%EA%B3%A0%ED%98%88%EC%95%95+%EC%98%88%EB%B0%A9");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnhylip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://namu.wiki/w/%EA%B3%A0%EC%A7%80%ED%98%88%EC%A6%9D");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btnhylipyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/results?search_query=%EA%B3%A0%EC%A7%80%ED%98%88%EC%A6%9D+%EC%98%88%EB%B0%A9");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        bntfatliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://namu.wiki/w/%EC%A7%80%EB%B0%A9%EA%B0%84");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        bntfatliveryt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/results?search_query=%EC%A7%80%EB%B0%A9%EA%B0%84+%EC%98%88%EB%B0%A9");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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
