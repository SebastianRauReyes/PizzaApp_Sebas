package com.rau.sebastian.pizzaapp_sebas;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PedidoActivity extends AppCompatActivity {

    private Spinner spinner1;
    private RadioGroup radioGroup;
    private CheckBox checkBox;
    private EditText editText;
    private RadioButton r1,r2,r3;
    String TipoPizza, TipoMasa, radio1;
    Double Precio;
    Integer complemento1 = 0;
    Integer complemento2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        spinner1 = (Spinner) findViewById(R.id.spinner);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        checkBox = (CheckBox) findViewById(R.id.checkBox);

        editText = (EditText) findViewById(R.id.direccion);

        r1 = (RadioButton) findViewById(R.id.radioButton1);
        r2 = (RadioButton) findViewById(R.id.radioButton1);
        r3 = (RadioButton) findViewById(R.id.radioButton1);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(parent.getContext(),
                        "Haz seleccionado : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg) {

            }

        });


    }

    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // This check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    //Do something when radio button is clicked
                    Toast.makeText(getApplicationContext(), "Masa gruesa", Toast.LENGTH_SHORT).show();

                TipoMasa = r1.getText().toString();
                break;

            case R.id.radioButton2:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "Masa delgada", Toast.LENGTH_SHORT).show();
                TipoMasa = r2.getText().toString();
                break;


            case R.id.radioButton3:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "Masa artesanal", Toast.LENGTH_SHORT).show();

                TipoMasa = r3.getText().toString();
                break;

        }
    }

    public void androidCheckBoxClicked(View view) {
        // action for checkbox click
        switch (view.getId()) {
            case R.id.checkBox:
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked())
                    Toast.makeText(this, checkBox.getText() , Toast.LENGTH_LONG).show();
                 complemento1 = 4;
                break;
            case R.id.checkBox2:
                //DO something when user check the box
                CheckBox checkBox2 = (CheckBox) view;
                if (checkBox2.isChecked())
                    Toast.makeText(this, checkBox2.getText() , Toast.LENGTH_LONG).show();
                complemento2 = 8;
                break;





        }
    }

    public void ordenardelivery(View view) {
        String getDireccion = editText.getText().toString();


        if (getDireccion.length() == 0) {

        } else {


            Toast.makeText(this, editText.getText() + " direccion correcta!", Toast.LENGTH_LONG).show();

            TipoPizza = spinner1.getSelectedItem().toString();

            if(TipoPizza.equalsIgnoreCase("Americana")){
                    Precio = 38.0;
            }else if(TipoPizza.equalsIgnoreCase("Pepperoni")){
                    Precio = 42.0;
            }else if(TipoPizza.equalsIgnoreCase("Hawaiana")){
                    Precio = 36.0;
            }else if(TipoPizza.equalsIgnoreCase("Meat Lover")){
                    Precio = 56.0;
            }

            Double PrecioTotal = Precio + complemento1 +complemento2;




           AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Confirmación de pedido");

            alertDialog.setMessage("Pizza         : "+TipoPizza+"\n"+
                                    "Tipo de masa : "+TipoMasa+"\n"+
                                    "Precio       : "+PrecioTotal+"\n"+
                                    "Direccion    : "+getDireccion);
            // Alert dialog button
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Confirmar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            alertDialog.show();

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        String fecha = dateFormat.format(date);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Pizza en camino !!!")
                .setContentText("Gracias por comprar con nosotros -"+fecha)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(this, R.color.accent))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        // Notification manager
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);



    }



}






