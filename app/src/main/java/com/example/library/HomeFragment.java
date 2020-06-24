package com.example.library;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.library.Constants.BOOK_URL;

public class HomeFragment extends Fragment {




    RecyclerView recyclerView;
    List<Book> bookList;





    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView =  view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        bookList = new ArrayList<>();
        loadBooks();



        return view;



    }

    private void loadBooks(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BOOK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                            recyclerView.setAdapter(adapter);
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
        });

        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }

}
