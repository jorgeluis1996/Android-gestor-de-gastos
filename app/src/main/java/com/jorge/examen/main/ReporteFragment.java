package com.jorge.examen.main;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jorge.examen.R;
import com.jorge.examen.adapters.ReporteAdapter;
import com.jorge.examen.datos.DatosSQLite;

import java.util.ArrayList;
import java.util.HashMap;

public class ReporteFragment extends Fragment {
    RecyclerView mrvReporte;
    ArrayList<HashMap<String, String>> arrayList ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reporte, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mrvReporte = view.findViewById(R.id.rvReporte);
        mrvReporte.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mrvReporte.setAdapter(new ReporteAdapter(leerDatos()));
    }

    private  ArrayList<HashMap<String, String>> leerDatos() {
        DatosSQLite datosSQLite = new DatosSQLite(getActivity());
        Cursor cursor = datosSQLite.movimientosSelect(datosSQLite);

        if(cursor != null){
            arrayList = new ArrayList<>();
            if(cursor.moveToFirst()){
                do{
                    HashMap<String,String> map = new HashMap<>();
                    map.put("idmovimiento", cursor.getString(cursor.getColumnIndexOrThrow("idmovimiento")));
                    map.put("descripcion", cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
                    map.put("monto", cursor.getString(cursor.getColumnIndexOrThrow("monto")));
                    map.put("fecha", cursor.getString(cursor.getColumnIndexOrThrow("fecha")));
                    map.put("movimiento", cursor.getString(cursor.getColumnIndexOrThrow("movimiento")));
                    arrayList.add(map);
                }while(cursor.moveToNext());
            }
        }
        return arrayList;
    }
}