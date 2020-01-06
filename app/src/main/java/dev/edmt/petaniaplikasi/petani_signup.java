package dev.edmt.petaniaplikasi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.edmt.petaniaplikasi.model.sign_up;



/**
 * Created by Herdi_WORK on 08.08.17.
 */

public class petani_signup extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etNama;
    private EditText etMerk;
    private EditText etHarga;
    private TextView login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petani_signup_activity);

        // inisialisasi fields EditText dan Button
        etNama = (EditText) findViewById(R.id.et_namabarang);
        etMerk = (EditText) findViewById(R.id.et_merkbarang);
        etHarga = (EditText) findViewById(R.id.et_hargabarang);
        btSubmit = (Button) findViewById(R.id.bt_submit);


        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final sign_up signup = (sign_up) getIntent().getSerializableExtra("data");

        if (signup != null) {
            etNama.setText(signup.getNama());//mengisi nilai edit text
            etMerk.setText(signup.getNik());
            etHarga.setText(signup.getNohp());
            btSubmit.setOnClickListener(new View.OnClickListener() { // ketika tombol button diklik
                @Override
                public void onClick(View view) {
                    signup.setNama(etNama.getText().toString());
                    signup.setNik(etMerk.getText().toString());
                    signup.setNohp(etHarga.getText().toString());

                }
            });
        } else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmpty(etNama.getText().toString()) && !isEmpty(etMerk.getText().toString()) && !isEmpty(etHarga.getText().toString()))
                        submitBarang(new sign_up(etNama.getText().toString(), etMerk.getText().toString(), etHarga.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_submit), "Data signup tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etNama.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s){
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void submitBarang(sign_up signup) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("DAFTAR-LIST-PETANI-MENDAFTAR").push().setValue(signup).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNama.setText("");
                etMerk.setText("");
                etHarga.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, petani_signup.class);
    }
    public void login(View view) {

        Intent intent = new Intent(petani_signup.this, dev.edmt.petaniaplikasi.petani_login.class);
        startActivity(intent);

    }
}


