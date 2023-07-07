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
import com.jorge.examen.adapters.GastosAdapter;
import com.jorge.examen.datos.DatosSQLite;

import java.util.ArrayList;
import java.util.HashMap;

public class GastosFragment extends Fragment {

    RecyclerView rvGastos;
    ArrayList<HashMap<String, String>> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gastos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvGastos = view.findViewById(R.id.rvGastos);
        rvGastos.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvGastos.setAdapter(new GastosAdapter(leerDatos()));
    }

    private ArrayList<HashMap<String, String>> leerDatos() {
        DatosSQLite datosSQLite = new DatosSQLite(getActivity());
        Cursor cursor = datosSQLite.movimientosSelect(datosSQLite);

        if (cursor != null) {
            arrayList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("idmovimiento", cursor.getString(cursor.getColumnIndexOrThrow("idmovimiento")));
                    map.put("descripcion", cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
                    map.put("monto", cursor.getString(cursor.getColumnIndexOrThrow("monto")));
                    map.put("fecha", cursor.getString(cursor.getColumnIndexOrThrow("fecha")));
                    String movimiento = cursor.getString(cursor.getColumnIndexOrThrow("movimiento"));
                    if (movimiento.equals("-1")) {
                        arrayList.add(map);
                    }
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    }
}