package com.jpc.felipe.sqlite;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user_input, lastname;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_input = (EditText) findViewById(R.id.user_input);
        lastname = (EditText) findViewById(R.id.game_input);
        textView = (TextView) findViewById(R.id.textView);

        controller = new DB_Controller(this, " ", null, 1);
    }

    public void btn_click (View view){
        switch (view.getId()){
            case R.id.button_add:
                try {
                    controller.insert_users(user_input.getText().toString(),lastname.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(MainActivity.this, "Usuário Já Cadastrado", Toast.LENGTH_SHORT).show();
                }

                break;
            case  R.id.button_delete:
                controller.delete_users(user_input.getText().toString());
                break;
            case R.id.button_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Coloque o Nome do USUARIO ");
                final EditText new_user = new EditText(this);
                dialog.setView(new_user);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick (DialogInterface dialogInterface, int i){
                        controller.update_users(user_input.getText().toString(),new_user.getText().toString());
                }
        });
                dialog.show();
                break;

            case R.id.list_user:
                controller.list_all_users(textView);
                break;
        }
    }


}
