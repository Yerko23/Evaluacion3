package com.example.evaluacion3;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ingreso extends AppCompatActivity {
    EditText etNombre, etApellido, etEmail, etTelefono, etCiudad;
    Button btnGuardar;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        etNombre = findViewById(R.id.et_nombre);
        etApellido = findViewById(R.id.et_apellido);
        etEmail = findViewById(R.id.et_email);
        etTelefono = findViewById(R.id.et_telefono);
        etCiudad = findViewById(R.id.et_ciudad);
        btnGuardar = findViewById(R.id.btn_guardar);
        dbHelper = new DatabaseHelper(this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String email = etEmail.getText().toString();
                String telefono = etTelefono.getText().toString();
                String ciudad = etCiudad.getText().toString();

                long newRowId = dbHelper.insertData(nombre, apellido, email, telefono, ciudad);

                if (newRowId != -1) {
                    Toast.makeText(Ingreso.this, "Datos guardados con éxito.", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etApellido.setText("");
                    etEmail.setText("");
                    etTelefono.setText("");
                    etCiudad.setText("");

                    // Después de guardar, inicia la actividad ListaActivity
                    Intent intent = new Intent(Ingreso.this, ListaActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Ingreso.this, "Error al guardar los datos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
