package etec.com.ewen.matheus.appcameragaleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class TelaImagem extends AppCompatActivity {

    ImageView imgRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_imagem);

        imgRecebida = findViewById(R.id.imgRecebida);

        Intent it = getIntent();
        Bundle imagemFrag = it.getExtras();

        byte[] arrayImagem = imagemFrag.getByteArray("imagem");
        //imagem recontruida
        Bitmap bmp = BitmapFactory.decodeByteArray(arrayImagem, 0, arrayImagem.length);
        imgRecebida.setImageBitmap(bmp);
    }
}