package com.example.starbucks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView txtName;
    private TextView txtPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPassword);

        btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMenu();
            }
        });
    }
    public void openActivityMenu()
    {
        if(txtName.getText().toString().equalsIgnoreCase("Mike")&& txtPass.getText().toString().equalsIgnoreCase("1234")){
        Intent intent = new Intent(this,ActivityMenu.class);
        intent.putExtra("Username",txtName.getText().toString());
        startActivity(intent);}
        else{
            Toast.makeText(this, "Username or Password is incorrect!", Toast.LENGTH_SHORT).show();
        }
    }
}
