package kodluyoruz.com.hurriyetjson;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    //singleton pattern
    private static AppController mInstance;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    //requesti kuyruga ekler. Asagidaki methodlar icinde kullanilir.
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    //image requestleri olusturmak icin ozel yapi.
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    //requesti kuyruga eklemek icin kullanilacak. Iptal etmek istenirse tag parametresi verilir.
    public <T> void addToRequestQueue(Request<T> req, String tag) {

        //tag bos ise default tag degeri verilir.
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    //Üstteki method ile aynı islem requesti kuyruga eklemek icin kullanilacak. Iptal etmek istenirse defaul tag verilir.
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    //verilen tag'e gore requesti iptal eder. Activity olurse request otomatik iptal olur.
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}