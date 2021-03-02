package com.techsofts.mybooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddBook extends AppCompatActivity {
    String URL="https://mybooks023.herokuapp.com/books/book";
    String preview;
    Button save,cancle;
    EditText name,authorname,image,pages,isbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        name=findViewById(R.id.bookname);
        authorname=findViewById(R.id.authername);
        image=findViewById(R.id.imagelink);
        pages=findViewById(R.id.pagesin);
        isbn=findViewById(R.id.isbn);
        save=findViewById(R.id.save);
        cancle=findViewById(R.id.cancle);
        RequestQueue queue = Volley.newRequestQueue(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject object = new JSONObject();
                try {
                    //input your API parameters

                    object.put("name",name.getText());
                    object.put( "image",image.getText());
                    object.put(  "pages",pages.getText());
                    object.put( "isbn",isbn.getText());
                    object.put(  "author",authorname.getText());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Enter the correct url for your api service site
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
//                        Toast.makeText(Login_screen.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();
                                try {
                                    System.out.println(String.valueOf(response));
                                    Log.d("JSON", String.valueOf(response));
                                    Toast.makeText(AddBook.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(AddBook.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    //loading.dismiss();
                                    String Error = response.getString("httpStatus");
                                    if (Error.equals("")||Error.equals(null)){

                                    }else if(Error.equals("OK")){
                                        JSONObject body = response.getJSONObject("body");

                                    }else {

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    //    loading.dismiss();
                                }
//                        resultTextView.setText("String Response : "+ response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //loading.dismiss();
                        VolleyLog.d("Error", "Error: " + error.getMessage());
                        Toast.makeText(AddBook.this, "ERROR !! " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }

        });



    }
}