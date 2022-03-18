package com.nupen.hapa10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GirisActivity extends AppCompatActivity {

    EditText kullaniciAdiEditText;
    Button kayitOlButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    Button uygulamabilgisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        tanimla();
    }

    public void ButtonClicked(View view){
        Intent intentToI= new Intent(GirisActivity.this,infoActivity.class);
        startActivity(intentToI);
        finish();
    }




    public void tanimla ()
    {
        kullaniciAdiEditText = (EditText) findViewById(R.id.kullaniciAdiEditText);
        kayitOlButton = (Button) findViewById(R.id.kayitOlButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        uygulamabilgisi = (Button) findViewById(R.id.uygulamabilgisi);

        kayitOlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = kullaniciAdiEditText.getText().toString();
                kullaniciAdiEditText.setText("");
                ekle(username);
            }
        });
    }

    public void ekle(final String kadi)
    {
        reference.child("Kullanıcılar").child(kadi).child("kullaniciadi").setValue(kadi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Başarı ile giriş yaptınız", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GirisActivity.this,MainActivity.class);
                    intent.putExtra("kadi", kadi);
                    startActivity(intent);
                }
            }
        });








    }
}