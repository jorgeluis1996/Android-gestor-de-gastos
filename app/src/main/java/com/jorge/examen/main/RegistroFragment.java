package com.jorge.examen.main;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jorge.examen.R;
import com.jorge.examen.datos.DatosSQLite;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;


public class RegistroFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    TextInputEditText mtietDescripcion, mtietMonto;
    Switch mswMovimiento;
    Button mbtnRegistrar;
    TextView tvTotalIngresos;

    ArrayList<HashMap<String, String>> arrayList;
    Float ingresos;
    Float gastos;
    Float saldoTotal;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mtietDescripcion = view.findViewById(R.id.tietDescripcion);

        mtietMonto = view.findViewById(R.id.tietMonto);
        mswMovimiento = view.findViewById(R.id.swMovimiento);
        mbtnRegistrar = view.findViewById(R.id.btnRegistrar);


        mbtnRegistrar.setOnClickListener(this);

        mswMovimiento.setOnCheckedChangeListener(this);
        setearRegistros();
    }


    @Override
    public void onClick(View view) {

        String descripcion = mtietDescripcion.getText().toString();
        float monto = Float.parseFloat(mtietMonto.getText().toString());
        int movimiento = 0;
        if (mswMovimiento.isChecked()) {
            movimiento = 1;
        } else {
            movimiento = -1;
        }

        DatosSQLite datosSQLite = new DatosSQLite(getActivity());
        int autonumerico = datosSQLite.movimientosInsert(datosSQLite, descripcion, monto, movimiento);
        Toast.makeText(getActivity(), String.valueOf(autonumerico), Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d("Estado", String.valueOf(b));
        if (b == true) {
            mswMovimiento.setText("Ingreso");
        } else {
            mswMovimiento.setText("Gasto");
        }

    }

    private void setearRegistros() {
        arrayList = leerDatos();

    }

    private ArrayList<HashMap<String, String>> leerDatos() {
        DatosSQLite datosSQLite = new DatosSQLite(getActivity());
        Cursor cursor = datosSQLite.movimientosSelect(datosSQLite);

        if (cursor != null) {
            arrayList = new ArrayList<>();
            ingresos = 0F;
            saldoTotal = 0F;
            gastos = 0F;
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("idmovimiento", cursor.getString(cursor.getColumnIndexOrThrow("idmovimiento")));
                    map.put("descripcion", cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
                    map.put("monto", cursor.getString(cursor.getColumnIndexOrThrow("monto")));
                    map.put("fecha", cursor.getString(cursor.getColumnIndexOrThrow("fecha")));
                    String movimiento = cursor.getString(cursor.getColumnIndexOrThrow("movimiento"));
                    arrayList.add(map);
                    if (movimiento.equals("1") && ingresos != null) {
                        ingresos += Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow("monto")));
                    }
                    if (movimiento.equals("-1") && gastos != null) {
                        gastos += Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow("monto")));
                    }
                } while (cursor.moveToNext());
                if (saldoTotal != null) {
                    saldoTotal = ingresos - gastos;
                }
            }
        }
        return arrayList;
    }

}