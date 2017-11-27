package pt.ubi.di.pdm.tiagomartins;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by TiagoMartins on 26/11/2017.
 */

public class ConsultarPratos extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.consultarpratos);


            AjudanteParaAbrirBD ajudanteBD= new AjudanteParaAbrirBD(this);
            SQLiteDatabase db = ajudanteBD.getWritableDatabase();


            Cursor queryres = ajudanteBD.getPratos(); //query para todos os pratos da bd

            queryres.moveToFirst();

            HashMap<String,String> hash_resultado_bd = new HashMap<>(); //hasmap com dados da query

            final ListView aminhalistview = (ListView) findViewById(R.id.listview);

            while (!queryres.isAfterLast()){


                hash_resultado_bd.put(queryres.getString(0), queryres.getString(2));
                queryres.moveToNext();

            }

            queryres.close();
            db.close();

            //criar uma listview personalizada com 2 xml

            List<HashMap<String,String>> listItens = new ArrayList<>();

            SimpleAdapter adapter = new SimpleAdapter(this, listItens, R.layout.listview_item,
                    new String[]{"First Line", "Second Line"},
                    new int[]{R.id.text1,R.id.subitem1});


            Iterator it = hash_resultado_bd.entrySet().iterator();

            while (it.hasNext()){


                HashMap<String,String> resultado_final = new HashMap<>();
                HashMap.Entry par = (Map.Entry) it.next();
                resultado_final.put("First Line", par.getKey().toString());
                resultado_final.put("Second Line", "Preço: " + par.getValue().toString() + " €");
                listItens.add(resultado_final);



            }

            aminhalistview.setAdapter(adapter);


            aminhalistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    HashMap<String, String> getItemclick = (HashMap<String, String>)  aminhalistview.getItemAtPosition(position);

                    //Log.i("Teste", getItemclick.toString()); ???

                    Set<String> keys = getItemclick.keySet();


                   String nomeprato = getItemclick.get("First Line").toString();

                   //Log.i("Teste", nomeprato);


                    /* ver isto
                    Iterator itr = keys.iterator();
                    String key=null;
                    String nomep=null;

                    while(itr.hasNext()) {

                        key = (String)itr.next();
                        nomep = getItemclick.get(key);


                    }*/



                    Bundle bundle = new Bundle();
                    bundle.putString("nome", nomeprato);
                    Intent showinfoprato = new Intent(getApplicationContext(), InfoPrato.class);
                    showinfoprato.putExtras(bundle); // anexar extras ao intento para ir para a outra view
                    startActivity(showinfoprato);
                }
            });




        }




}
