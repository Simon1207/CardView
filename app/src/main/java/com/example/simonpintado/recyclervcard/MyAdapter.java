package com.example.simonpintado.recyclervcard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Pintado on 12/11/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Movie>movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<Movie>movies, int layout,OnItemClickListener listener){
        this.movies=movies;
        this.layout=layout;
        this.itemClickListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflado de vista y es pasada por el constructor del viewHolder
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //volcado de datos
        holder.bind(movies.get(position),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView){
            super(itemView);
            textViewName=(TextView)itemView.findViewById(R.id.textViewTitle);
            imageViewPoster=(ImageView)itemView.findViewById(R.id.imageViewPoster);

        }

        public void bind(final Movie movie, final OnItemClickListener listener ) { //se pasa el modelo
            //Porcesamos los datos a renderizar
            textViewName.setText(movie.getName());
            imageViewPoster.setImageResource(movie.getPoster());
            // Definimos que por cada elemento de nuestro recycler view, tenemos un clic listener
            // que se comporta de la siguiente manera.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(movie,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Movie movie,int position);
    }
}
