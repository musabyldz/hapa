package com.nupen.hapa10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<String> list;
    String userName;
    RecyclerView userRecyView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        listele();
        auth= FirebaseAuth.getInstance();
    }
    public void LogOutClicked(View view){
        if (view.getId()== R.id.buttonCikis){
            auth.signOut();

            Intent intent=new Intent(MainActivity.this,GirisActivity.class);
            startActivity(intent);
            finish();

            Toast.makeText(MainActivity.this,"Başarıyla Çıkış Yaptınız",Toast.LENGTH_LONG).show();
        }
        }



    public void tanimla()
    {
        userName = getIntent().getExtras().getString("kadi");
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        list = new ArrayList<>();
        userRecyView = (RecyclerView)findViewById(R.id.userRecyView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        userRecyView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(MainActivity.this,list,MainActivity.this, userName);
        userRecyView.setAdapter(userAdapter);
    }



    public void listele()
    {
        reference.child("Kullanıcılar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (!snapshot.getKey().equals(userName)) {
                    list.add(snapshot.getKey());
                    Log.i("Kullanıcı",snapshot.getKey());
                    userAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}