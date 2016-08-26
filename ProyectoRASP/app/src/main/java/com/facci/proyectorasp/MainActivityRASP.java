package com.facci.proyectorasp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityRASP extends AppCompatActivity {

    DBhelper dbSQLITE;

    EditText txtNombre,txtApellidos,txtRecintoElectoral,txtAñoDeNacimiento,txtID;

    Button btnInsertar,btnModificar,btnEliminar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_rasp);

        dbSQLITE = new DBhelper (this);

    }

    public void insertarClick(View v){

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAñoDeNacimiento = (EditText) findViewById(R.id.txtAñoDeNacimiento);

        boolean estaInsertado = dbSQLITE.Insertar(txtNombre.getText().toString(),txtApellidos.getText().toString(),txtRecintoElectoral.getText().toString(),Integer.parseInt(txtAñoDeNacimiento.getText().toString()));

        if (estaInsertado)
            Toast.makeText(MainActivityRASP.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityRASP.this,"Lo sentimos ocurrio un error",Toast.LENGTH_SHORT).show();

        }

    public void verTodosClick(View v){

        Cursor res = dbSQLITE.selectVerTodos();
        if (res.getCount() == 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()) {
            buffer.append("ID : " + res.getString(0) + "\n");
            buffer.append("Nombre : " + res.getString(1) + "\n");
            buffer.append("Apellidos : " + res.getString(2) + "\n");
            buffer.append("RecintoElectoral : " + res.getString(3) + "\n");
            buffer.append("AñoDeNacimiento : " + res.getInt(4) + "\n");
        }

        mostrarMensaje("Registros",buffer.toString());
    }

    public void mostrarMensaje(String titulo, String Mensaje){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setTitle(Mensaje);
        builder.show();
    }

    public void modificarRegistrosClick(View v){

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAñoDeNacimiento = (EditText) findViewById(R.id.txtAñoDeNacimiento);
        txtID = (EditText) findViewById(R.id.txtID);

        boolean estaActualizado = dbSQLITE.modificarRegistro(txtID.getText().toString(),txtNombre.getText().toString(),txtApellidos.getText().toString(),txtRecintoElectoral.getText().toString(),Integer.parseInt(txtAñoDeNacimiento.getText().toString()));

        if (estaActualizado == true){
            Toast.makeText(MainActivityRASP.this,"Registro Actualizado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityRASP.this,"ERROR: Registro NO actualizado",Toast.LENGTH_SHORT).show();

        }

    }


    public void eliminarRegistroClick(View v){

        txtID = (EditText) findViewById(R.id.txtID);

        Integer registroEliminados = dbSQLITE.eliminarRegistro(txtID.getText().toString());

        if (registroEliminados > 0 ){
            Toast.makeText(MainActivityRASP.this,"Registros Eliminados",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityRASP.this,"ERROR: Registros no eliminados",Toast.LENGTH_SHORT).show();

        }



    }






}
