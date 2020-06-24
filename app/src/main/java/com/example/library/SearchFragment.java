package com.example.library;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.library.Constants.BOOKSEARCH_URL;

public class SearchFragment extends Fragment {

    private EditText searchstrng;




    RecyclerView recyclerView2;
    List<Book> bookList;
    Button search;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView2 =  view.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookList = new ArrayList<>();



        searchstrng = (EditText) view.findViewById(R.id.editTextSearch);
        search = view.findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadBooks();
            }
        });


        return view;

    }

    private void loadBooks(){
        final String bkname = searchstrng.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BOOKSEARCH_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Search", "onResponse: " + response);
                        try {
                            JSONArray books = new JSONArray(response);
                            for(int i=0; i<books.length(); i++){

                                JSONObject book = books.getJSONObject(i);
                                bookList.add(new Book(
                                        book.getInt("bookid"),
                                        book.getString("bookname"),
                                        book.getString("Author"),
                                        book.getString("image")
                                ));
                            }
                            BookAdapter adapter = new BookAdapter(getActivity(), bookList);
                            recyclerView2.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("bookname",bkname);

                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }

}
