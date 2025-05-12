package bu.ac.kr.caldiary;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class Cal extends AppCompatActivity {
    EditText edit;
    TextView text;

    XmlPullParser xpp;
    String key = "Xz56j4w4KJdgmRWwojz11Ut9YdMHO0teXRXmlNWh7EaC35ouuXGGL5Cj2L1ktToNjtGqvLKh5nJXZNMxkixofg%3D%3D";
    String data;
    Button btnBMI, btnCal, btnDia, btnFood, btnInfo, btnLogOut, btnCal1, btnImage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal);

        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.result);
        btnBMI = (Button) findViewById(R.id.btnBMI);
        btnCal = (Button) findViewById(R.id.btnCal);
        btnDia = (Button) findViewById(R.id.btnDia);
        btnFood = (Button) findViewById(R.id.btnFood);
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnCal1 = (Button) findViewById(R.id.btnCal1);
        btnImage1 = (Button) findViewById(R.id.btnImage1);

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

        btnCal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Cal.this);
                dlg.setTitle("이미지도 클릭해 보세요!"); //제목
                dlg.setMessage("여려분이 드시고 있는 현재 음식에 대한 정보를 모르시겠나요?\n\n" +
                               " 정확한 음식에 대한 영양성분을 알고 싶다면 검색창에 음식을 입력해주세요!"); // 메시지
                dlg.setIcon(R.drawable.icon_1); // 아이콘 설정
//                버튼 클릭시 동작
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(Cal.this, "확인을 누르셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
        btnImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] versionArray = new String[] {"튀긴 후라이드 닭다리1조각(250kcal)","삶은 닭가슴살 300그램(450kcal)","양념 닭다리1조각(267.3kcal)"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(Cal.this);
                dlg.setTitle("       깨알 상식\n"+
                             "더 몸에 안 좋은것은 무엇일까요?\n"+
                        "밑에 메세지에 정답 확인 가능"); //제목
                dlg.setIcon(R.drawable.icon_1); // 아이콘 설정
//                버튼 클릭시 동작
                dlg.setItems(versionArray,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                dlg.setPositiveButton("정답은?", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(Cal.this, "양념 치킨입니다. 칼로리는 비교적 낮지만 튀긴것과 양념에는 인체에 해로운 성분이 있습니다. 칼로리가 전부 아닙니다! ", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
            }
        });
    }

    public void mOnClick(View v) {
        if (v.getId() == R.id.button) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = getXmlData();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(data);
                        }
                    });
                }
            }).start();
        }
    }

    String getXmlData() {
        StringBuffer buffer = new StringBuffer();
        String str = edit.getText().toString();//EditText에 작성된 Text얻어오기
        String food = URLEncoder.encode(str);

        String queryUrl = "https://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1?serviceKey=" + key + "&desc_kor=" + food + "&pageNo=1&numOfRows=3&type=xml";
        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    buffer.append("시작\n\n");
                } else if (eventType == XmlPullParser.START_TAG) {
                    tag = xpp.getName();

                    if (tag.equals("item")) ;
                    else if (tag.equals("DESC_KOR")) {
                        buffer.append("음식 : ");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("SERVING_WT")) {
                        buffer.append("1회제공량(g) : ");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT1")) {
                        buffer.append("열량 (kcal) :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT2")) {
                        buffer.append("탄수화물 (g) :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT3")) {
                        buffer.append("단백질 (g)  :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT4")) {
                        buffer.append("지방 (g) :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT5")) {
                        buffer.append("당류 (g) :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT6")) {
                        buffer.append("나트륨 (mg) :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT7")) {
                        buffer.append("콜레스테롤 (mg)  :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT8")) {
                        buffer.append("포화지방산 (g) :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    } else if (tag.equals("NUTR_CONT9")) {
                        buffer.append("트랜스지방산 (g) :");
                        xpp.next();
                        buffer.append(xpp.getText());
                        buffer.append("\n");
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                } else if (eventType == XmlPullParser.END_TAG) {
                    tag = xpp.getName();

                    if (tag.equals("item")) buffer.append("\n");
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            buffer.append("에러\n");
        }

        buffer.append("검색 완료\n");
        return buffer.toString();

    }
}
