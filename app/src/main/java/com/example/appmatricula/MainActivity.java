package com.example.appmatricula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Declaración
    EditText edtAlumno;
    Spinner spnEscuela, spnCarrera;
    CheckBox chkBiblioteca, chkMedio;
    RadioButton rbtn4, rbtn5, rbtn6;
    TextView tvCostoCarrera, tvPension, tvGastosAdicionales, tvTotalPagar;
    Button btnCalcular, btnImprimir;

    int numeroCuota, numeroAdicionales;
    Double costoCarrera, valorCuota, costoPension, costoAdicional, totalPagar;
    String nomEscuela, nomCarrera, nomAdicional;

    // Esta línea da error
    //List<String> lista = Arrays.asList(getResources().getStringArray(R.array.carreras_ti));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias
        edtAlumno = (EditText) findViewById(R.id.edtAlumno);
        spnEscuela = (Spinner) findViewById(R.id.spnEscuela);
        spnCarrera = (Spinner) findViewById(R.id.spnCarrera);
        chkBiblioteca = (CheckBox) findViewById(R.id.chkBiblioteca);
        chkMedio = (CheckBox) findViewById(R.id.chkMedio);
        rbtn4 = (RadioButton) findViewById(R.id.rbtn4);
        rbtn5 = (RadioButton) findViewById(R.id.rbtn5);
        rbtn6 = (RadioButton) findViewById(R.id.rbtn6);
        tvCostoCarrera = (TextView) findViewById(R.id.tvCostoCarrera);
        tvPension = (TextView) findViewById(R.id.tvPension);
        tvGastosAdicionales = (TextView) findViewById(R.id.tvGastosAdicionales);
        tvTotalPagar = (TextView) findViewById(R.id.tvTotalPagar);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnImprimir = (Button) findViewById(R.id.btnImprimir);

        // Evento click en botones
        btnCalcular.setOnClickListener(this);
        btnImprimir.setOnClickListener(this);

        // ¡Funciona! Carga el spinner principal
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.escuela, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEscuela.setAdapter(adapter);

        spnEscuela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos;
                pos = spnEscuela.getSelectedItemPosition();

                if(pos == 1) {
                    ArrayAdapter<CharSequence> adapterTI = ArrayAdapter.createFromResource(getApplicationContext(), R.array.carreras_ti, android.R.layout.simple_spinner_item);
                    adapterTI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnCarrera.setAdapter(adapterTI);

                    nomEscuela = spnEscuela.getSelectedItem().toString();

                }else if(pos == 2) {
                    ArrayAdapter<CharSequence> adapterGN = ArrayAdapter.createFromResource(getApplicationContext(), R.array.carreras_gn, android.R.layout.simple_spinner_item);
                    adapterGN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnCarrera.setAdapter(adapterGN);

                    nomEscuela = spnEscuela.getSelectedItem().toString();
                }else {
                    spnCarrera.setAdapter(null);
                    tvCostoCarrera.setText("S/. ");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int posEscuela;
                posEscuela = spnEscuela.getSelectedItemPosition();

                int posCarrera;
                posCarrera = spnCarrera.getSelectedItemPosition();

                if(posEscuela == 1) {
                    switch (posCarrera) {
                        case 0:
                            costoCarrera = (double) 3200;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        case 1:
                            costoCarrera = (double) 3100;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        case 2:
                            costoCarrera = (double) 3000;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        default:
                            costoCarrera = (double) 0;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                    }

                    tvCostoCarrera.setText("S/. " + costoCarrera);

                }else if(posEscuela == 2) {
                    switch (posCarrera) {
                        case 0:
                            costoCarrera = (double) 2800;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        case 1:
                            costoCarrera = (double) 2700;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        case 2:
                            costoCarrera = (double) 2600;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        case 3:
                            costoCarrera = (double) 2650;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        case 4:
                            costoCarrera = (double) 2750;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        case 5:
                            costoCarrera = (double) 2550;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                            break;
                        default:
                            costoCarrera = (double) 0;
                            nomCarrera = spnCarrera.getSelectedItem().toString();
                    }

                    tvCostoCarrera.setText("S/. " + costoCarrera);

                }else {
                    tvCostoCarrera.setText("S/. ");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == btnCalcular) {

             /* En caso no seleccione la escuela, no se tiene costo y la app se cae.
                   Como medida preventiva, lanzo un Toast para controlarlo. */

            if(spnEscuela.getSelectedItemPosition() == 0) {
                Toast.makeText(MainActivity.this, "Seleccione una Escuela", Toast.LENGTH_SHORT).show();
            }else {
                calcularAdicionales();
                calcularPension();
                calcularTotal();
            }
        }else {
            // btnImprimir
            String nomAlumno = edtAlumno.getText().toString();

            // Crear objeto de la lase Intent
            Intent intent = new Intent(this, MainImprimir.class);

            // Crear claves dentro del objeto Intent
            intent.putExtra("alumno", nomAlumno);
            intent.putExtra("escuela", nomEscuela);
            intent.putExtra("carrera", nomCarrera);
            intent.putExtra("adicionales", nomAdicional);
            intent.putExtra("numeroAdicionales", numeroAdicionales);
            intent.putExtra("cuotas", numeroCuota);
            intent.putExtra("costoCarrera", costoCarrera);
            intent.putExtra("pension", costoPension);
            intent.putExtra("costoAdicional", costoAdicional);
            intent.putExtra("totalPagar", totalPagar);

            // Direccionar a la actividad MainImprimir;
            startActivity(intent);

        }
    }

    void calcularPension() {
        if(rbtn5.isChecked()) {
            valorCuota = 0.12;
            numeroCuota = 5;
        }else if(rbtn6.isChecked()) {
            valorCuota = 0.16;
            numeroCuota = 6;
        }else {
            valorCuota = (double) 0;
            numeroCuota = 4;
        }

        costoPension = (costoCarrera + (costoCarrera * valorCuota)) / (double) 5;
        tvPension.setText("S/. " + costoPension);

    }

    void calcularAdicionales() {
        if(chkBiblioteca.isChecked() && chkMedio.isChecked()) {
            costoAdicional = (double) (25 + 22);
            nomAdicional = "Carnet Biblioteca y Carnet Medio Pasaje";
            numeroAdicionales = 2;
        }else {
            if(chkBiblioteca.isChecked()) {
                costoAdicional = (double) 25;
                nomAdicional = "Carnet Biblioteca";
                numeroAdicionales = 1;
            }else if(chkMedio.isChecked()) {
                costoAdicional = (double) 22;
                nomAdicional = "Carnet Medio Pasaje";
                numeroAdicionales = 1;
            }else {
                costoAdicional = (double) 0;
                nomAdicional = "Ninguno";
                numeroAdicionales = 0;
            }
        }

        tvGastosAdicionales.setText("S/. "+costoAdicional);
    }

    void calcularTotal() {
        totalPagar = (costoCarrera + (costoCarrera * valorCuota)) + costoAdicional;
        tvTotalPagar.setText("S/. " + totalPagar);
    }

}

// Intentos fallidos con el spinner
// Evento item selected de Spinner
        /*
        spnEscuela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int posEscuela;
                posEscuela = spnEscuela.getSelectedItemPosition();
                if(posEscuela == 0) {
                    System.out.println("Prueba 0");
                }else if(posEscuela == 1) {
                    System.out.println("Prueba 1");
                }else if(posEscuela == 2) {
                    System.out.println("Prueba 2");
                }

                /*
                List<String> listaTI = Arrays.asList(getResources().getStringArray(R.array.escuela_ti));
                //String[] lista = getResources().getStringArray(R.array.escuela_ti);
                ArrayAdapter<String> adapterTI = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, listaTI);

                List<String> listaGN = Arrays.asList(getResources().getStringArray(R.array.escuela_gn));
                ArrayAdapter<String> adapterGN = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, listaGN);

                switch(posEscuela) {
                    case 1:

                        adapterTI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spnCarrera.setAdapter(adapterTI);
                        adapterTI.notifyDataSetChanged();

                        tvTotalPagar.setText("TI");
                        break;
                    case 2:

                        adapterGN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spnCarrera.setAdapter(adapterGN);
                        adapterGN.notifyDataSetChanged();
                        tvTotalPagar.setText("GN");
                        break;
                    default:
                        tvTotalPagar.setText("Nulo");
                }


            }
            */
    /*
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
//spnCarrera.setOnItemSelectedListener(this);
        /*
        List<String> listaTI = Arrays.asList(getResources().getStringArray(R.array.carreras_ti));
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, listaTI);
        spnEscuela.setAdapter(adapter);

        spnEscuela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(1) {
                    case 1:
                        tvTotalPagar.setText("Uno");
                        break;
                    case 2:
                        tvTotalPagar.setText("Dos");
                        break;
                    default:
                        tvTotalPagar.setText("Default ehe");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         */



/*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int posEscuela, posCarrera;
        posEscuela = spnEscuela.getSelectedItemPosition();
        posCarrera = spnCarrera.getSelectedItemPosition();

        if(posEscuela == 0){
            //spnCarrera.setAdapter(null);
        }
        else if(posEscuela == 1) {
            //tvTotalPagar.setText("Prueba");
            //spnCarrera.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.escuela_ti)));

            /*
            List<String> lista = Arrays.asList(getResources().getStringArray(R.array.escuela_ti));
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAdapter.add("");
            spnCarrera.setAdapter(spinnerAdapter);
            spinnerAdapter.notifyDataSetChanged();
*/

            /*
            String [] array_ti = {"Computación e informática", "Administración de redes y comunicaciones", "Administración y sistemas", "Industrial y sistemas"};

            ArrayAdapter dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_ti);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnCarrera.setAdapter(dataAdapter);


            adapter.setNotifyOnChange(true);
            adapter.add(array_ti);
            //adapter.notifyDataSetChanged();
*/
/*
        }else if(posEscuela == 2) {
            //spnCarrera.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.escuela_gn)));
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
 */