package com.example.analogstop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button start;
    Button pause;
    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.one);
        pause=(Button)findViewById(R.id.two);
        stop=(Button)findViewById(R.id.three);
        final View view=new Watch(this);

        final com.example.analogstop.Watch y=(com.example.analogstop.Watch)findViewById(R.id.watch);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y.starttimer();
                start.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                //stop.setVisibility(View.VISIBLE);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y.pausetimer();

                if(pause.getText().toString().equalsIgnoreCase("pause")){
                    pause.setText("continue");
                    stop.setVisibility(View.VISIBLE);
                }else{
                    pause.setText("pause");
                    stop.setVisibility(View.GONE);
                }


            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y.resetTimer();
                stop.setVisibility(View.GONE);
                pause.setVisibility(View.GONE);
                pause.setText("pause");
                start.setVisibility(View.VISIBLE);
            }
        });
    }
}
