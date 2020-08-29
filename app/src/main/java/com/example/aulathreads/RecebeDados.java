package com.example.aulathreads;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RecebeDados extends AsyncTask<String, Integer, String>{

    private ProgressBar barra;
    private TextView usuario;
    private TextView id;
    private TextView titulo;
    private TextView corpo;


    public RecebeDados(ProgressBar barra,TextView usuario
            ,TextView id,TextView titulo,TextView corpo){
        this.barra = barra;
        this.usuario = usuario;
        this.id = id;
        this.titulo = titulo;
        this.corpo = corpo;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        barra.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            //URL para acesso
            URL url = new URL(strings[0]);
            //Atualizar Progresso
            publishProgress(1);
            Thread.sleep(1000);
            //Abrir a conexão
            HttpURLConnection conexao =(HttpURLConnection) url.openConnection();
            //Tipo de requisição
            conexao.setRequestMethod("GET");
            //Tipo de retorno
            conexao.setRequestProperty("Accept","aplication/json");
            //Tempo para fazer a conexão
            conexao.setConnectTimeout(5000);
            //Conectar
            conexao.connect();

            //atualizar Progresso
            publishProgress(2);

            Thread.sleep(1000);
            //Devolve em Byte
            InputStream inputStream = conexao.getInputStream();

            //Conversão de Byte para Caracteres
            InputStreamReader reader = new InputStreamReader(inputStream);

            //Ler o caracter
            BufferedReader buffer = new BufferedReader(reader);

            //atualizar Progresso
            publishProgress(3);
            Thread.sleep(1000);

            String linha = "";

            StringBuffer stringBuffer = new StringBuffer();

             while((linha = buffer.readLine()) != null){
                stringBuffer.append(linha);
                stringBuffer.append("\n");
             }
             //atualizar progresso
            publishProgress(4);
            Thread.sleep(1000);

             return stringBuffer.toString();

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        barra.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Log.i("Retorno",s);

        //--------------------------- Objeto Json -----------------------------

       /* int retornoUserId = 0;
        int retornoId = 0;

        String retornoTitle = "";
        String retornoBody = "";

        try {
            //Transformando a string s em objeto Json
            JSONObject json = new JSONObject(s);

            //Recuperando os dados
            retornoUserId = json.getInt("userId");
            retornoId = json.getInt("id");
            retornoTitle = json.getString("title");
            retornoBody = json.getString("body");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Inserindo os resultados no TextView

        usuario.setText(String.valueOf(retornoUserId));
        id.setText(String.valueOf(retornoId));
        titulo.setText(retornoTitle);
        corpo.setText(retornoBody);
        */
        //--------------------------------------------------------------

        //--------------------------- Objeto Gson -----------------------------
        //Inserindo os resultados no TextView

        Post objetoPost = new Gson().fromJson(s,Post.class);


        usuario.setText(String.valueOf(objetoPost.getId()));
        id.setText(String.valueOf(objetoPost.getId()));
        titulo.setText(objetoPost.getTitle());
        corpo.setText(objetoPost.getBody());

        //Escondendo a barra
        barra.setVisibility(View.GONE);
    }
}
