package com.example.appmatricula;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainImprimir extends AppCompatActivity implements View.OnClickListener {

    // Declaración
    TextView tvAlumnoR, tvEscuelaR, tvCarreraR, tvGastosAdicionalesR, tvMontoGastosR, tvNumeroCuotasR, tvCostoCarreraR, tvPensionR, tvGastoAdicionalR, tvTotalPagarR;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprimir);

        // Referenciar
        tvAlumnoR = (TextView) findViewById(R.id.tvAlumnoR);
        tvEscuelaR = (TextView) findViewById(R.id.tvEscuelaR);
        tvCarreraR = (TextView) findViewById(R.id.tvCarreraR);
        tvGastosAdicionalesR = (TextView) findViewById(R.id.tvGastosAdicionalesR);
        tvMontoGastosR = (TextView) findViewById(R.id.tvMontoGastosR);
        tvNumeroCuotasR = (TextView) findViewById(R.id.tvNumeroCuotasR);
        tvCostoCarreraR = (TextView) findViewById(R.id.tvCostoCarreraR);
        tvPensionR = (TextView) findViewById(R.id.tvPensionR);
        tvGastoAdicionalR = (TextView) findViewById(R.id.tvGastoAdicionalR);
        tvTotalPagarR = (TextView) findViewById(R.id.tvTotalPagarR);

        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);

        mostrarDatos();

    }

    void mostrarDatos() {

        // Obtener el Intent
        Bundle datos = this.getIntent().getExtras();

        tvAlumnoR.setText("ALUMNO: " + datos.getString("alumno"));
        tvEscuelaR.setText("ESCUELA: " + datos.getString("escuela"));
        tvCarreraR.setText("CARRERA: " + datos.getString("carrera"));
        tvGastosAdicionalesR.setText("GASTOS ADICIONALES: " + datos.getString("adicionales"));
        tvMontoGastosR.setText("MONTO GASTOS ADICIONALES: " + datos.getInt("numeroAdicionales"));
        tvNumeroCuotasR.setText("NÚMERO CUOTAS: " + datos.getInt("cuotas"));
        tvCostoCarreraR.setText("COSTO CARRERA: S/. " + datos.getDouble("costoCarrera"));
        tvPensionR.setText("PENSIÓN: S/. " + datos.getDouble("pension"));
        tvGastoAdicionalR.setText("GASTOS ADICIONALES: S/. " + datos.getDouble("costoAdicional"));
        tvTotalPagarR.setText("TOTAL PAGAR: S/. " + datos.getDouble("totalPagar"));
    }

    @Override
    public void onClick(View v) {
        if(v == btnVolver) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}