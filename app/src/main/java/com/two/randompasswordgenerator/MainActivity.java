package com.two.randompasswordgenerator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {


    private static SecureRandom random = new SecureRandom();
    private static Object MainActivity;
    private CheckBox chk1,chk2,chk3,chk4;
    private SeekBar seekbar;
    private Button btn;
    private TextView seekText;
    private String temp;

    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    private int l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekText = (TextView)findViewById(R.id.seekTest);
        btn = (Button)findViewById(R.id.password_generaotr);

        chk1 = (CheckBox)findViewById(R.id.chk1);
        chk2 = (CheckBox)findViewById(R.id.chk2);
        chk3 = (CheckBox)findViewById(R.id.chk3);
        chk4 = (CheckBox)findViewById(R.id.chk4);

        seekbar = (SeekBar)findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Toast.makeText(getApplicationContext(), "seekbar progress: " + i, Toast.LENGTH_SHORT).show();
                seekText.setText(Integer.toString(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                l = seekBar.getProgress();
            }
        });


       //l = seekbar.getMax();

        if(chk1.isChecked()) temp+=ALPHA_CAPS;
        if(chk2.isChecked()) temp+=ALPHA;
        if(chk3.isChecked()) temp+=NUMERIC;
        if(chk4.isChecked()) temp+=SPECIAL_CHARS;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp="";
                if(chk1.isChecked()) temp+=ALPHA_CAPS;
                if(chk2.isChecked()) temp+=ALPHA;
                if(chk3.isChecked()) temp+=NUMERIC;
                if(chk4.isChecked()) temp+=SPECIAL_CHARS;

                if(temp.isEmpty()){
                    Toast.makeText(MainActivity.this,"You have to chose at least one option",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                    intent.putExtra("pass",generatePassword(l,temp));
                    startActivity(intent);
                }

            }
        });

    }


    public String generatePassword(int len, String dic) {
        String result = "";
        Toast.makeText(this,"Hoise jai hok",Toast.LENGTH_SHORT);
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }
}
