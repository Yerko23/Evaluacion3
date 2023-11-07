
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Contacto> lista;
    private daoContacto dao;
    private Contacto c;
    private Activity a;
    private int id = 0;

    public Adaptador(Activity a, ArrayList<Contacto> lista, daoContacto dao) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }

    // Devuelve la cantidad de elementos en la lista
    @Override
    public int getCount() {
        return lista.size();
    }

    // Devuelve un elemento de la lista en una posición específica
    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    // Devuelve el ID de un elemento en una posición específica
    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    // Este método se utiliza para mostrar cada elemento de la lista en la vista de la actividad
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }

        c = lista.get(posicion);

        TextView nombre = v.findViewById(R.id.nombre);
        TextView apellido = v.findViewById(R.id.apellido);
        TextView email = v.findViewById(R.id.email);
        TextView telefono = v.findViewById(R.id.telefono);
        TextView ciudad = v.findViewById(R.id.ciudad);

        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);

        nombre.setText(c.getNombre());
        apellido.setText(c.getApellido());
        email.setText(c.getEmail());
        telefono.setText(c.getTelefono());
        ciudad.setText(c.getCiudad());

        // Establece etiquetas para los botones para identificar la posición del elemento en la lista
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        // Configura el clic en el botón "Editar"
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                // Aquí implementa la lógica para editar un registro
                // Debes abrir un cuadro de diálogo que permita al usuario editar los detalles del contacto
                // y aplicar los cambios a la base de datos
            }
        });

        // Configura el clic en el botón "Eliminar"
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                // Aquí implementa la lógica para eliminar un registro
                // Debes mostrar un cuadro de diálogo de confirmación y, si el usuario confirma,
                // eliminar el registro de la base de datos
            }
        });

        return v;
    }
}
