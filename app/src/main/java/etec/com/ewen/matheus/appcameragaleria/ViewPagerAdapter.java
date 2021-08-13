package etec.com.ewen.matheus.appcameragaleria;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private String[] urlImagens;

    public ViewPagerAdapter(Context context, String[] urlImagens) {
        this.context = context;
        this.urlImagens = urlImagens;
    }

    @Override
    public int getCount() {
        return urlImagens.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Constroi a imagem a partir do endereço URL
        ImageView imageView = new ImageView(context);
        Picasso.get()
                .load(urlImagens[position])
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //PARA EVITAR USO EXCESSIVO DE MEMORIA A IMAGEM SERÁ DESTRUIDA NO CELULAR NÃO NO FIREBASE
        container.removeView((View) object);
    }
}
