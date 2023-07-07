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
import java.util.Objects;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList;

    Boolean esUnGasto;

    public ReporteAdapter(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ReporteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reporte,parent, false);
        return new ReporteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReporteAdapter.ViewHolder holder, int position) {
        esUnGasto = true;
        HashMap<String,String> map = arrayList.get(position);
        holder.mtvIdMovimiento.setText(map.get("idmovimiento"));
        holder.mtvDescripcion.setText(map.get("descripcion"));
        holder.mtvMonto.setText(map.get("monto"));
        holder.mtvFecha.setText(map.get("fecha"));
        holder.mtvMovimiento.setText(map.get("movimiento"));
        if (esUnGasto != null && Objects.equals(map.get("movimiento"), "-1")) {
            esUnGasto = false;
            holder.mtvIdMovimiento.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.color_2));
            holder.mtvDescripcion.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.color_2));
            holder.mtvMonto.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.color_2));
            holder.mtvFecha.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.color_2));
            holder.mtvMovimiento.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.color_2));
        }

        if (esUnGasto != null && Objects.equals(map.get("movimiento"), "1")) {
            esUnGasto = true;
            holder.mtvIdMovimiento.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.black));
            holder.mtvDescripcion.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.black));
            holder.mtvMonto.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.black));
            holder.mtvFecha.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.black));
            holder.mtvMovimiento.setTextColor( holder.itemView.getContext().getResources().getColor(R.color.black));

        }
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
