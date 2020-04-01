package com.victorsystems.seminariogamification;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Activity mActivity;
    private FirebaseDatabase fbDatabase;

    EditText txtFlorA, txtFlorP, txtFlorB, txtFlorC;
    EditText txtAlanisA, txtAlanisP, txtAlanisB, txtAlanisC;
    EditText txtWellingtonA, txtWellingtonP, txtWellingtonB, txtWellingtonC;
    EditText txtStarlingA, txtStarlingP, txtStarlingB, txtStarlingC;
    EditText txtNicoleA, txtNicoleP, txtNicoleB, txtNicoleC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActivity = this;
        fbDatabase = FirebaseDatabase.getInstance();

        txtFlorA = (EditText)findViewById(R.id.txtAsistenciaFlor);
        txtFlorP = (EditText)findViewById(R.id.txtParticipacionFlor);
        txtFlorB = (EditText)findViewById(R.id.txtBonusFlor);
        txtFlorC = (EditText)findViewById(R.id.txtConsumoFlor);

        txtAlanisA = (EditText)findViewById(R.id.txtAsistenciaAlanis);
        txtAlanisP = (EditText)findViewById(R.id.txtParticipacionAlanis);
        txtAlanisB = (EditText)findViewById(R.id.txtBonusAlanis);
        txtAlanisC = (EditText)findViewById(R.id.txtConsumoAlanis);

        txtWellingtonA = (EditText)findViewById(R.id.txtAsistenciaWellington);
        txtWellingtonP = (EditText)findViewById(R.id.txtParticipacionWellington);
        txtWellingtonB = (EditText)findViewById(R.id.txtBonusWellington);
        txtWellingtonC = (EditText)findViewById(R.id.txtConsumoWellington);

        txtStarlingA = (EditText)findViewById(R.id.txtAsistenciaStarling);
        txtStarlingP = (EditText)findViewById(R.id.txtParticipacionStarling);
        txtStarlingB = (EditText)findViewById(R.id.txtBonusStarling);
        txtStarlingC = (EditText)findViewById(R.id.txtConsumoStarling);

        txtNicoleA = (EditText)findViewById(R.id.txtAsistenciaNicole);
        txtNicoleP = (EditText)findViewById(R.id.txtParticipacionNicole);
        txtNicoleB = (EditText)findViewById(R.id.txtBonusNicole);
        txtNicoleC = (EditText)findViewById(R.id.txtConsumoNicole);


        findViewById(R.id.fabSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("Enviar Datos");
                builder.setMessage("¿Esta seguro que desea enviar los datos?");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        setFecha();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        findViewById(R.id.fabClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFlorA.setText("0");
                txtFlorP.setText("0");
                txtFlorB.setText("0");
                txtFlorC.setText("0");

                txtAlanisA.setText("0");
                txtAlanisP.setText("0");
                txtAlanisB.setText("0");
                txtAlanisC.setText("0");

                txtWellingtonA.setText("0");
                txtWellingtonP.setText("0");
                txtWellingtonB.setText("0");
                txtWellingtonC.setText("0");

                txtStarlingA.setText("0");
                txtStarlingP.setText("0");
                txtStarlingB.setText("0");
                txtStarlingC.setText("0");

                txtNicoleA.setText("0");
                txtNicoleP.setText("0");
                txtNicoleB.setText("0");
                txtNicoleC.setText("0");
                showMessage("Textbox's Inicializados");
            }
        });
    }

    public void setFecha() {
        String fs = dateToString(Calendar.getInstance().getTime(), "yyyy-MM-dd");
        DatePickerDialog dialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Date f = new Date((year - 1900), (month), day);
                finishProcess(f);
            }
        }, Integer.parseInt(fs.substring(0,4)), Integer.parseInt(fs.substring(5,7)) - 1, Integer.parseInt(fs.substring(8)));
        dialog.show();
    }

    public void finishProcess(Date fecha) {
        DatabaseReference mRef = fbDatabase.getReference("puntos");
        //Flor
        Puntos flor = new Puntos(mRef.push().getKey(),"FLOR",
                Integer.parseInt(txtFlorA.getText().toString()),
                Integer.parseInt(txtFlorP.getText().toString()),
                Integer.parseInt(txtFlorB.getText().toString()),
                Integer.parseInt(txtFlorC.getText().toString()), fecha);

        mRef.child(flor.getId()).setValue(flor);

        //Alanis
        Puntos alanis = new Puntos(mRef.push().getKey(),"ALANIS",
                Integer.parseInt(txtAlanisA.getText().toString()),
                Integer.parseInt(txtAlanisP.getText().toString()),
                Integer.parseInt(txtAlanisB.getText().toString()),
                Integer.parseInt(txtAlanisC.getText().toString()), fecha);

        mRef.child(alanis.getId()).setValue(alanis);

        //Wellington
        Puntos wellington = new Puntos(mRef.push().getKey(),"WELLINGTON",
                Integer.parseInt(txtWellingtonA.getText().toString()),
                Integer.parseInt(txtWellingtonP.getText().toString()),
                Integer.parseInt(txtWellingtonB.getText().toString()),
                Integer.parseInt(txtWellingtonC.getText().toString()), fecha);

        mRef.child(wellington.getId()).setValue(wellington);

        //Starling
        Puntos starling = new Puntos(mRef.push().getKey(),"STARLING",
                Integer.parseInt(txtStarlingA.getText().toString()),
                Integer.parseInt(txtStarlingP.getText().toString()),
                Integer.parseInt(txtStarlingB.getText().toString()),
                Integer.parseInt(txtStarlingC.getText().toString()), fecha);

        mRef.child(starling.getId()).setValue(starling);

        //Nicole
        Puntos nicole = new Puntos(mRef.push().getKey(),"NICOLE",
                Integer.parseInt(txtNicoleA.getText().toString()),
                Integer.parseInt(txtNicoleP.getText().toString()),
                Integer.parseInt(txtNicoleB.getText().toString()),
                Integer.parseInt(txtNicoleC.getText().toString()), fecha);

        mRef.child(nicole.getId()).setValue(nicole);

        showMessage("Proceso Concluido Exitosamente");
    }

    public void showMessage(String msj) {
        Toast.makeText(mActivity, msj, Toast.LENGTH_LONG).show();
    }

    public String dateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static class Puntos implements Serializable {
        private String id;
        private String nombre;
        private int asistencia;
        private int participacion;
        private int bonus;
        private int consumo;
        private Date fecha;

        public Puntos() {}

        public Puntos(String id, String nombre, int asistencia, int participacion, int bonus, int consumo, Date fecha) {
            this.id = id;
            this.nombre = nombre;
            this.asistencia = asistencia;
            this.participacion = participacion;
            this.bonus = bonus;
            this.consumo = consumo;
            this.fecha = fecha;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getAsistencia() {
            return asistencia;
        }

        public void setAsistencia(int asistencia) {
            this.asistencia = asistencia;
        }

        public int getParticipacion() {
            return participacion;
        }

        public void setParticipacion(int participacion) {
            this.participacion = participacion;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public int getConsumo() {
            return consumo;
        }

        public void setConsumo(int consumo) {
            this.consumo = consumo;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }
    }


}
