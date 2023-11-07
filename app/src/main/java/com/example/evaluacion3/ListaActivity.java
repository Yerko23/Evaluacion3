package com.example.evaluacion3;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Contacto> contactos;
    private daoContacto dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listView = findViewById(R.id.ListView);
        dao = new daoContacto(this);
        contactos = dao.verTodo();

        // Crear un ArrayList con los nombres de los contactos
        ArrayList<String> nombres = new ArrayList<>();
        for (Contacto contacto : contactos) {
            nombres.add(contacto.getNombre());
        }

        // Crear un ArrayAdapter para mostrar los nombres en el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Maneja el clic en un elemento de la lista (puedes agregar lógica para editar o eliminar aquí)
                Contacto selectedContact = contactos.get(position);
                Toast.makeText(ListaActivity.this, "Clic en " + selectedContact.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
