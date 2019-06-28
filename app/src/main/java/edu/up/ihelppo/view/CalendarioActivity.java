package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Date;

import edu.up.ihelppo.R;

public class CalendarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                String data = dayOfMonth + "/" + month + "/" + year;

                Intent intent = new Intent(CalendarioActivity.this, CadastrarAtividadeActivity.class);
                intent.putExtra("DATA_ATIVIDADE", data);
                startActivity(intent);
            }
        });
    }
}
