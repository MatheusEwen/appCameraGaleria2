package etec.com.ewen.matheus.appcameragaleria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MostrarImagens extends AppCompatActivity {

    //local onde vamos guardar o caminho das imagens
    private String[]urlImagens = new String[]{
            "https://firebasestorage.googleapis.com/v0/b/imagens-b85c0.appspot.com/o/Imagens%2F48efb768-90e5-4afe-9563-581a52915b67.jpeg?alt=media&token=eacf436a-ccce-4cc0-b63c-6fd035b6918c",
            "https://firebasestorage.googleapis.com/v0/b/imagens-b85c0.appspot.com/o/Imagens%2Ff3b4a760-7d91-4545-a823-499e7586d8d4.jpeg?alt=media&token=02300b53-9964-40ca-9fc6-807c83815fcc",
            "https://firebasestorage.googleapis.com/v0/b/imagens-b85c0.appspot.com/o/Imagens%2F8bbc55f4-6f9e-4ce0-9604-48a80b1b989f.jpeg?alt=media&token=1c2de3e5-1aac-4fcd-a53c-fc5c6a1ce1c5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_imagens);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, urlImagens);
        viewPager.setAdapter(adapter);
    }
}