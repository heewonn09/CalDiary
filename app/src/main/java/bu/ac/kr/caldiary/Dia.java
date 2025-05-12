package bu.ac.kr.caldiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.widget.Switch;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;



import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class Dia extends AppCompatActivity {
    CalendarView calview1;
    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;
    Button btnBMI, btnCal, btnDia, btnFood, btnInfo, btnLogOut, btnDial, btnDiary, btnDial2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dia);

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnCal = (Button) findViewById(R.id.btnCal);
        btnDia = (Button) findViewById(R.id.btnDia);
        btnFood = (Button) findViewById(R.id.btnFood);
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnDial = (Button) findViewById(R.id.btnDial);
        btnDiary = (Button) findViewById(R.id.btnDiary);
        btnDial2 = (Button) findViewById(R.id.btnDial2);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                fileName = Integer.toString(i) + "_" + Integer.toString(i1 + 1) + "_" + Integer.toString(i2) + "_" + ".txt";
                String str = readDiary(fileName);
                edtDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + "저장됨", Toast.LENGTH_LONG).show();
                } catch (IOException e) {

                }
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(Dia.this);
                dlg.setTitle("고객센터 전화번호"); //제목
                dlg.setMessage("  \n\n              010-5129-8609"); // 메시지
                dlg.setIcon(R.drawable.icon12); // 아이콘 설정
//                버튼 클릭시 동작
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(Dia.this, "확인을 누르셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });

        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Dia.this);
                dlg.setTitle("이미지도 클릭해보세요!"); //제목
                dlg.setMessage(" 칼로리 다이어리에 오신것을 환영합니다!\n" +
                        "칼로리 다이어리는 성인병 예방에 도움을 주는 목적으로 제작하였습니다!\n" +
                        "저희는 회원님에 도움을 주고자 다양한 기능을 구현해 봤습니다!\n" +
                        "" +
                        "  \n" +
                        "사용해 주신 모든 분들께 감사한 말씀을 전해드리고자 메세지를 보냅니다!\n" +
                        "많은 사용 부탁드립니다!\n"); // 메시지
                dlg.setIcon(R.drawable.icon2); // 아이콘 설정
//                버튼 클릭시 동작
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(Dia.this, "확인을 누르셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });

        btnDial2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Dia.this);
                dlg.setTitle("칼로리 다이어리"); //제목
                dlg.setMessage("     현재 구성요소\n" +
                        "1) 날짜별로 식단을 입력 후 저장 기능\n" +
                        "2) 성인병에 관한 정보 제공(고혈압, 비만, 당뇨 등)\n" +
                        "3) 성인병 예방에 도움이 되는 식단 정보(일일 권장섭취량, 레시피, 열량 등)\n" +
                        "4) 현재 자신의 신장, 체중, 나이를 입력 후 bmi측정 값\n" +
                        "5) 음식을 검색 시 일일 식단 및 열량과 영양분표를 제시\n" +
                        ""); // 메시지
                dlg.setIcon(R.drawable.icon2); // 아이콘 설정
//                버튼 클릭시 동작
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(Dia.this, "확인을 누르셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
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

            String readDiary(String fName) {
                String diaryStr = null;
                FileInputStream inFs;
                try {
                    inFs = openFileInput(fName);
                    byte[] txt = new byte[500];
                    inFs.read(txt);
                    inFs.close();
                    diaryStr = (new String(txt)).trim();
                    btnWrite.setText("수정하기");
                } catch (IOException e) {
                    edtDiary.setHint("식단없음");
                    btnWrite.setText("새로저장");
                }
                return diaryStr;
            }

            {


            }//onCreate
        }
