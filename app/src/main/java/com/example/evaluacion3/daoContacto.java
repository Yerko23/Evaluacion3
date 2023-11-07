package com.example.evaluacion3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoContacto {
    private SQLiteDatabase bd;
    private Context context;

    public daoContacto(Context context) {
        this.context = context;
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        bd = dbHelper.getWritableDatabase();
    }

    public boolean insertar(Contacto contacto) {
        ContentValues values = new ContentValues();
        values.put("nombre", contacto.getNombre());
        values.put("apellido", contacto.getApellido());
        values.put("email", contacto.getEmail());
        values.put("telefono", contacto.getTelefono());
        values.put("ciudad", contacto.getCiudad());
        return (bd.insert("contacto", null, values)) > 0;
    }

    public boolean eliminar(int id) {
        return bd.delete("contacto", "id=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean editar(Contacto contacto) {
        ContentValues values = new ContentValues();
        values.put("nombre", contacto.getNombre());
        values.put("apellido", contacto.getApellido());
        values.put("email", contacto.getEmail());
        values.put("telefono", contacto.getTelefono());
        values.put("ciudad", contacto.getCiudad());
        return bd.update("contacto", values, "id=?", new String[]{String.valueOf(contacto.getId())}) > 0;
    }

    public ArrayList<Contacto> verTodo() {
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        Cursor cursor = bd.rawQuery("SELECT * FROM contacto", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String apellido = cursor.getString(2);
                String email = cursor.getString(3);
                String telefono = cursor.getString(4);
                String ciudad = cursor.getString(5);

                listaContactos.add(new Contacto(id, nombre, apellido, email, telefono, ciudad));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaContactos;
    }
}


