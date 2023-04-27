package com.example.keuzevakkenapp;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keuzevakkenapp.activity.DetailActivity;
import com.example.keuzevakkenapp.database.Keuzevak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeuzevakAdapter extends RecyclerView.Adapter<KeuzevakAdapter.KeuzevakViewHolder> implements Filterable {

    private Keuzevak[] keuzevak;
    private List<Keuzevak> keuzevakFiltered;

    public KeuzevakAdapter(Keuzevak[] keuzevak) {
        this.keuzevak = keuzevak;
        keuzevakFiltered = Arrays.asList(keuzevak);
    }

    public class KeuzevakViewHolder extends RecyclerView.ViewHolder {
        public TextView textCode, textSpec;
        public CardView vakCard;

        public KeuzevakViewHolder(View v) {
            super(v);
            textCode = v.findViewById(R.id.keuzevak_code);
            textSpec = v.findViewById(R.id.keuzevak_name);
            vakCard = v.findViewById(R.id.keuzevakCard);
        }
    }

    @NonNull
    @Override
    public KeuzevakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.keuzevak_card, parent, false);
        KeuzevakViewHolder keuzevakViewHolder = new KeuzevakViewHolder(v);
        return keuzevakViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KeuzevakViewHolder holder, int position) {
        // Assign variables
        String keuzevakCode = keuzevak[position].getCode();
        String keuzevakNaam = keuzevak[position].getName();
        String keuzevakDesc = keuzevak[position].getDescription();
        String keuzevakSpec = keuzevak[position].getSpecialization();

        // Set Text in Screen
        holder.textCode.setText(keuzevakCode);
        holder.textSpec.setText(keuzevakSpec);

        // Change color for each Specialization
        if (keuzevakSpec.equals("Algemeen")) {
            holder.textSpec.setTextColor(Color.parseColor("#9C27B0"));
        }
        if (keuzevakSpec.equals("Software Engineering")) {
            holder.textSpec.setTextColor(Color.parseColor("#2196F3"));
        }
        if (keuzevakSpec.equals("Interactie-technologie")) {
            holder.textSpec.setTextColor(Color.parseColor("#4CAF50"));
        }
        if (keuzevakSpec.equals("Forensisch ICT")) {
            holder.textSpec.setTextColor(Color.parseColor("#FFC107"));
        }
        if (keuzevakSpec.equals("Business Data Management")) {
            holder.textSpec.setTextColor(Color.parseColor("#FF9800"));
        }

        // Open Detail Activity on Click
        holder.vakCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("code", keuzevakCode);
                intent.putExtra("name", keuzevakNaam);
                intent.putExtra("desc", keuzevakDesc);
                intent.putExtra("spec", keuzevakSpec);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return keuzevak.length;
    }

    @Override
    public Filter getFilter() {

        Filter keuzevakFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Keuzevak> filteredResults = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredResults.addAll(keuzevakFiltered);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Keuzevak keuzevak : keuzevakFiltered) {
                        if (keuzevak.getCode().toLowerCase().contains(filterPattern)) {
                            filteredResults.add(keuzevak);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                Arrays.fill(keuzevak, null);
                keuzevak = (Keuzevak[]) keuzevakFiltered.toArray();
                notifyDataSetChanged();
            }
        };

        return keuzevakFilter;
    }
}
