package com.nextgened.dnd.diceroller;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.ConstraintDescriptor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class MainActivity extends Activity {
    User userToRegister = new User();

    @BindView(R.id.tfUserName)
    EditText tfUserName;
    @BindView(R.id.tfEmail)
    EditText tfEmail;
    @BindView(R.id.tfBirthDate)
    EditText tfBirthDate;
    @BindView(R.id.tvHello)
    TextView tvHello;
    @BindView(R.id.btnMyWebView)
    Button btnMyWebView;

    // Attach in activity_main.xml's by setting btnRegister's onClick method to the method name
    @OnClick(R.id.btnRegister)
    public void registerUser(View button) {
        //EditText tfUserName = (EditText) findViewById(R.id.tfUserName);
        String userName = tfUserName.getText().toString();

//        String birthDate = ((EditText) findViewById(R.id.tfBirthDate)).getText().toString();
        String birthDate = tfBirthDate.getText().toString();

//        String email = ((EditText) findViewById(R.id.tfEmail)).getText().toString();
        String email = tfEmail.getText().toString();

        Log.v(MainActivity.class.getName(), "User Name: " + userName);
        Log.v(MainActivity.class.getName(), "BirthDate: " + birthDate);
        Log.v(MainActivity.class.getName(), "Email: " + email);


        Set<ConstraintViolation<User>> errors = new HashSet<>();
        User user = new User();
        user.setUserName(userName);
        try {
            user.setBirthDate(SimpleDateFormat.getDateInstance().parse(birthDate));
        } catch (ParseException e) {
            // TODO - Add ConstraintViolation<User> to errors...
            e.printStackTrace();
        }
        user.setEmail(email);
        user.setLastUpdated(new Date());
        user.setCreatedDate(new Date());
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        errors.addAll(validator.validate(user));

        if (errors.size() <= 0) {
            tvHello.setText(getString(R.string.hello_statement, userName));
            String registered_user = getString(R.string.registered_user, userName);
            Toast.makeText(this, registered_user, Toast.LENGTH_LONG).show();

        } else {
//            String allErrorMessages = "";
//            for (ConstraintViolation<User> error : errors) {
//                allErrorMessages += Resources.getSystem().getIdentifier(error.getMessageTemplate(), "string", null);
//            }
//            Log.e(MainActivity.class.getName(), errors.toString());
//            Toast.makeText(this, allErrorMessages, Toast.LENGTH_LONG).show();
            Toast.makeText(this, errors.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.btnMyWebView)
    public void openMyWebView(View button) {
        startActivity(new Intent(this, MyWebView.class));
    }

//    @OnClick(R.id.btnWebView)
//    public void gotoWebView(View button) {
//        startActivity(new Intent(this, AngularWebApp.class));
//    }

    @OnFocusChange(R.id.tfEmail)
    public void focusChange(View tfEmailAsView) {
//        EditText tfEmailAsEditText = (EditText) tfEmailAsView;
        if (tfEmail.hasFocus()) {
            Log.i(MainActivity.class.getName(), "Email has gained the focus");
        } else {
            Log.i(MainActivity.class.getName(), "Email has lost the focus");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvHello.setText(getString(R.string.hello_statement, getString(R.string.unregistered_user)));
    }


}
