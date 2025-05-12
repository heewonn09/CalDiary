package bu.ac.kr.caldiary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Join {
    EditText edtId, edtPw;
    Button mainBtnJoin, mainBtnLogin;
    int idFlag = 0;
    int pwFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = (EditText) findViewById(R.id.edtId);
        edtPw = (EditText) findViewById(R.id.edtPw);
        mainBtnJoin = (Button) findViewById(R.id.mainBtnJoin);
        mainBtnLogin = (Button) findViewById(R.id.mainbtnLogin);

        mainBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
            }
        });

        mainBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor;
                sqlDB = myHelper.getReadableDatabase();
                cursor = sqlDB.rawQuery("SELECT * FROM JoinInfo;", null);
                String eId = null;
                String ePw = null;
                String dId = null;
                String dPw = null;
                while (cursor.moveToNext()) {
                    dId = cursor.getString(0);
                    dPw = cursor.getString(1);
                    eId = edtId.getText().toString();
                    ePw = edtPw.getText().toString();
                    if (dId.equals(eId)) {
                        idFlag = 1;
                        if (dPw.equals(ePw)) {
                            pwFlag = 1;
                            Toast.makeText(getApplicationContext(), "안녕하세요 회원님(정상회원)", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Dia.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "회원님 비밀번호 오류입니다", Toast.LENGTH_LONG).show();
                        }
                    } else {

                    }
                }
                cursor.close();
                sqlDB.close();
                if (idFlag == 0 && pwFlag == 0) {
                    Toast.makeText(getApplicationContext(), "아이디가 틀립니다. 회원가입해 주세요", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}