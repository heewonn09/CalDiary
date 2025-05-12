package bu.ac.kr.caldiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Test extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        setTitle("비만에 좋은 음식");
        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);
    }
    public class MyGalleryAdapter extends BaseAdapter {

        Context context;
        Integer[] posterID = {R.drawable.ot, R.drawable.so, R.drawable.sa, R.drawable.nu,
                R.drawable.ab, R.drawable.eg, R.drawable.ch, R.drawable.qu};//이미지 ID를 배열로 저장하자. 아이디는 십진수
        String[] posterTitle={"오트밀", "수프","샐러드","견과류","아보카도","달걀",
                "코티지치즈","퀴노아"};

        public MyGalleryAdapter(Context c) {
            context = c;
        }//생성자

        //메소드 추가

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) { //i이미지 index임. 각각의 갤러리 요소를 클릭하면 동작하는 부분
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 300)); //200, 300 너비높이 속성을 설정하는 메소드
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); //이미지 배치 확대축소 설정
            imageView.setPadding(5, 5, 5, 5); //이미지 테두리여백
            imageView.setImageResource(posterID[i]); //이미지설정

            TextView text = (TextView)findViewById(R.id.text);

            final int pos = i; //final 처리 변수는 onCreate이 종료가 되어도 사용할 목적으로 처리하는 변수
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ImageView ivPoster=(ImageView)findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);

                    text.setText(posterTitle[pos]);

                    Toast toast = new Toast(getApplicationContext());
                    //토스트 메세지에 셋팅한 뷰를 하나 생성 View.inflate()메소드가 xml을 토스트메세지로
                    View toastView = (View)View.inflate(getApplicationContext(),
                            R.layout.toast,null); // toast.xml을 확장해서 toast뷰생성
                    TextView toastText = (TextView)toastView.findViewById(R.id.textView1);
                    toastText.setText(posterTitle[pos]);
                    toast.setView(toastView);
                    toast.show();

                    ivPoster.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Uri uri = Uri.parse("https://namu.wiki/w/"+posterTitle[pos]);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    });

                    return false;//눌리지 않은 상태
                }
            }); //터치했을때 포스터 큰것을 보여주자.
            return imageView;
        }
    }//MainGalleryAdapter
}

