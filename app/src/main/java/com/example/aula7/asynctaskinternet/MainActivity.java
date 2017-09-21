package com.example.aula7.asynctaskinternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aula7.asynctaskinternet.Adapter.CommentAdapter;
import com.example.aula7.asynctaskinternet.Models.Comment;
import com.example.aula7.asynctaskinternet.Parser.Json;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
    RecyclerView recyclerView;
    List<Comment>  commentList;
    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        progressBar= (ProgressBar) findViewById(R.id.id_pb_1);
        button= (Button) findViewById(R.id.btn_1);
       recyclerView= (RecyclerView) findViewById(R.id.id_rv_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    public Boolean isOnLine(){
        // objeto connectivitymanager para manejar las conectividadess
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //obtener el estado de la conexion
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //validar el estado de la red
        if(networkInfo!= null){
            return  true;
        }else {
            return false;
        }
    }
    public void loadData (View view){
        //
        if (isOnLine()){
            MiTask task = new MiTask();
            task.execute("https://jsonplaceholder.typicode.com/comments");

        }else{
            Toast.makeText(this,"sin conexion",Toast.LENGTH_SHORT).show();
        }

    }

    public void  processData(){
        commentAdapter= new CommentAdapter(commentList,getApplicationContext());
        recyclerView.setAdapter(commentAdapter);

    }


    public class MiTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

           String contend = null;
            try {
                contend= HttpManager.getData(params [0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return contend;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                commentList= Json.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressBar.setVisibility(View.GONE);
            processData();
        }
    }
}
