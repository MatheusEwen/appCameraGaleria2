package etec.com.ewen.matheus.appcameragaleria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imgFoto;
    Button btnEnvia, btnGaleria, btnCamera, btnCancelar;

    Bitmap imagem;

    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;
    private final int CAMERA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFoto = findViewById(R.id.imgFoto);
        btnCamera = findViewById(R.id.btnCamera);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnEnvia = findViewById(R.id.btnEnviar);
        btnGaleria = findViewById(R.id.btnGaleria);

        btnEnvia.setEnabled(false);

        //Verificação de Permissão
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        ){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    || (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))){
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, PERMISSAO_REQUEST);
            }

        }

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(itCamera, CAMERA);
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(itGaleria, GALERIA_IMAGENS);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCamera.setEnabled(true);
                btnGaleria.setEnabled(true);
                btnEnvia.setEnabled(false);

                imgFoto.setImageResource(R.drawable.img);
            }
        });

        btnEnvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarImagem();
            }
        });

    }
    private void enviarImagem(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imagem = Bitmap.createScaledBitmap(imagem, 550, 650, true);
        imagem.compress(Bitmap.CompressFormat.JPEG, 75, baos);
        byte[] imagemEnviada = baos.toByteArray();

        Intent it = new Intent(MainActivity.this, TelaImagem.class);
        it.putExtra("imagem", imagemEnviada);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS){
            //O QUE FAZER COM A IMAGEM ESCOLHIDA DA GALERIA
            Uri imagemSelecionada = data.getData();
            String[] caminhoImagem = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(imagemSelecionada, caminhoImagem, null, null,null);
            c.moveToFirst();

            int indicaColuna = c.getColumnIndex(caminhoImagem[0]);

            String caminho = c.getString(indicaColuna);
            c.close();

            imagem = BitmapFactory.decodeFile(caminho);

            imgFoto.setImageBitmap(imagem);


        } else if(resultCode == RESULT_OK && requestCode == CAMERA) {
            //O QUE FAZER COM A FOTO TIRADA NA CÂMERA
            imagem = (Bitmap) data.getExtras().get("data");
            imgFoto.setImageBitmap(imagem);

        }
        btnEnvia.setEnabled(true);
        btnGaleria.setEnabled(false);
        btnCamera.setEnabled(false);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSAO_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                btnEnvia.setEnabled(true);
                btnCamera.setEnabled(true);
                btnGaleria.setEnabled(true);

            }else {
                btnEnvia.setEnabled(false);
            }
        }

    }
}