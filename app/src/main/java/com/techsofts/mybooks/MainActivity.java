package com.techsofts.mybooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
   public String URL="https://mybooks023.herokuapp.com/books/All";
    public RecyclerView userList;
    public Button add;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
add=findViewById(R.id.Add);
        userList=findViewById(R.id.booklist);
        layoutManager=new GridLayoutManager(this,1);
        userList.setLayoutManager(layoutManager);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBook.class);
                startActivity(intent);
            }
        });
        StringRequest request= new StringRequest(URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Code",response);

                        GsonBuilder gsonBuilder= new GsonBuilder();
                        Gson gson=gsonBuilder.create();
                        Book[] book=gson.fromJson(response, Book[].class);
                        userList.setAdapter(new ListAdopter(MainActivity.this,book));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "somthing wrong", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }


}