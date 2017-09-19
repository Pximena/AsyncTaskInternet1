package com.example.aula7.asynctaskinternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar= (ProgressBar) findViewById(R.id.id_pb_1);
        button= (Button) findViewById(R.id.btn_1);
        textView=(TextView) findViewById(R.id.id_tv_1);
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
            task.execute("https://jsonplaceholder.typicode.com/posts");

        }else{
            Toast.makeText(this,"sin conexion",Toast.LENGTH_SHORT).show();
        }

    }

    public void  processData(String s){
        textView.append("item" + s +" \n");
        textView.setTextSize(Integer.parseInt(s));
    }


    public class MiTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
           /* for(int i=1; i<50; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(String.valueOf(i));
            }
            return null;*/
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
            processData(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            processData(s);
        }
    }
}
