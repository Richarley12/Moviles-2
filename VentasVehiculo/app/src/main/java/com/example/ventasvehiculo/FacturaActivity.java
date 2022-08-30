package com.example.ventasvehiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class FacturaActivity extends AppCompatActivity {


    EditText jetplaca,jetmarca,jetmodelo,jetvalor,jetcod_factura,jetfecha;
    CheckBox jcbactivo;
    ClsOpenHelper admin=new ClsOpenHelper(this,"Concesionario.db",null,1);
    String placa,marca,modelo,valor,cod_factura,fecha;
    long resp;
    int sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

<<<<<<< HEAD
        jetplaca=findViewById(R.id.etplacas);
        jetmarca=findViewById(R.id.etmarcas);
        jetmodelo=findViewById(R.id.etmodelos);
        jetvalor=findViewById(R.id.etvalores);
        jcbactivo=findViewById(R.id.etactivo);
=======
        jetplaca=findViewById(R.id.fcplacas);
        jetmarca=findViewById(R.id.fcmarcas);
        jetmodelo=findViewById(R.id.fcmodelos);
        jetvalor=findViewById(R.id.fcvalores);
        jcbactivo=findViewById(R.id.fcactivo);
>>>>>>> richar
        //jetcod_factura=findViewById(R.id.etcodfactura);
        jetfecha=findViewById(R.id.etfecha);
        sw=0;
    }
    public void Buscar(View view){
        placa=jetplaca.getText().toString();
        if (placa.isEmpty()){
            Toast.makeText(this,"La placa no existe",Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else{
            SQLiteDatabase db=admin.getReadableDatabase();
<<<<<<< HEAD
                Cursor fila= db.rawQuery("select * from TblLVehiculo where placa='"+placa+"'",null);
=======
            Cursor fila= db.rawQuery("select * from TblLVehiculo where placa='"+placa+"'",null);
>>>>>>> richar
            if(fila.moveToNext()){
                sw=1;
                jetmarca.setText(fila.getString(1));
                jetmodelo.setText(fila.getString(2));
                jetvalor.setText(fila.getString(3));
                if(fila.getString(4).equals("si"))
                    jcbactivo.setChecked(true);
                else
                    jcbactivo.setChecked(false);
            }
            else Toast.makeText(this,"Vehiculo no registrado",Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void Guardarfac(View view){
        cod_factura=jetcod_factura.getText().toString();
        fecha=jetfecha.getText().toString();
        placa=jetplaca.getText().toString();
        marca=jetmarca.getText().toString();
        modelo =jetmodelo.getText().toString();
        valor=jetvalor.getText().toString();
        if (placa.isEmpty()||marca.isEmpty()||modelo.isEmpty()||valor.isEmpty()||fecha.isEmpty()||cod_factura.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else {
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues registro=new ContentValues();
            /*registro.put("marca",marca);
            registro.put("modelo",modelo);
            registro.put("valor", Integer.parseInt(valor));*/
            registro.put("cod_factura",cod_factura);
            registro.put("fecha",fecha);
            registro.put("placa",placa);
            //if(sw==0) {
<<<<<<< HEAD
                resp = db.insert("TBLFactura", null, registro);
=======
            resp = db.insert("TBLFactura", null, registro);
>>>>>>> richar
            /*}
            else {
                resp = db.update("TblLVehiculo", registro, "placa='" + placa + "'", null);
            }*/
            if (resp>0){
                Toast.makeText(this,"Registro guardado",Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            }
            else Toast.makeText(this,"Error en registro",Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void Consultarfac(View view){
        cod_factura=jetcod_factura.getText().toString();
        if (cod_factura.isEmpty()){
            Toast.makeText(this,"El código no existe",Toast.LENGTH_SHORT).show();
            jetcod_factura.requestFocus();
        }
        else{
            SQLiteDatabase db=admin.getReadableDatabase();
            Cursor fila= db.rawQuery("select * from TBLFactura where cod_factura='"+cod_factura+"'",null);
            if(fila.moveToNext()) {
                sw = 1;
                jetfecha.setText(fila.getString(1));
                jetplaca.setText(fila.getString(2));
            }
            else Toast.makeText(this,"Vehiculo no registrado",Toast.LENGTH_SHORT).show();
            db.close();
        }
    }

    public void Cancelar(View view){
        Limpiar_campos();
    }

    public void Regresar(View view){
        Intent intmain= new Intent(this,MainActivity.class);
        startActivity(intmain);
    }

    private void Limpiar_campos(){
        jetplaca.setText("");
        jetvalor.setText("");
        jetmodelo.setText("");
        jetmarca.setText("");
        jcbactivo.setChecked(false);
        jetplaca.requestFocus();
        jetcod_factura.setText("");
        jetfecha.setText("");
        sw=0;
    }
}