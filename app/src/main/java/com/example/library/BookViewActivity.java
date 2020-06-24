package com.example.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookViewActivity extends AppCompatActivity {
    Button  buttonBook;
    private TextView bookname;
    public String bkname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);
        getincomingintent();
        buttonBook = (Button) findViewById(R.id.buttonBook);
        buttonBook.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              if (v == buttonBook) {
                                                  issueBook();
                                                  Toast.makeText(getApplicationContext(), "Book Issued:" + bkname, Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }
        );

        //bookname = (TextView) findViewById(R.id.image_description);
        //Toast.makeText(this,"bookname"+ bookname,Toast.LENGTH_SHORT).show();
       // buttonBook.setOnClickListener(this);

    }

    private void getincomingintent(){
        if (getIntent().hasExtra("image_URL")&& getIntent().hasExtra("bookname")&&getIntent().hasExtra("Author")&&getIntent().hasExtra("bookid")){
            String imageurl = getIntent().getStringExtra("image_URL");
            String bookname = getIntent().getStringExtra("bookname");
            String author = getIntent().getStringExtra("Author");
            bkname= bookname;


            setimage(imageurl,bookname,author);
        }

    }
    private void issueBook(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_BOOKISSUE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("bookname",bkname);

                return params;
            }


        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void setimage(String imageurl, String bookname , String author){
        TextView name = findViewById(R.id.image1_description);
        name.setText(bookname);
        TextView name1 = findViewById(R.id.image_description);
        name1.setText(author);
        TextView name2 = findViewById(R.id.bookid);

        ImageView image = findViewById(R.id.image2);
        Glide.with(this)
                .load(imageurl)
                .into(image);


    }



    }


