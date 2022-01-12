package com.example.creditcardinputexercise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextInputLayout t1, t2, t3, t4, t5;
    TextInputEditText te0, te1, te2, te3, te4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button
        btn = findViewById(R.id.button2);

        // TextInputLayout
        t1 =findViewById(R.id.textInputLayout);
        t2 = findViewById(R.id.textInputLayout2);
        t3 = findViewById(R.id.textInputLayout3);
        t4 = findViewById(R.id.textInputLayout4);
        t5 = findViewById(R.id.textInputLayout5);

        // TextInputEditText
        te0 = findViewById(R.id.textInputEditText);
        te1 = findViewById(R.id.textInputEditText1);
        te2 = findViewById(R.id.textInputEditText2);
        te3 = findViewById(R.id.textInputEditText3);
        te4 = findViewById(R.id.textInputEditText4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateCard(te0.getText())) {
                    t1.setError("Invalid card number");
                    te0.setText("");
                } else if (!validateDate(te1.getText())) {
                    t2.setError("Invalid card number");
                    te1.setText("");
                } else if (!validateSec(te2.getText())) {
                    t3.setError("Invalid card number");
                    te2.setText("");
                } else if (!validateName(te3.getText())) {
                    t4.setError("Invalid card number");
                    te3.setText("");
                } else if (!validateName(te4.getText())) {
                    t5.setError("Invalid card number");
                    te4.setText("");
                } else {
                    t1.setErrorEnabled(false);
                    t2.setErrorEnabled(false);
                    t3.setErrorEnabled(false);
                    t4.setErrorEnabled(false);
                    t5.setErrorEnabled(false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Payment Successful").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).create().show();
                }
            }
        });

    }

    private boolean validateName(Editable text) {
        String name = text.toString();
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    }

    private boolean validateSec(Editable text) {
        String regex = "^[0-9]{3,4}$";
        Pattern p = Pattern.compile(regex);
        String str = text.toString();
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }

    private boolean validateDate(Editable text) {
        String expiryDate = text.toString();
        return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
    }

    private boolean validateCard(Editable text) {
        String str = text.toString();
        if (!(str.length() == 16)) return false;
        else {
            long num = Long.parseLong(str);
            long last = (long) (num % 10);
            int sum = 0;
            int count = 1;
            while (num != 0) {
                last = num%10;
                if (count % 2 != 0) {
                    long x = last*2;
                    if(x>9) x = ((x/10)+(x%10));
                    sum += x;
                } else sum += last;
                count++;
                num = num / 10;
            }
            System.out.println(sum);
            return (sum % 10)==0;
        }
    }
}