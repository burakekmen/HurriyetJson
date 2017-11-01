package kodluyoruz.com.hurriyetjson;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView haberResim;
    TextView haberBaslik;
    TextView haberIcerik;

    public ViewHolder(final View itemView) {
        super(itemView);


        //Burada yukarıda tanımladıgım ve layout dosyamda olan ogelere erisiyorum
        haberResim = (ImageView) itemView.findViewById(R.id.haberResim);
        haberBaslik = (TextView) itemView.findViewById(R.id.haberBaslik);
        haberIcerik = (TextView) itemView.findViewById(R.id.haberIcerik);


    }


    @Override
    public void onClick(View view) {



        //Adaptörün pozisyonunu aldım.Hangi cardview'a tıklandıgını anlayıp ona gore islem yaptırıyorum
        int position = getAdapterPosition();

        switch (position) {
            case 0:
//                Intent niyet0 = new Intent(view.getContext(), Anasayfa.class);
//                view.getContext().startActivity(niyet0);
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:


                break;
            case 4:

                break;

        }
    }
}
