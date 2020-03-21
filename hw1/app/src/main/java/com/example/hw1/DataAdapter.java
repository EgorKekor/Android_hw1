package com.example.hw1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Vector;
import java.util.zip.Inflater;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private final ListFragment.listFragmentListener listener;
    private LayoutInflater inflater;
    private Vector<DigitCell> cells;

    public void addCell(Integer num) {
        cells.add(new DigitCell(num));
    }


    DataAdapter(LayoutInflater inflater, Vector<DigitCell> cells, ListFragment.listFragmentListener listener) {
        this.cells = cells;
        this.inflater = inflater;
        this.listener = listener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        DigitCell cell = cells.get(position);
        holder.button.setBackgroundColor(cell.getColor());
        holder.button.setText(cell.getValue().toString());
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {       // При создании ищет вьюху 1 раз и хранит ссылку
        final Button button;

        public DigitCell getCell() {
            return cells.get(getAdapterPosition());
        }
        public ViewHolder(View view) {
            super(view);
            button = (Button) view.findViewById(R.id.listButton);

            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.cellClick(getCell());
                }
            });
        }
    }
}
