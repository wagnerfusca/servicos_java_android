package br.edu.fcv.lembreteandroid;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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

	private static final String HOST = "SEUSITE.herokuapp.com";
	private static final String PATH = "/rest/lembrete/";
	private static final String LISTAR = "listar/";
	private static final String INSERIR = "salvar/";

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.cadastro);

		Button btnListar = (Button) findViewById(R.id.btnListar);
		btnListar.setOnClickListener(this);

		Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
		btnAdicionar.setOnClickListener(this);
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
		EditText textLembrete = (EditText) findViewById(R.id.lembrete);

		final TextView textResultado = (TextView) findViewById(R.id.resultado);

		String novoPath = PATH;
		String resultado = "";
		try {
			switch (idBotaoSelecionado) {
			case R.id.btnListar:
				novoPath = novoPath + LISTAR;
				break;
			case R.id.btnAdicionar:
				novoPath = novoPath + INSERIR + textLembrete.getText().toString();

				break;
			default:
				break;
			}
			resultado = converse(HOST, 80, novoPath);
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
