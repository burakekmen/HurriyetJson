package kodluyoruz.com.hurriyetjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    RecyclerView recylerView;
    private String TAG = "JsonArrayRequest";
    TextView tv1, tv2, tv3, tv4;
    ImageView iv;
    String detaylink = "";
    String detaylink2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        iv = (ImageView) findViewById(R.id.iv);
        recylerView=(RecyclerView)findViewById(R.id.recylerView);

        jsonStringRequest();

    }

    //Normal Çekme Loga Bastırdım
    private void jsonArrayRequest() {


        String url = "https://api.hurriyet.com.tr/v1/articles?apikey=fc07f48ba2f14e7a9431c53c8c0372cd&$top=20";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.e("Json Array : ", response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.e(MainActivity.this.TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(req, TAG);
    }


    private void jsonStringRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        String tag_string_req = "JsonStringRequest";

        final String url = "https://api.hurriyet.com.tr/v1/articles?apikey=fc07f48ba2f14e7a9431c53c8c0372cd&$top=2";

        final ProgressDialog pDialog = new ProgressDialog(this);
        //  pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                /*
                * Gson Samples
                * */

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();


                List<InfoViewModel> liste = Arrays.asList(gson.fromJson(response, InfoViewModel[].class));





                Adapter adapter=new Adapter(liste,MainActivity.this);
                recylerView.setAdapter(adapter);



                // Toast.makeText(MainActivity.this, listem.get(0).getTitle(), Toast.LENGTH_SHORT).show();

                //Gelen Verileri Model Dosyasına Yaz

//               for (int i=0;i<listem.size();i++){
//                   listem.add(listem.get(i));
//               }



//resimler File icinde oldugu icin listeyi cektim
                List<File> files = liste.get(0).getFiles();
//Listeden resimi cektim
                String resim = files.get(0).getFileUrl();

//                Picasso.with(MainActivity.this)
//                        .load(resim)
//                        .resize(500, 300)
//                        .into(iv);


              //  tv1.setText(listem.get(0).getTitle().toString());

                //tv2.setText(listem.get(0).getDescription() + "Devamı");

                // tv3.setText(listem.get(0).getUrl().toString());

               // detaylink = listem.get(0).getUrl().toString();
                //detaylink2 = listem.get(1).getUrl().toString();


                //Detay Activity'Linki Gönderiyorum


                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetayActivity.class);

                        intent.putExtra("link", detaylink);

                        startActivity(intent);


                    }
                });


                tv4.setText(detaylink2);
/*
              for (int i=0;i<listem.size();i++){
                    Log.e("aa",listem.get(i).getTitle().toString());
                }
*/
/*
                Log.e("Json String", response.toString());
                pDialog.hide();
*/
                pDialog.dismiss();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.e(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {

            //encoding ayarlamak icin yazilmali. S
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {

                String charset = "UTF-8";
                String parsed;
                try {
                    if (charset != null) {
                        parsed = new String(response.data, charset);
                    } else {
                        parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    }
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
