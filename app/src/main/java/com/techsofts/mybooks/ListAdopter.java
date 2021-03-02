package com.techsofts.mybooks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

public class ListAdopter extends RecyclerView.Adapter<ListAdopter.GithubViewHolder>{

    private Context context;
    private Book[] data;



    public ListAdopter(Context context, Book[] data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.books_layout,parent,false);
        return new GithubViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GithubViewHolder holder, int position) {
        final Book book=data[position];

        holder.Name.setText(book.getName());
        holder.Isbn.setText(String.valueOf(book.getIsbn()));
        holder.Author.setText(book.getAuthor());
      holder.Page.setText(String.valueOf(book.getPages()));
        Glide.with(holder.profile.getContext()).load(book.getImage()).into(holder.profile);




holder.edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context,AddNew.class);
        intent.putExtra("id",String.valueOf( book.getId()));

       context.startActivity(intent);
    }
});

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"User Name ="+ book.getName(), Toast.LENGTH_SHORT).show();
               /* Intent intent=new Intent(context,SelectGames.class);
                intent.putExtra("id", matka.getId());
                intent.putExtra("Title", matka.getName());
                context.startActivity(intent);
           */ }


        });
    }

    @Override
    public int getItemCount() {
        return  data.length;
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder {
        ImageView profile,edit,delete;
        TextView Name;
        TextView Author;
        TextView Page;
        TextView Isbn;

        //CardView cardView;
        public  GithubViewHolder(@NonNull View itemView) {
            super(itemView);

            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            profile=itemView.findViewById(R.id.profile);
            Name=itemView.findViewById(R.id.name);
            Author=itemView.findViewById(R.id.author);
            Page=itemView.findViewById(R.id.page);
            Isbn=itemView.findViewById(R.id.isbn);
            //    cardView=itemView.findViewById(R.id.cardview);

        }

    }

}