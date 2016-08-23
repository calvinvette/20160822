package com.nextgened.dnd.diceroller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    @BindView(R.id.tfUserName) EditText tfUserName;
    @BindView(R.id.tfEmail) EditText tfEmail;
    @BindView(R.id.tfBirthDate) EditText tfBirthDate;
    @BindView(R.id.tvHello) TextView tvHello;

    // Attach in activity_main.xml's by setting btnRegister's onClick method to the method name
    @OnClick(R.id.btnRegister)
    public void registerUser(View button) {
        //EditText tfUserName = (EditText) findViewById(R.id.tfUserName);
        String userName = tfUserName.getText().toString();

//        String birthDate = ((EditText) findViewById(R.id.tfBirthDate)).getText().toString();
        String birthDate = tfBirthDate.getText().toString();

//        String email = ((EditText) findViewById(R.id.tfEmail)).getText().toString();
        String email = tfEmail.getText().toString();

        Log.i(MainActivity.class.getName(), "User Name: " + userName);
        Log.i(MainActivity.class.getName(), "BirthDate: " + birthDate);
        Log.i(MainActivity.class.getName(), "Email: " + email);

        tvHello.setText(getString(R.string.hello_statement, userName));
        String registered_user = getString(R.string.registered_user, userName);
        Toast.makeText(this, registered_user, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvHello.setText(getString(R.string.hello_statement, getString(R.string.unregistered_user)));
    }


}
