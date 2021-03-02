package com.techsofts.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddNew extends AppCompatActivity {
     String URL="https://mybooks023.herokuapp.com/books/";
     String preview;
     Button save,cancle;
    EditText name,authorname,image,pages,isbn;
    ImageView priview;
     @SuppressLint("WrongViewCast")
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        name=findViewById(R.id.bookname);
        authorname=findViewById(R.id.authername);
        image=findViewById(R.id.imagelink);
        pages=findViewById(R.id.pagesin);
        isbn=findViewById(R.id.isbn);
        save=findViewById(R.id.save);
        cancle=findViewById(R.id.cancle);


        final String id = getIntent().getStringExtra("id");
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, URL+"books/"+id, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            name.setText(response.getString("name"));
                            authorname.setText(response.getString("author"));
                            image.setText(response.getString("image"));
                            preview=response.getString("image");
                            pages.setText(response.getString("pages"));
                            isbn.setText(response.getString("isbn"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest putRequest = new StringRequest(Request.Method.POST, URL+"book",
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", String.valueOf(error));
                            }
                        }
                ) {

                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("id", id);
                        params.put("name",name.getText().toString() );
                        params.put("image", image.getText().toString());
                        params.put("pages", pages.getText().toString());
                        params.put("isbn", isbn.getText().toString());
                        params.put("author",authorname.getText().toString() );

                        return params;
                    }

                };

                queue.add(putRequest);
            }
        });


// add it to the RequestQueue
      queue.add(getRequest);
    }
}