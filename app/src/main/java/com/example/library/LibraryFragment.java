package com.example.library;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LibraryFragment extends Fragment implements View.OnClickListener {

    private EditText editTextbookname,editTextimage,editTextAuthor;
    private Button submit;



    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        editTextbookname = (EditText)view.findViewById(R.id.editTextbookname);
        editTextAuthor = (EditText)view.findViewById(R.id.editTextAuthor);
        editTextimage = (EditText)view.findViewById(R.id.editTextimage);

        submit = (Button)view.findViewById(R.id.submit);
        submit.setOnClickListener(this);

        return view;
    }


    private void bookpost() {
        final String bookname = editTextbookname.getText().toString().trim();
        final String Author = editTextAuthor.getText().toString().trim();
        final String image = editTextimage.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_BOOKPOST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("bookname", bookname);
                params.put("Author", Author);
                params.put("image", image);
                return params;
            }
        };
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == submit)
            bookpost();
    }

}
