package com.example.aulathreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ProgressBar barra;
    private TextView usuario;
    private TextView id;
    private TextView titulo;
    private TextView corpo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barra = findViewById(R.id.progressBar);
        barra.setVisibility(View.GONE);

        usuario = findViewById(R.id.textUsuario);
        id = findViewById(R.id.textId);
        titulo = findViewById(R.id.textTitulo);
        corpo = findViewById(R.id.textCorpo);
    }

    public void iniciarTarefa(View view) {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    public void iniciarConsulta(View view) {
        RecebeDados recebeDados = new RecebeDados(barra,usuario,id,titulo,corpo);
        // a forma comentaada posso concatenar com o que eu pedir pro usuario o CEP por exemplo
        //String url = "https://jsonplaceholder.typicode.com/posts/1" + variavel;
        String url = "https://jsonplaceholder.typicode.com/posts/3";
        recebeDados.execute(url);
       /* String retorno = "";
        try {
             retorno = recebeDados.execute().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        resultado.setText(retorno);
    }*/
    }
}