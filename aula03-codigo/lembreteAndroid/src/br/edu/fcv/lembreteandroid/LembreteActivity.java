package br.edu.fcv.lembreteandroid;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LembreteActivity extends Activity implements OnClickListener, Runnable {
	private static final String CATEGORIA = "lembrete";
	private Handler handler = new Handler();
	private ProgressDialog dialog;
	private int idBotaoSelecionado;

	private static final String HOST = "projetowagnerfusca.herokuapp.com";
	private static final String PATH = "/rest/lembrete/";
	private static final String LISTAR = "listar/";
	private static final String INSERIR = "salvar/";
	private static final String JSON = "listajson/";
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.cadastro);


		Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
		btnAdicionar.setOnClickListener(this);

		Button btnJson = (Button) findViewById(R.id.btnJson);
		btnJson.setOnClickListener(this);

	}

	public void onClick(View view) {
		dialog = ProgressDialog
				.show(this,
						"Loading...",
						"Acessando base de dados com web service, por favor aguarde...",
						false, true);

		idBotaoSelecionado = view.getId();
		new Thread(this).start();
	}

	public void run() {
		EditText oqueEmprestei = (EditText) findViewById(R.id.oqueemprestei);
		EditText quemPegouEmprestado = (EditText) findViewById(R.id.pegouemprestado);
		final TextView textResultado = (TextView) findViewById(R.id.resultado);

		String novoPath = PATH;
		String resultado = "";
		try {
			switch (idBotaoSelecionado) {
			
			case R.id.btnAdicionar:
				novoPath = novoPath + INSERIR + oqueEmprestei.getText().toString()+"/"+quemPegouEmprestado.getText().toString();
				break;
			case R.id.btnJson:
				novoPath = novoPath + JSON;
				break;				
			default:
				break;
			}
			resultado = converse(HOST, 80, novoPath);
			if (idBotaoSelecionado == R.id.btnJson){
				JSONObject root = new JSONObject(resultado);
				JSONArray lembretes = root.getJSONArray("lembrete"); 
				for (int i = 0; i < lembretes.length(); i++) {
                    JSONObject c = lembretes.getJSONObject(i);
                    String id = c.getString("id");
                   
                    String oqueEmprestei2 = c.getString("descricao");
                    Log.i(CATEGORIA, id+"-"+oqueEmprestei2);
				}
				
			}
			textResultado.setText("Resultado: " + resultado);
			Log.i(CATEGORIA, String.valueOf(resultado));
					
			handler.post(new Runnable() {
				public void run() {
					textResultado.setVisibility(View.VISIBLE);
				}
			});
		} catch (Exception e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		} finally {
			dialog.dismiss();
		}
	}

	public static String converse(String host, int port, String path)
			throws IOException {
		HttpHost target = new HttpHost(host, port);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(path);
		HttpEntity results = null;

		try {
			HttpResponse response = client.execute(target, get);
			results = response.getEntity();
			return EntityUtils.toString(results);
		} catch (Exception e) {
			Log.e(CATEGORIA,e.getMessage(), e);
			throw new RuntimeException("Nao encontrou o webservice "  + e.getMessage());
		} finally {
			if (results != null) {
				try {
					results.consumeContent();
				} catch (IOException e) {

				}
			}
		}

	}

}
