package com.example.hw1;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class ListFragment extends Fragment {
    public interface listFragmentListener {     // Фрашментами должна управлять активити
        public void cellClick(DigitCell c);
    }

    private listFragmentListener listener;
    private int cellCount = 100;

    @Override
    public void onAttach(Activity activity) {
        Log.d("check", "onAttach");
        super.onAttach(activity);
        try {
            listener = (listFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement listFragmentListener");
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            cellCount = 100;
            return;
        }

        cellCount = savedInstanceState.getInt("count");
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedData) {
        View v = inflater.inflate(R.layout.list_fragment, null);
        Log.d("check", String.valueOf(cellCount));


        final Vector<DigitCell> cells = new Vector<DigitCell>(cellCount);
        for (int i = 0; i < cellCount; ++i) {
            cells.add(new DigitCell(i + 1));
        }


        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.numberList);
        recyclerView.removeAllViewsInLayout();

        int columns = 3;

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 4;
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columns));

        final DataAdapter adapter = new DataAdapter(inflater, cells, listener);
        recyclerView.setAdapter(adapter);



        Button addButton = (Button) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ListFragment.this.cellCount++;
                adapter.addCell(ListFragment.this.cellCount);
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", cellCount);
    }
}
