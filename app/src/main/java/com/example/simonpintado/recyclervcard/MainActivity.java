package com.example.simonpintado.recyclervcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies=this.getAllMovies();
        mRecyclerView=(RecyclerView) findViewById(R.id.my_recycler_view);


        mLayoutManager=new LinearLayoutManager(this);
        //Agrega un nuevo gridLayoutManager con un limite de 2 columnas
       // mLayoutManager=new GridLayoutManager(this,2);
       // mLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //Implemntatamos nuestro OnItemClickListener propio, sobreescribiendo el metodo que nosotros
        //definimos en el adaptador y recibiendo los parametros que necesitamos
        mAdapter=new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {

            }
        });

                // Lo usamos en caso de que sepamos que el layout no va acabmiar de tamaño, mejorando el performance
                mRecyclerView.setHasFixedSize(true);
        // Aña un efecto por defecto, si le pasamos null lo desactivamos por completo
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Enlazamos el layout manager y adaptador directamente al recycler view
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_name:
              //  this.addName(0);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }

    }




    private List<Movie> getAllMovies(){
        return  new ArrayList<Movie>(){{
            add(new Movie("Dr. Strange",R.drawable.drstrange));
            add(new Movie("Justice Leage",R.drawable.justice));
            add(new Movie("Wolverine",R.drawable.wolf));
            add(new Movie("Wonder Woman",R.drawable.wonder));
        }};
    }
/*
    private void addName(int position) {
        names.add(position,"new Name"+(++counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);

    }

    private void deleteName(int position){
        names.remove(position);
        mAdapter.notifyItemRemoved(position);

    }
*/
}
