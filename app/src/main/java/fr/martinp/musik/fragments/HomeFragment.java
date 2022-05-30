package fr.martinp.musik.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;

import fr.martinp.musik.R;
import fr.martinp.musik.adapters.CoursAdapter;
import fr.martinp.musik.models.CoursModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private CoursAdapter coursAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView coursContainer = view.findViewById(R.id.coursContainer);
        coursAdapter = new CoursAdapter(getCours(), getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coursContainer.setLayoutManager(linearLayoutManager);
        coursContainer.setAdapter(coursAdapter);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<CoursModel> getCours(){
        ArrayList<CoursModel> coursList = new ArrayList<>();
        CoursModel cours1 = new CoursModel(LocalDateTime.now(), "Basse");
        coursList.add(cours1);
        CoursModel cours2 = new CoursModel(LocalDateTime.now().plusDays(5), "Guitare");
        coursList.add(cours2);
        CoursModel cours3 = new CoursModel(LocalDateTime.now().plusDays(6), "Piano");
        coursList.add(cours3);
        CoursModel cours4 = new CoursModel(LocalDateTime.now().plusDays(6), "Triangle");
        coursList.add(cours4);
        CoursModel cours5 = new CoursModel(LocalDateTime.now().plusDays(15), "Ukulélé");
        coursList.add(cours5);

        return coursList;

    }
}