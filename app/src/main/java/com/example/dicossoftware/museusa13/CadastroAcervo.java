package com.example.dicossoftware.museusa13;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroAcervo extends AppCompatActivity {
    private SQLiteDatabase b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_acervo);

            b = this.openOrCreateDatabase("baseMuseu", Context.MODE_PRIVATE, null);

            b.execSQL("CREATE table if not exists  artefatos (id_mom INTEGER PRIMARY KEY AUTOINCREMENT, int varchar(60))");

            carregarTabela();

        }

        public void adicionar(View v) {
            EditText et = (EditText) findViewById(R.id.etNomeAr);

            b.execSQL("INSERT INTO frase(texto) values('" + et.getText().toString() + "')");
            carregarTabela();
        }


        public void apagarTudo(View v) {
            b.execSQL("delete FROM artefatos;");
            carregarTabela();
        }

        public void carregarTabela() {
            Cursor c = b.rawQuery("SELECT id_mom, texto FROM artefatos;", new String[]{});


            while (c.moveToNext()) {

                TextView textView = new TextView(this);
                textView.setText(c.getString(0) + ", " + c.getString(1));

            }

            c.close();
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.acervo:
                intent = new Intent(this, AcervoActivity.class);
                startActivity(intent);
                break;

            case R.id.sobre:
                intent = new Intent(this, SobreActivity.class);
                startActivity(intent);
                break;

            case R.id.contato:
                intent = new Intent(this, ContatoActivity.class);
                startActivity(intent);
                break;

            case R.id.mAdm:
                intent = new Intent(this, CadastroAcervo.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}