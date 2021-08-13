package etec.com.ewen.matheus.appcameragaleria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MostrarImagens extends AppCompatActivity {

    ArrayList<Fotos> listaFotos;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_imagens);

        viewPager = findViewById(R.id.view_pager);

    }

    @Override
    protected void onStart() { //o array lista vai ser criado aqui dentro
        super.onStart();

        //Acessar o banco de dados
        BDImagens banco = new BDImagens(MostrarImagens.this);
        //receber o caminho das fotos armazenadas no banco
        listaFotos = banco.consultarFotos();

        if (!listaFotos.isEmpty()){
            int tamanho = listaFotos.size();
            String[] fotosArmazenadas = new String[tamanho];

            for (int i = 0; i < listaFotos.size(); i++) {
                fotosArmazenadas[i] = listaFotos.get(i).getCaminho();
            }

            ViewPagerAdapter adapter = new ViewPagerAdapter(this, fotosArmazenadas);
            viewPager.setAdapter(adapter);

        }else{
            Toast.makeText(MostrarImagens.this, "Sem Imagens Armazenadas!", Toast.LENGTH_SHORT).show();
        }
    }
}