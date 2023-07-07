package com.jorge.examen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.jorge.examen.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GastosAdapter extends RecyclerView.Adapter<GastosAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList;

    public GastosAdapter(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public GastosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gastos,parent, false);
        return new GastosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GastosAdapter.ViewHolder holder, int position) {
        HashMap<String,String> map = arrayList.get(position);
        holder.mtvIdMovimiento.setText(map.get("idmovimiento"));
        holder.mtvDescripcion.setText(map.get("descripcion"));
        holder.mtvMonto.setText(map.get("monto"));
        holder.mtvFecha.setText(map.get("fecha"));
        holder.mtvMovimiento.setText(map.get("movimiento"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtvIdMovimiento, mtvDescripcion, mtvMonto, mtvFecha, mtvMovimiento;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtvIdMovimiento = itemView.findViewById(R.id.tvIdMovimiento);
            mtvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            mtvMonto = itemView.findViewById(R.id.tvMonto);
            mtvFecha = itemView.findViewById(R.id.tvFecha);
            mtvMovimiento = itemView.findViewById(R.id.tvMovimiento);
        }
    }

}
