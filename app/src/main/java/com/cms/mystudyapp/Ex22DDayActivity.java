package com.cms.mystudyapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Ex22DDayActivity extends AppCompatActivity{

    // Millisecond 형태의 하루(24 시간)
    private final int ONE_DAY = 24 * 60 * 60 * 1000;

    // 현재 날짜를 알기 위해 사용
    private Calendar mCalendar;

    // D-day result
    private TextView mTvResult;

    // DatePicker 에서 날짜 선택 시 호출
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker a_view, int a_year, int a_monthOfYear, int a_dayOfMonth) {
            // D-day 계산 결과 출력
            mTvResult.setText(getDday(a_year, a_monthOfYear, a_dayOfMonth));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex22_dday);

        // 한국어 설정 (ex: date picker)
        Locale.setDefault(Locale.KOREAN);

        // 현재 날짜를 알기 위해 사용
        mCalendar = new GregorianCalendar();

        // Today 보여주기
        TextView tvDate = findViewById(R.id.tv_date);
        tvDate.setText(getToday());

        // D-day 보여주기
        mTvResult = findViewById(R.id.tv_result);

        // Input date click 시 date picker 호출
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View a_view) {
                final int year = mCalendar.get(Calendar.YEAR);
                final int month = mCalendar.get(Calendar.MONTH);
                final int day = mCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Ex22DDayActivity.this, mDateSetListener, year, month, day);
                dialog.show();
            }
        };
        findViewById(R.id.btn_input_date).setOnClickListener(clickListener);
    }

    /**
     * Today 반환
     */
    private String getToday() {
        // 지정된 format 으로 string 표시
        final String strFormat = getString(R.string.format_today);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat(strFormat);
        return CurDateFormat.format(mCalendar.getTime());
    }

    /**
     * D-day 반환
     */
    private String getDday(int a_year, int a_monthOfYear, int a_dayOfMonth) {
        // D-day 설정
        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(a_year, a_monthOfYear, a_dayOfMonth);

        // D-day 를 구하기 위해 millisecond 으로 환산하여 d-day 에서 today 의 차를 구한다.
        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;

        // 출력 시 d-day 에 맞게 표시
        final String strFormat;
        if (result > 0) {
            strFormat = "D-%d";
        } else if (result == 0) {
            strFormat = "D-Day";
        } else {
            result *= -1;
            strFormat = "D+%d";
        }

        final String strCount = (String.format(strFormat, result));
        return strCount;
    }
}