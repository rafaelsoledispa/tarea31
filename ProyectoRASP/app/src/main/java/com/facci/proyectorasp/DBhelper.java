package com.facci.proyectorasp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rafael S on 24/8/2016.
 */
public class DBhelper extends SQLiteOpenHelper {

    public static final String CNE_RASP = "elecciones.db";
    public static final String VOTANTES_RASP = "votantes";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "APELLIDO";
    public static final String COL_4 = "RECINTO ELECTORAL";
    public static final String COL_5 = "AÑO DE NACIMIENTO";

    public DBhelper(Context context) {
        super(context, CNE_RASP, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT, %s TEXT, %s INTEGER)", VOTANTES_RASP, COL_2, COL_3, COL_4, COL_5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(String.format("DROP TABLE IF EXISTS %s", VOTANTES_RASP));
        onCreate(db);
    }

    public boolean Insertar (String nombre, String apellido, String recintoElectoral, int añoNacimiento){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoElectoral);
        contentValues.put(COL_5,añoNacimiento);
        long resultado = db.insert(VOTANTES_RASP,null,contentValues);

        if (resultado == -1){
            return false;
        }else
            return true;
    }

    public Cursor selectVerTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",VOTANTES_RASP),null);
        return res;
    }


    public boolean modificarRegistro(String id,String nombre,String apellido, String recintoElectoral, int añoNacimiento){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,recintoElectoral);
        contentValues.put(COL_5,añoNacimiento);

        db.update(VOTANTES_RASP,contentValues,"id = ?",new String[]{id});

        return true;
    }

    public Integer eliminarRegistro(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(VOTANTES_RASP,"id = ?",new String[]{id});

    }






}
