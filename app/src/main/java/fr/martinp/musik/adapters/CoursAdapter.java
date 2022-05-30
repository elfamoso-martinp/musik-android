package fr.martinp.musik.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.martinp.musik.R;
import fr.martinp.musik.models.CoursModel;

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.ViewHolder> {



    private List<CoursModel> coursList;
    private Context context;

    public CoursAdapter(List<CoursModel> coursModelList, Context context){
        this.coursList = coursModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cours_item_design, parent, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoursModel cours = coursList.get(position);

        holder.instrument.setText(cours.getInstrument());
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        holder.date.setText(cours.getDateTime().format(formatDate));
        holder.heure.setText(cours.getDateTime().format(formatTime));
    }

    @Override
    public int getItemCount() {
        return coursList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView instrument;
        public TextView date;
        public TextView heure;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            instrument = itemView.findViewById(R.id.instrumentText);
            date = itemView.findViewById(R.id.dateText);
            heure = itemView.findViewById(R.id.hourText);
        }
    }
}
