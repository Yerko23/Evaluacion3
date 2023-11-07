package com.example.evaluacion3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactosDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contacto";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDO = "apellido";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TELEFONO = "telefono";
    private static final String COLUMN_CIUDAD = "ciudad";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_APELLIDO + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_TELEFONO + " TEXT, " +
                    COLUMN_CIUDAD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TABLE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String nombre, String apellido, String email, String telefono, String ciudad) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_APELLIDO, apellido);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_TELEFONO, telefono);
        values.put(COLUMN_CIUDAD, ciudad);

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        return newRowId;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getContactById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NOMBRE, COLUMN_APELLIDO, COLUMN_EMAIL, COLUMN_TELEFONO, COLUMN_CIUDAD};
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }

    public int updateContact(long id, String nombre, String apellido, String email, String telefono, String ciudad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_APELLIDO, apellido);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_TELEFONO, telefono);
        values.put(COLUMN_CIUDAD, ciudad);
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteContact(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}
