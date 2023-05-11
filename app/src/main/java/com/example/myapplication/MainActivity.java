package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText etHargaRumah, etUangMuka, etBunga, etTenor;
    private Button btnHitung, btnReset;
    private TextView tvCicilan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHargaRumah = findViewById(R.id.et_harga_rumah);
        etUangMuka = findViewById(R.id.et_uang_muka);
        etBunga = findViewById(R.id.et_bunga);
        etTenor = findViewById(R.id.et_tenor);
        btnHitung = findViewById(R.id.btn_hitung);
        btnReset = findViewById(R.id.btn_reset);
        tvCicilan = findViewById(R.id.tv_hasil);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //deklarasi input
                double pokokPinjaman = Double.parseDouble(etHargaRumah.getText().toString());
                double uangMuka = Double.parseDouble(etUangMuka.getText().toString());
                double bunga = Double.parseDouble(etBunga.getText().toString());
                int tenor = Integer.parseInt(etTenor.getText().toString());

                // hitung cicilan perbulan
                double bungaPinjaman = bunga / 100.0 / 12.0;
                double cicilan = (pokokPinjaman - uangMuka) * bungaPinjaman * Math.pow(1 + bungaPinjaman, tenor) / (Math.pow(1 + bungaPinjaman, tenor) - 1);

                // tampilkan hasil pada TextView
                tvCicilan.setText(String.format("Cicilan per bulan: Rp%,.2f", cicilan));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etHargaRumah.setText("");
                etUangMuka.setText("");
                etBunga.setText("");
                etTenor.setText("");
                tvCicilan.setText("");
                Toast.makeText(MainActivity.this, "Input sudah dibersihkan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean backPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed();
            return;
        }

        backPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressedOnce = false;
            }
        }, 2000);
    }

}
