package edu.up.ihelppo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

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
                String ano, mes, dia;
                ano = String.format(Locale.getDefault(), "%04d", year);
                mes = String.format(Locale.getDefault(), "%02d", month);
                dia = String.format(Locale.getDefault(), "%02d", dayOfMonth);

                String data = ano + "-" + mes + "-" + dia;

                Intent intent = new Intent(CalendarioActivity.this, CadastrarAtividadeActivity.class);
                intent.putExtra("DATA_ATIVIDADE", data);
                startActivity(intent);
            }
        });
    }
}
