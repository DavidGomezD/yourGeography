package com.davidgd.davidgd.yourgeographycountriesandcapitalsoftheworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.davidgd.davidgd.yourgeographycountriesandcapitalsoftheworld.utilidades.Utilidades;

import java.util.Random;

public class juego extends AppCompatActivity {

    //Evita el doble clic
    private long mLastClickTime = 0;

    //Sonido
    MediaPlayer mperror, mp;

    TextView Estado1, Estado2, Estado3, Capital1, Capital2, Capital3, SeleccionEstado, SeleccionCapital, Califica, Marcador, TotalVidas;
    Button BE1, BE2, BE3, BC1, BC2, BC3;

    //Sirve para identificar cuando se da el primer y segundo click
    Integer AppInicio = 0;
    //Almacena los puntos
    Integer Puntos = 0;
    //Indica cuantas veces ganamos si el valor es 3 se mandara la alerta no hay datos
    Integer SeGano = 0;
    //Indicara el fin del juego
    Integer FinApp = 0;
    Integer Vidas = 3;
    //Sirve para borrar los Estados y Capitales
    String BorrarEstado = "";
    String BorrarCapital = "";
    //Variable aleatoria
    Random aleatorio = new Random();
    //El Arreglo debe de ser mayor en 1 por que el random redondea el ultimo valor
    //Declaracion de los arreglos
    String [] ArregloEstados = new String[257];
    String [] ArregloCapitales = new String[257];
    //Variables de tipo entero llenados por el aleatorio, NOTA: Los aleatorios se repiten
    Integer ale1 = aleatorio.nextInt(ArregloEstados.length-1);
    Integer ale2 = aleatorio.nextInt(ArregloEstados.length-1);
    Integer ale3 = aleatorio.nextInt(ArregloEstados.length-1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Quita las notificaciones, no mover de lugar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_juego);

        //Conexion a los TextView
        Estado1 = (TextView) findViewById(R.id.textView);
        Estado2 = (TextView) findViewById(R.id.textView2);
        Estado3 = (TextView) findViewById(R.id.textView3);

        Capital1 = (TextView) findViewById(R.id.textView4);
        Capital2 = (TextView) findViewById(R.id.textView5);
        Capital3 = (TextView) findViewById(R.id.textView6);

        Marcador = (TextView) findViewById(R.id.textView10);
        TotalVidas = (TextView) findViewById(R.id.txtVidas);

        SeleccionEstado = (TextView) findViewById(R.id.textView7);
        SeleccionCapital = (TextView) findViewById(R.id.textView8);
        Califica = (TextView) findViewById(R.id.textView9);

        //Conexion a los botones
        BE1 = (Button) findViewById(R.id.button13);
        BE2 = (Button) findViewById(R.id.button14);
        BE3 = (Button) findViewById(R.id.button15);

        BC1 = (Button) findViewById(R.id.button16);
        BC2 = (Button) findViewById(R.id.button17);
        BC3 = (Button) findViewById(R.id.button18);

        //Sonido, nunca poner mayusculas en el nombre del sonido
        mperror = MediaPlayer.create(this, R.raw.error);
        mp = MediaPlayer.create(this, R.raw.clic);

        //--------------------Inicia las vidas del juego--------------------

        IniciaVidas();

        //--------------------Llenado de los arreglos--------------------

        LLenaLosArreglos();
        //--------------------Ordenamiento aleatorio de los Estados y capitales--------------------

        OrdenamientoAleatorio();

        //-----------------Programacion de los botones--------------------

        BE1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sonido
                mp.start();
                if (Estado1.getText() == "" || Estado1.getText() == SeleccionEstado.getText()){
                    //Boton sin texto no hacer nada
                }else {
                    //Cambia el color del boton
                    BE1.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_encendido));
                    SeleccionEstado.setText(Estado1.getText().toString());
                    BorrarEstado = SeleccionEstado.getText().toString();
                    calificiar();
                    VerificarTV();
                    FinalizaActivity();
                }
            }
        });

        BE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sonido
                mp.start();
                if (Estado2.getText() == "" || Estado2.getText() == SeleccionEstado.getText()){
                    //Boton sin texto no hacer nada
                }else {
                    //Cambia el color del boton
                    BE2.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_encendido));
                    SeleccionEstado.setText(Estado2.getText().toString());
                    BorrarEstado = SeleccionEstado.getText().toString();
                    calificiar();
                    VerificarTV();
                    FinalizaActivity();
                }
            }
        });

        BE3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sonido
                mp.start();
                if (Estado3.getText() == "" || Estado3.getText() == SeleccionEstado.getText()){
                    //Boton sin texto no hacer nada
                }else {
                    //Cambia el color del boton
                    BE3.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_encendido));
                    SeleccionEstado.setText(Estado3.getText().toString());
                    BorrarEstado = SeleccionEstado.getText().toString();
                    calificiar();
                    VerificarTV();
                    FinalizaActivity();
                }
            }
        });

        BC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sonido
                mp.start();
                if (Capital1.getText() == "" || Capital1.getText() == SeleccionCapital.getText()){
                    //Boton sin texto no hacer nada
                }else {
                    //Cambia el color del boton
                    BC1.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_encendido));
                    SeleccionCapital.setText(Capital1.getText().toString());
                    BorrarCapital = SeleccionCapital.getText().toString();
                    calificiar();
                    VerificarTV();
                    FinalizaActivity();
                }
            }
        });

        BC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sonido
                mp.start();
                if (Capital2.getText() == "" || Capital2.getText() == SeleccionCapital.getText()){
                    //Boton sin texto no hacer nada
                }else {
                    //Cambia el color del boton
                    BC2.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_encendido));
                    SeleccionCapital.setText(Capital2.getText().toString());
                    BorrarCapital = SeleccionCapital.getText().toString();
                    calificiar();
                    VerificarTV();
                    FinalizaActivity();
                }
            }
        });

        BC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sonido
                mp.start();
                if (Capital3.getText() == "" || Capital3.getText() == SeleccionCapital.getText()){
                    //Boton sin texto no hacer nada
                }else {
                    //Cambia el color del boton
                    BC3.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_encendido));
                    SeleccionCapital.setText(Capital3.getText().toString());
                    BorrarCapital = SeleccionCapital.getText().toString();
                    calificiar();
                    VerificarTV();
                    FinalizaActivity();
                }
            }
        });

        //Fin del OnCreate
    }

    //Metodos del programa

    //Califica
    public void calificiar(){

        //Calificar
        if (AppInicio == 0){
            Califica.setText("");
            AppInicio = 1;
        }else {if (SeleccionEstado.getText().toString() == ArregloEstados[0] && SeleccionCapital.getText().toString()== ArregloCapitales[0]
                || SeleccionEstado.getText().toString() == ArregloEstados[1] && SeleccionCapital.getText().toString() == ArregloCapitales[1]
                || SeleccionEstado.getText().toString() == ArregloEstados[2] && SeleccionCapital.getText().toString() == ArregloCapitales[2]
                || SeleccionEstado.getText().toString() == ArregloEstados[3] && SeleccionCapital.getText().toString() == ArregloCapitales[3]
                || SeleccionEstado.getText().toString() == ArregloEstados[4] && SeleccionCapital.getText().toString() == ArregloCapitales[4]
                || SeleccionEstado.getText().toString() == ArregloEstados[5] && SeleccionCapital.getText().toString() == ArregloCapitales[5]
                || SeleccionEstado.getText().toString() == ArregloEstados[6] && SeleccionCapital.getText().toString() == ArregloCapitales[6]
                || SeleccionEstado.getText().toString() == ArregloEstados[7] && SeleccionCapital.getText().toString() == ArregloCapitales[7]
                || SeleccionEstado.getText().toString() == ArregloEstados[8] && SeleccionCapital.getText().toString() == ArregloCapitales[8]
                || SeleccionEstado.getText().toString() == ArregloEstados[9] && SeleccionCapital.getText().toString() == ArregloCapitales[9]
                || SeleccionEstado.getText().toString() == ArregloEstados[10] && SeleccionCapital.getText().toString() == ArregloCapitales[10]
                || SeleccionEstado.getText().toString() == ArregloEstados[11] && SeleccionCapital.getText().toString() == ArregloCapitales[11]
                || SeleccionEstado.getText().toString() == ArregloEstados[12] && SeleccionCapital.getText().toString() == ArregloCapitales[12]
                || SeleccionEstado.getText().toString() == ArregloEstados[13] && SeleccionCapital.getText().toString() == ArregloCapitales[13]
                || SeleccionEstado.getText().toString() == ArregloEstados[14] && SeleccionCapital.getText().toString() == ArregloCapitales[14]
                || SeleccionEstado.getText().toString() == ArregloEstados[15] && SeleccionCapital.getText().toString() == ArregloCapitales[15]
                || SeleccionEstado.getText().toString() == ArregloEstados[16] && SeleccionCapital.getText().toString() == ArregloCapitales[16]
                || SeleccionEstado.getText().toString() == ArregloEstados[17] && SeleccionCapital.getText().toString() == ArregloCapitales[17]
                || SeleccionEstado.getText().toString() == ArregloEstados[18] && SeleccionCapital.getText().toString() == ArregloCapitales[18]
                || SeleccionEstado.getText().toString() == ArregloEstados[19] && SeleccionCapital.getText().toString() == ArregloCapitales[19]
                || SeleccionEstado.getText().toString() == ArregloEstados[20] && SeleccionCapital.getText().toString() == ArregloCapitales[20]
                || SeleccionEstado.getText().toString() == ArregloEstados[21] && SeleccionCapital.getText().toString() == ArregloCapitales[21]
                || SeleccionEstado.getText().toString() == ArregloEstados[22] && SeleccionCapital.getText().toString() == ArregloCapitales[22]
                || SeleccionEstado.getText().toString() == ArregloEstados[23] && SeleccionCapital.getText().toString() == ArregloCapitales[23]
                || SeleccionEstado.getText().toString() == ArregloEstados[24] && SeleccionCapital.getText().toString() == ArregloCapitales[24]
                || SeleccionEstado.getText().toString() == ArregloEstados[25] && SeleccionCapital.getText().toString() == ArregloCapitales[25]
                || SeleccionEstado.getText().toString() == ArregloEstados[26] && SeleccionCapital.getText().toString() == ArregloCapitales[26]
                || SeleccionEstado.getText().toString() == ArregloEstados[27] && SeleccionCapital.getText().toString() == ArregloCapitales[27]
                || SeleccionEstado.getText().toString() == ArregloEstados[28] && SeleccionCapital.getText().toString() == ArregloCapitales[28]
                || SeleccionEstado.getText().toString() == ArregloEstados[29] && SeleccionCapital.getText().toString() == ArregloCapitales[29]
                || SeleccionEstado.getText().toString() == ArregloEstados[30] && SeleccionCapital.getText().toString() == ArregloCapitales[30]
                || SeleccionEstado.getText().toString() == ArregloEstados[31] && SeleccionCapital.getText().toString() == ArregloCapitales[31]
                || SeleccionEstado.getText().toString() == ArregloEstados[32] && SeleccionCapital.getText().toString() == ArregloCapitales[32]
                || SeleccionEstado.getText().toString() == ArregloEstados[33] && SeleccionCapital.getText().toString() == ArregloCapitales[33]
                || SeleccionEstado.getText().toString() == ArregloEstados[34] && SeleccionCapital.getText().toString() == ArregloCapitales[34]
                || SeleccionEstado.getText().toString() == ArregloEstados[35] && SeleccionCapital.getText().toString() == ArregloCapitales[35]
                || SeleccionEstado.getText().toString() == ArregloEstados[36] && SeleccionCapital.getText().toString() == ArregloCapitales[36]
                || SeleccionEstado.getText().toString() == ArregloEstados[37] && SeleccionCapital.getText().toString() == ArregloCapitales[37]
                || SeleccionEstado.getText().toString() == ArregloEstados[38] && SeleccionCapital.getText().toString() == ArregloCapitales[38]
                || SeleccionEstado.getText().toString() == ArregloEstados[39] && SeleccionCapital.getText().toString() == ArregloCapitales[39]
                || SeleccionEstado.getText().toString() == ArregloEstados[40] && SeleccionCapital.getText().toString() == ArregloCapitales[40]
                || SeleccionEstado.getText().toString() == ArregloEstados[41] && SeleccionCapital.getText().toString() == ArregloCapitales[41]
                || SeleccionEstado.getText().toString() == ArregloEstados[42] && SeleccionCapital.getText().toString() == ArregloCapitales[42]
                || SeleccionEstado.getText().toString() == ArregloEstados[43] && SeleccionCapital.getText().toString() == ArregloCapitales[43]
                || SeleccionEstado.getText().toString() == ArregloEstados[44] && SeleccionCapital.getText().toString() == ArregloCapitales[44]
                || SeleccionEstado.getText().toString() == ArregloEstados[45] && SeleccionCapital.getText().toString() == ArregloCapitales[45]
                || SeleccionEstado.getText().toString() == ArregloEstados[46] && SeleccionCapital.getText().toString() == ArregloCapitales[46]
                || SeleccionEstado.getText().toString() == ArregloEstados[47] && SeleccionCapital.getText().toString() == ArregloCapitales[47]
                || SeleccionEstado.getText().toString() == ArregloEstados[48] && SeleccionCapital.getText().toString() == ArregloCapitales[48]
                || SeleccionEstado.getText().toString() == ArregloEstados[49] && SeleccionCapital.getText().toString() == ArregloCapitales[49]
                || SeleccionEstado.getText().toString() == ArregloEstados[50] && SeleccionCapital.getText().toString() == ArregloCapitales[50]
                || SeleccionEstado.getText().toString() == ArregloEstados[51] && SeleccionCapital.getText().toString() == ArregloCapitales[51]
                || SeleccionEstado.getText().toString() == ArregloEstados[52] && SeleccionCapital.getText().toString() == ArregloCapitales[52]
                || SeleccionEstado.getText().toString() == ArregloEstados[53] && SeleccionCapital.getText().toString() == ArregloCapitales[53]
                || SeleccionEstado.getText().toString() == ArregloEstados[54] && SeleccionCapital.getText().toString() == ArregloCapitales[54]
                || SeleccionEstado.getText().toString() == ArregloEstados[55] && SeleccionCapital.getText().toString() == ArregloCapitales[55]
                || SeleccionEstado.getText().toString() == ArregloEstados[56] && SeleccionCapital.getText().toString() == ArregloCapitales[56]
                || SeleccionEstado.getText().toString() == ArregloEstados[57] && SeleccionCapital.getText().toString() == ArregloCapitales[57]
                || SeleccionEstado.getText().toString() == ArregloEstados[58] && SeleccionCapital.getText().toString() == ArregloCapitales[58]
                || SeleccionEstado.getText().toString() == ArregloEstados[59] && SeleccionCapital.getText().toString() == ArregloCapitales[59]
                || SeleccionEstado.getText().toString() == ArregloEstados[60] && SeleccionCapital.getText().toString() == ArregloCapitales[60]
                || SeleccionEstado.getText().toString() == ArregloEstados[61] && SeleccionCapital.getText().toString() == ArregloCapitales[61]
                || SeleccionEstado.getText().toString() == ArregloEstados[62] && SeleccionCapital.getText().toString() == ArregloCapitales[62]
                || SeleccionEstado.getText().toString() == ArregloEstados[63] && SeleccionCapital.getText().toString() == ArregloCapitales[63]
                || SeleccionEstado.getText().toString() == ArregloEstados[64] && SeleccionCapital.getText().toString() == ArregloCapitales[64]
                || SeleccionEstado.getText().toString() == ArregloEstados[65] && SeleccionCapital.getText().toString() == ArregloCapitales[65]
                || SeleccionEstado.getText().toString() == ArregloEstados[66] && SeleccionCapital.getText().toString() == ArregloCapitales[66]
                || SeleccionEstado.getText().toString() == ArregloEstados[67] && SeleccionCapital.getText().toString() == ArregloCapitales[67]
                || SeleccionEstado.getText().toString() == ArregloEstados[68] && SeleccionCapital.getText().toString() == ArregloCapitales[68]
                || SeleccionEstado.getText().toString() == ArregloEstados[69] && SeleccionCapital.getText().toString() == ArregloCapitales[69]
                || SeleccionEstado.getText().toString() == ArregloEstados[70] && SeleccionCapital.getText().toString() == ArregloCapitales[70]
                || SeleccionEstado.getText().toString() == ArregloEstados[71] && SeleccionCapital.getText().toString() == ArregloCapitales[71]
                || SeleccionEstado.getText().toString() == ArregloEstados[72] && SeleccionCapital.getText().toString() == ArregloCapitales[72]
                || SeleccionEstado.getText().toString() == ArregloEstados[73] && SeleccionCapital.getText().toString() == ArregloCapitales[73]
                || SeleccionEstado.getText().toString() == ArregloEstados[74] && SeleccionCapital.getText().toString() == ArregloCapitales[74]
                || SeleccionEstado.getText().toString() == ArregloEstados[75] && SeleccionCapital.getText().toString() == ArregloCapitales[75]
                || SeleccionEstado.getText().toString() == ArregloEstados[76] && SeleccionCapital.getText().toString() == ArregloCapitales[76]
                || SeleccionEstado.getText().toString() == ArregloEstados[77] && SeleccionCapital.getText().toString() == ArregloCapitales[77]
                || SeleccionEstado.getText().toString() == ArregloEstados[78] && SeleccionCapital.getText().toString() == ArregloCapitales[78]
                || SeleccionEstado.getText().toString() == ArregloEstados[79] && SeleccionCapital.getText().toString() == ArregloCapitales[79]
                || SeleccionEstado.getText().toString() == ArregloEstados[80] && SeleccionCapital.getText().toString() == ArregloCapitales[80]
                || SeleccionEstado.getText().toString() == ArregloEstados[81] && SeleccionCapital.getText().toString() == ArregloCapitales[81]
                || SeleccionEstado.getText().toString() == ArregloEstados[82] && SeleccionCapital.getText().toString() == ArregloCapitales[82]
                || SeleccionEstado.getText().toString() == ArregloEstados[83] && SeleccionCapital.getText().toString() == ArregloCapitales[83]
                || SeleccionEstado.getText().toString() == ArregloEstados[84] && SeleccionCapital.getText().toString() == ArregloCapitales[84]
                || SeleccionEstado.getText().toString() == ArregloEstados[85] && SeleccionCapital.getText().toString() == ArregloCapitales[85]
                || SeleccionEstado.getText().toString() == ArregloEstados[86] && SeleccionCapital.getText().toString() == ArregloCapitales[86]
                || SeleccionEstado.getText().toString() == ArregloEstados[87] && SeleccionCapital.getText().toString() == ArregloCapitales[87]
                || SeleccionEstado.getText().toString() == ArregloEstados[88] && SeleccionCapital.getText().toString() == ArregloCapitales[88]
                || SeleccionEstado.getText().toString() == ArregloEstados[89] && SeleccionCapital.getText().toString() == ArregloCapitales[89]
                || SeleccionEstado.getText().toString() == ArregloEstados[90] && SeleccionCapital.getText().toString() == ArregloCapitales[90]
                || SeleccionEstado.getText().toString() == ArregloEstados[91] && SeleccionCapital.getText().toString() == ArregloCapitales[91]
                || SeleccionEstado.getText().toString() == ArregloEstados[92] && SeleccionCapital.getText().toString() == ArregloCapitales[92]
                || SeleccionEstado.getText().toString() == ArregloEstados[93] && SeleccionCapital.getText().toString() == ArregloCapitales[93]
                || SeleccionEstado.getText().toString() == ArregloEstados[94] && SeleccionCapital.getText().toString() == ArregloCapitales[94]
                || SeleccionEstado.getText().toString() == ArregloEstados[95] && SeleccionCapital.getText().toString() == ArregloCapitales[95]
                || SeleccionEstado.getText().toString() == ArregloEstados[96] && SeleccionCapital.getText().toString() == ArregloCapitales[96]
                || SeleccionEstado.getText().toString() == ArregloEstados[97] && SeleccionCapital.getText().toString() == ArregloCapitales[97]
                || SeleccionEstado.getText().toString() == ArregloEstados[98] && SeleccionCapital.getText().toString() == ArregloCapitales[98]
                || SeleccionEstado.getText().toString() == ArregloEstados[99] && SeleccionCapital.getText().toString() == ArregloCapitales[99]
                || SeleccionEstado.getText().toString() == ArregloEstados[100] && SeleccionCapital.getText().toString() == ArregloCapitales[100]
                || SeleccionEstado.getText().toString() == ArregloEstados[101] && SeleccionCapital.getText().toString() == ArregloCapitales[101]
                || SeleccionEstado.getText().toString() == ArregloEstados[102] && SeleccionCapital.getText().toString() == ArregloCapitales[102]
                || SeleccionEstado.getText().toString() == ArregloEstados[103] && SeleccionCapital.getText().toString() == ArregloCapitales[103]
                || SeleccionEstado.getText().toString() == ArregloEstados[104] && SeleccionCapital.getText().toString() == ArregloCapitales[104]
                || SeleccionEstado.getText().toString() == ArregloEstados[105] && SeleccionCapital.getText().toString() == ArregloCapitales[105]
                || SeleccionEstado.getText().toString() == ArregloEstados[106] && SeleccionCapital.getText().toString() == ArregloCapitales[106]
                || SeleccionEstado.getText().toString() == ArregloEstados[107] && SeleccionCapital.getText().toString() == ArregloCapitales[107]
                || SeleccionEstado.getText().toString() == ArregloEstados[108] && SeleccionCapital.getText().toString() == ArregloCapitales[108]
                || SeleccionEstado.getText().toString() == ArregloEstados[109] && SeleccionCapital.getText().toString() == ArregloCapitales[109]
                || SeleccionEstado.getText().toString() == ArregloEstados[110] && SeleccionCapital.getText().toString() == ArregloCapitales[110]
                || SeleccionEstado.getText().toString() == ArregloEstados[111] && SeleccionCapital.getText().toString() == ArregloCapitales[111]
                || SeleccionEstado.getText().toString() == ArregloEstados[112] && SeleccionCapital.getText().toString() == ArregloCapitales[112]
                || SeleccionEstado.getText().toString() == ArregloEstados[113] && SeleccionCapital.getText().toString() == ArregloCapitales[113]
                || SeleccionEstado.getText().toString() == ArregloEstados[114] && SeleccionCapital.getText().toString() == ArregloCapitales[114]
                || SeleccionEstado.getText().toString() == ArregloEstados[115] && SeleccionCapital.getText().toString() == ArregloCapitales[115]
                || SeleccionEstado.getText().toString() == ArregloEstados[116] && SeleccionCapital.getText().toString() == ArregloCapitales[116]
                || SeleccionEstado.getText().toString() == ArregloEstados[117] && SeleccionCapital.getText().toString() == ArregloCapitales[117]
                || SeleccionEstado.getText().toString() == ArregloEstados[118] && SeleccionCapital.getText().toString() == ArregloCapitales[118]
                || SeleccionEstado.getText().toString() == ArregloEstados[119] && SeleccionCapital.getText().toString() == ArregloCapitales[119]
                || SeleccionEstado.getText().toString() == ArregloEstados[120] && SeleccionCapital.getText().toString() == ArregloCapitales[120]
                || SeleccionEstado.getText().toString() == ArregloEstados[121] && SeleccionCapital.getText().toString() == ArregloCapitales[121]
                || SeleccionEstado.getText().toString() == ArregloEstados[122] && SeleccionCapital.getText().toString() == ArregloCapitales[122]
                || SeleccionEstado.getText().toString() == ArregloEstados[123] && SeleccionCapital.getText().toString() == ArregloCapitales[123]
                || SeleccionEstado.getText().toString() == ArregloEstados[124] && SeleccionCapital.getText().toString() == ArregloCapitales[124]
                || SeleccionEstado.getText().toString() == ArregloEstados[125] && SeleccionCapital.getText().toString() == ArregloCapitales[125]
                || SeleccionEstado.getText().toString() == ArregloEstados[126] && SeleccionCapital.getText().toString() == ArregloCapitales[126]
                || SeleccionEstado.getText().toString() == ArregloEstados[127] && SeleccionCapital.getText().toString() == ArregloCapitales[127]
                || SeleccionEstado.getText().toString() == ArregloEstados[128] && SeleccionCapital.getText().toString() == ArregloCapitales[128]
                || SeleccionEstado.getText().toString() == ArregloEstados[129] && SeleccionCapital.getText().toString() == ArregloCapitales[129]
                || SeleccionEstado.getText().toString() == ArregloEstados[130] && SeleccionCapital.getText().toString() == ArregloCapitales[130]
                || SeleccionEstado.getText().toString() == ArregloEstados[131] && SeleccionCapital.getText().toString() == ArregloCapitales[131]
                || SeleccionEstado.getText().toString() == ArregloEstados[132] && SeleccionCapital.getText().toString() == ArregloCapitales[132]
                || SeleccionEstado.getText().toString() == ArregloEstados[133] && SeleccionCapital.getText().toString() == ArregloCapitales[133]
                || SeleccionEstado.getText().toString() == ArregloEstados[134] && SeleccionCapital.getText().toString() == ArregloCapitales[134]
                || SeleccionEstado.getText().toString() == ArregloEstados[135] && SeleccionCapital.getText().toString() == ArregloCapitales[135]
                || SeleccionEstado.getText().toString() == ArregloEstados[136] && SeleccionCapital.getText().toString() == ArregloCapitales[136]
                || SeleccionEstado.getText().toString() == ArregloEstados[137] && SeleccionCapital.getText().toString() == ArregloCapitales[137]
                || SeleccionEstado.getText().toString() == ArregloEstados[138] && SeleccionCapital.getText().toString() == ArregloCapitales[138]
                || SeleccionEstado.getText().toString() == ArregloEstados[139] && SeleccionCapital.getText().toString() == ArregloCapitales[139]
                || SeleccionEstado.getText().toString() == ArregloEstados[140] && SeleccionCapital.getText().toString() == ArregloCapitales[140]
                || SeleccionEstado.getText().toString() == ArregloEstados[141] && SeleccionCapital.getText().toString() == ArregloCapitales[141]
                || SeleccionEstado.getText().toString() == ArregloEstados[142] && SeleccionCapital.getText().toString() == ArregloCapitales[142]
                || SeleccionEstado.getText().toString() == ArregloEstados[143] && SeleccionCapital.getText().toString() == ArregloCapitales[143]
                || SeleccionEstado.getText().toString() == ArregloEstados[144] && SeleccionCapital.getText().toString() == ArregloCapitales[144]
                || SeleccionEstado.getText().toString() == ArregloEstados[145] && SeleccionCapital.getText().toString() == ArregloCapitales[145]
                || SeleccionEstado.getText().toString() == ArregloEstados[146] && SeleccionCapital.getText().toString() == ArregloCapitales[146]
                || SeleccionEstado.getText().toString() == ArregloEstados[147] && SeleccionCapital.getText().toString() == ArregloCapitales[147]
                || SeleccionEstado.getText().toString() == ArregloEstados[148] && SeleccionCapital.getText().toString() == ArregloCapitales[148]
                || SeleccionEstado.getText().toString() == ArregloEstados[149] && SeleccionCapital.getText().toString() == ArregloCapitales[149]
                || SeleccionEstado.getText().toString() == ArregloEstados[150] && SeleccionCapital.getText().toString() == ArregloCapitales[150]
                || SeleccionEstado.getText().toString() == ArregloEstados[151] && SeleccionCapital.getText().toString() == ArregloCapitales[151]
                || SeleccionEstado.getText().toString() == ArregloEstados[152] && SeleccionCapital.getText().toString() == ArregloCapitales[152]
                || SeleccionEstado.getText().toString() == ArregloEstados[153] && SeleccionCapital.getText().toString() == ArregloCapitales[153]
                || SeleccionEstado.getText().toString() == ArregloEstados[154] && SeleccionCapital.getText().toString() == ArregloCapitales[154]
                || SeleccionEstado.getText().toString() == ArregloEstados[155] && SeleccionCapital.getText().toString() == ArregloCapitales[155]
                || SeleccionEstado.getText().toString() == ArregloEstados[156] && SeleccionCapital.getText().toString() == ArregloCapitales[156]
                || SeleccionEstado.getText().toString() == ArregloEstados[157] && SeleccionCapital.getText().toString() == ArregloCapitales[157]
                || SeleccionEstado.getText().toString() == ArregloEstados[158] && SeleccionCapital.getText().toString() == ArregloCapitales[158]
                || SeleccionEstado.getText().toString() == ArregloEstados[159] && SeleccionCapital.getText().toString() == ArregloCapitales[159]
                || SeleccionEstado.getText().toString() == ArregloEstados[160] && SeleccionCapital.getText().toString() == ArregloCapitales[160]
                || SeleccionEstado.getText().toString() == ArregloEstados[161] && SeleccionCapital.getText().toString() == ArregloCapitales[161]
                || SeleccionEstado.getText().toString() == ArregloEstados[162] && SeleccionCapital.getText().toString() == ArregloCapitales[162]
                || SeleccionEstado.getText().toString() == ArregloEstados[163] && SeleccionCapital.getText().toString() == ArregloCapitales[163]
                || SeleccionEstado.getText().toString() == ArregloEstados[164] && SeleccionCapital.getText().toString() == ArregloCapitales[164]
                || SeleccionEstado.getText().toString() == ArregloEstados[165] && SeleccionCapital.getText().toString() == ArregloCapitales[165]
                || SeleccionEstado.getText().toString() == ArregloEstados[166] && SeleccionCapital.getText().toString() == ArregloCapitales[166]
                || SeleccionEstado.getText().toString() == ArregloEstados[167] && SeleccionCapital.getText().toString() == ArregloCapitales[167]
                || SeleccionEstado.getText().toString() == ArregloEstados[168] && SeleccionCapital.getText().toString() == ArregloCapitales[168]
                || SeleccionEstado.getText().toString() == ArregloEstados[169] && SeleccionCapital.getText().toString() == ArregloCapitales[169]
                || SeleccionEstado.getText().toString() == ArregloEstados[170] && SeleccionCapital.getText().toString() == ArregloCapitales[170]
                || SeleccionEstado.getText().toString() == ArregloEstados[171] && SeleccionCapital.getText().toString() == ArregloCapitales[171]
                || SeleccionEstado.getText().toString() == ArregloEstados[172] && SeleccionCapital.getText().toString() == ArregloCapitales[172]
                || SeleccionEstado.getText().toString() == ArregloEstados[173] && SeleccionCapital.getText().toString() == ArregloCapitales[173]
                || SeleccionEstado.getText().toString() == ArregloEstados[174] && SeleccionCapital.getText().toString() == ArregloCapitales[174]
                || SeleccionEstado.getText().toString() == ArregloEstados[175] && SeleccionCapital.getText().toString() == ArregloCapitales[175]
                || SeleccionEstado.getText().toString() == ArregloEstados[176] && SeleccionCapital.getText().toString() == ArregloCapitales[176]
                || SeleccionEstado.getText().toString() == ArregloEstados[177] && SeleccionCapital.getText().toString() == ArregloCapitales[177]
                || SeleccionEstado.getText().toString() == ArregloEstados[178] && SeleccionCapital.getText().toString() == ArregloCapitales[178]
                || SeleccionEstado.getText().toString() == ArregloEstados[179] && SeleccionCapital.getText().toString() == ArregloCapitales[179]
                || SeleccionEstado.getText().toString() == ArregloEstados[180] && SeleccionCapital.getText().toString() == ArregloCapitales[180]
                || SeleccionEstado.getText().toString() == ArregloEstados[181] && SeleccionCapital.getText().toString() == ArregloCapitales[181]
                || SeleccionEstado.getText().toString() == ArregloEstados[182] && SeleccionCapital.getText().toString() == ArregloCapitales[182]
                || SeleccionEstado.getText().toString() == ArregloEstados[183] && SeleccionCapital.getText().toString() == ArregloCapitales[183]
                || SeleccionEstado.getText().toString() == ArregloEstados[184] && SeleccionCapital.getText().toString() == ArregloCapitales[184]
                || SeleccionEstado.getText().toString() == ArregloEstados[185] && SeleccionCapital.getText().toString() == ArregloCapitales[185]
                || SeleccionEstado.getText().toString() == ArregloEstados[186] && SeleccionCapital.getText().toString() == ArregloCapitales[186]
                || SeleccionEstado.getText().toString() == ArregloEstados[187] && SeleccionCapital.getText().toString() == ArregloCapitales[187]
                || SeleccionEstado.getText().toString() == ArregloEstados[188] && SeleccionCapital.getText().toString() == ArregloCapitales[188]
                || SeleccionEstado.getText().toString() == ArregloEstados[189] && SeleccionCapital.getText().toString() == ArregloCapitales[189]
                || SeleccionEstado.getText().toString() == ArregloEstados[190] && SeleccionCapital.getText().toString() == ArregloCapitales[190]
                || SeleccionEstado.getText().toString() == ArregloEstados[191] && SeleccionCapital.getText().toString() == ArregloCapitales[191]
                || SeleccionEstado.getText().toString() == ArregloEstados[192] && SeleccionCapital.getText().toString() == ArregloCapitales[192]
                || SeleccionEstado.getText().toString() == ArregloEstados[193] && SeleccionCapital.getText().toString() == ArregloCapitales[193]
                || SeleccionEstado.getText().toString() == ArregloEstados[194] && SeleccionCapital.getText().toString() == ArregloCapitales[194]
                || SeleccionEstado.getText().toString() == ArregloEstados[195] && SeleccionCapital.getText().toString() == ArregloCapitales[195]
                || SeleccionEstado.getText().toString() == ArregloEstados[196] && SeleccionCapital.getText().toString() == ArregloCapitales[196]
                || SeleccionEstado.getText().toString() == ArregloEstados[197] && SeleccionCapital.getText().toString() == ArregloCapitales[197]
                || SeleccionEstado.getText().toString() == ArregloEstados[198] && SeleccionCapital.getText().toString() == ArregloCapitales[198]
                || SeleccionEstado.getText().toString() == ArregloEstados[199] && SeleccionCapital.getText().toString() == ArregloCapitales[199]
                || SeleccionEstado.getText().toString() == ArregloEstados[200] && SeleccionCapital.getText().toString() == ArregloCapitales[200]
                || SeleccionEstado.getText().toString() == ArregloEstados[201] && SeleccionCapital.getText().toString() == ArregloCapitales[201]
                || SeleccionEstado.getText().toString() == ArregloEstados[202] && SeleccionCapital.getText().toString() == ArregloCapitales[202]
                || SeleccionEstado.getText().toString() == ArregloEstados[203] && SeleccionCapital.getText().toString() == ArregloCapitales[203]
                || SeleccionEstado.getText().toString() == ArregloEstados[204] && SeleccionCapital.getText().toString() == ArregloCapitales[204]
                || SeleccionEstado.getText().toString() == ArregloEstados[205] && SeleccionCapital.getText().toString() == ArregloCapitales[205]
                || SeleccionEstado.getText().toString() == ArregloEstados[206] && SeleccionCapital.getText().toString() == ArregloCapitales[206]
                || SeleccionEstado.getText().toString() == ArregloEstados[207] && SeleccionCapital.getText().toString() == ArregloCapitales[207]
                || SeleccionEstado.getText().toString() == ArregloEstados[208] && SeleccionCapital.getText().toString() == ArregloCapitales[208]
                || SeleccionEstado.getText().toString() == ArregloEstados[209] && SeleccionCapital.getText().toString() == ArregloCapitales[209]
                || SeleccionEstado.getText().toString() == ArregloEstados[210] && SeleccionCapital.getText().toString() == ArregloCapitales[210]
                || SeleccionEstado.getText().toString() == ArregloEstados[211] && SeleccionCapital.getText().toString() == ArregloCapitales[211]
                || SeleccionEstado.getText().toString() == ArregloEstados[212] && SeleccionCapital.getText().toString() == ArregloCapitales[212]
                || SeleccionEstado.getText().toString() == ArregloEstados[213] && SeleccionCapital.getText().toString() == ArregloCapitales[213]
                || SeleccionEstado.getText().toString() == ArregloEstados[214] && SeleccionCapital.getText().toString() == ArregloCapitales[214]
                || SeleccionEstado.getText().toString() == ArregloEstados[215] && SeleccionCapital.getText().toString() == ArregloCapitales[215]
                || SeleccionEstado.getText().toString() == ArregloEstados[216] && SeleccionCapital.getText().toString() == ArregloCapitales[216]
                || SeleccionEstado.getText().toString() == ArregloEstados[217] && SeleccionCapital.getText().toString() == ArregloCapitales[217]
                || SeleccionEstado.getText().toString() == ArregloEstados[218] && SeleccionCapital.getText().toString() == ArregloCapitales[218]
                || SeleccionEstado.getText().toString() == ArregloEstados[219] && SeleccionCapital.getText().toString() == ArregloCapitales[219]
                || SeleccionEstado.getText().toString() == ArregloEstados[220] && SeleccionCapital.getText().toString() == ArregloCapitales[220]
                || SeleccionEstado.getText().toString() == ArregloEstados[221] && SeleccionCapital.getText().toString() == ArregloCapitales[221]
                || SeleccionEstado.getText().toString() == ArregloEstados[222] && SeleccionCapital.getText().toString() == ArregloCapitales[222]
                || SeleccionEstado.getText().toString() == ArregloEstados[223] && SeleccionCapital.getText().toString() == ArregloCapitales[223]
                || SeleccionEstado.getText().toString() == ArregloEstados[224] && SeleccionCapital.getText().toString() == ArregloCapitales[224]
                || SeleccionEstado.getText().toString() == ArregloEstados[225] && SeleccionCapital.getText().toString() == ArregloCapitales[225]
                || SeleccionEstado.getText().toString() == ArregloEstados[226] && SeleccionCapital.getText().toString() == ArregloCapitales[226]
                || SeleccionEstado.getText().toString() == ArregloEstados[227] && SeleccionCapital.getText().toString() == ArregloCapitales[227]
                || SeleccionEstado.getText().toString() == ArregloEstados[228] && SeleccionCapital.getText().toString() == ArregloCapitales[228]
                || SeleccionEstado.getText().toString() == ArregloEstados[229] && SeleccionCapital.getText().toString() == ArregloCapitales[229]
                || SeleccionEstado.getText().toString() == ArregloEstados[230] && SeleccionCapital.getText().toString() == ArregloCapitales[230]
                || SeleccionEstado.getText().toString() == ArregloEstados[231] && SeleccionCapital.getText().toString() == ArregloCapitales[231]
                || SeleccionEstado.getText().toString() == ArregloEstados[232] && SeleccionCapital.getText().toString() == ArregloCapitales[232]
                || SeleccionEstado.getText().toString() == ArregloEstados[233] && SeleccionCapital.getText().toString() == ArregloCapitales[233]
                || SeleccionEstado.getText().toString() == ArregloEstados[234] && SeleccionCapital.getText().toString() == ArregloCapitales[234]
                || SeleccionEstado.getText().toString() == ArregloEstados[235] && SeleccionCapital.getText().toString() == ArregloCapitales[235]
                || SeleccionEstado.getText().toString() == ArregloEstados[236] && SeleccionCapital.getText().toString() == ArregloCapitales[236]
                || SeleccionEstado.getText().toString() == ArregloEstados[237] && SeleccionCapital.getText().toString() == ArregloCapitales[237]
                || SeleccionEstado.getText().toString() == ArregloEstados[238] && SeleccionCapital.getText().toString() == ArregloCapitales[238]
                || SeleccionEstado.getText().toString() == ArregloEstados[239] && SeleccionCapital.getText().toString() == ArregloCapitales[239]
                || SeleccionEstado.getText().toString() == ArregloEstados[240] && SeleccionCapital.getText().toString() == ArregloCapitales[240]
                || SeleccionEstado.getText().toString() == ArregloEstados[241] && SeleccionCapital.getText().toString() == ArregloCapitales[241]
                || SeleccionEstado.getText().toString() == ArregloEstados[242] && SeleccionCapital.getText().toString() == ArregloCapitales[242]
                || SeleccionEstado.getText().toString() == ArregloEstados[243] && SeleccionCapital.getText().toString() == ArregloCapitales[243]
                || SeleccionEstado.getText().toString() == ArregloEstados[244] && SeleccionCapital.getText().toString() == ArregloCapitales[244]
                || SeleccionEstado.getText().toString() == ArregloEstados[245] && SeleccionCapital.getText().toString() == ArregloCapitales[245]
                || SeleccionEstado.getText().toString() == ArregloEstados[246] && SeleccionCapital.getText().toString() == ArregloCapitales[246]
                || SeleccionEstado.getText().toString() == ArregloEstados[247] && SeleccionCapital.getText().toString() == ArregloCapitales[247]
                || SeleccionEstado.getText().toString() == ArregloEstados[248] && SeleccionCapital.getText().toString() == ArregloCapitales[248]
                || SeleccionEstado.getText().toString() == ArregloEstados[249] && SeleccionCapital.getText().toString() == ArregloCapitales[249]
                || SeleccionEstado.getText().toString() == ArregloEstados[250] && SeleccionCapital.getText().toString() == ArregloCapitales[250]
                || SeleccionEstado.getText().toString() == ArregloEstados[251] && SeleccionCapital.getText().toString() == ArregloCapitales[251]
                || SeleccionEstado.getText().toString() == ArregloEstados[252] && SeleccionCapital.getText().toString() == ArregloCapitales[252]
                || SeleccionEstado.getText().toString() == ArregloEstados[253] && SeleccionCapital.getText().toString() == ArregloCapitales[253]
                || SeleccionEstado.getText().toString() == ArregloEstados[254] && SeleccionCapital.getText().toString() == ArregloCapitales[254]
                || SeleccionEstado.getText().toString() == ArregloEstados[255] && SeleccionCapital.getText().toString() == ArregloCapitales[255]
                || SeleccionEstado.getText().toString() == ArregloEstados[256] && SeleccionCapital.getText().toString() == ArregloCapitales[256]){
            //Si la respuesta es correcta
            Califica.setText("Correct");
            //cambia el color a verde
            Califica.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_correcto));
            SeleccionEstado.setText("Countries");
            SeleccionCapital.setText("Capitals");
            //Borra las respuestas correctas
            BorrarEC();
            //Limpia las variables a borrar
            BorrarEstado = "";
            BorrarCapital = "";
            //Suma 1 punto
            AumentaP();
            //Aumenta se gano
            AumentaSeGano();
            //Reinicia el contador del boton a 0
            AppInicio = 0;
        }else {
            //Si fallaste sonido feo
            mperror.start();
            Califica.setText("Incorrect");
            Califica.setBackgroundDrawable(getResources().getDrawable(R.drawable.b_fallaste));
            SeleccionEstado.setText("Countries");
            SeleccionCapital.setText("Capitals");
            //Limpia las variables a borrar
            BorrarEstado = "";
            BorrarCapital = "";
            //Resta un punto
            DecrementaP();
            //Reinicia el contador del boton a 0
            AppInicio = 0;
        }
            //Cambia el color de todos los botones a anaranjado
            BotonesAnaranjados();
        }
    }
    //Borra los Estados y Capitales
    public void BorrarEC(){

        //Borra los estados
        if (BorrarEstado == Estado1.getText().toString()){
            Estado1.setText("");
        }
        if (BorrarEstado == Estado2.getText().toString()){
            Estado2.setText("");
        }
        if (BorrarEstado == Estado3.getText().toString()){
            Estado3.setText("");
        }

        //Borra las capitales
        if (BorrarCapital == Capital1.getText().toString()){
            Capital1.setText("");
        }
        if (BorrarCapital == Capital2.getText().toString()){
            Capital2.setText("");
        }
        if (BorrarCapital == Capital3.getText().toString()){
            Capital3.setText("");
        }
    }
    //Mas 1 punto
    public void AumentaP(){
        Puntos ++;
        Marcador.setText("Points: " + Puntos);
    }
    //Menos 1 punto y menos 1 vida
    public void DecrementaP(){
        Puntos --;
        Vidas --;
        Marcador.setText("Points: " + Puntos);
        TotalVidas.setText("Lives: " + Vidas);
    }
    //LLena los arreglos
    public void LLenaLosArreglos(){
        //Ingresa los estados
        ArregloEstados[0] = "United Arab Emirates";
        ArregloEstados[1] = "Nigeria";
        ArregloEstados[2] = "Ghana";
        ArregloEstados[3] = "Pitcairn Islands";
        ArregloEstados[4] = "Ethiopia";
        ArregloEstados[5] = "Algeria";
        ArregloEstados[6] = "Niue";
        ArregloEstados[7] = "Jordan";
        ArregloEstados[8] = "Netherlands";
        ArregloEstados[9] = "Andorra";
        ArregloEstados[10] = "Turkey";
        ArregloEstados[11] = "Madagascar";
        ArregloEstados[12] = "Samoa";
        ArregloEstados[13] = "Turkmenistan";
        ArregloEstados[14] = "Eritrea";
        ArregloEstados[15] = "Kazakhstan";
        ArregloEstados[16] = "Paraguay";
        ArregloEstados[17] = "Greece";
        ArregloEstados[18] = "Cook Islands";
        ArregloEstados[19] = "Iraq";
        ArregloEstados[20] = "Azerbaijan";
        ArregloEstados[21] = "Mali";
        ArregloEstados[22] = "Brunei";
        ArregloEstados[23] = "Thailand";
        ArregloEstados[24] = "Central African Republic";
        ArregloEstados[25] = "Gambia";
        ArregloEstados[26] = "Saint Kitts and Nevis";
        ArregloEstados[27] = "China";
        ArregloEstados[28] = "Lebanon";
        ArregloEstados[29] = "Serbia";
        ArregloEstados[30] = "Belize";
        ArregloEstados[31] = "Germany";
        ArregloEstados[32] = "Switzerland";
        ArregloEstados[33] = "Kyrgyzstan";
        ArregloEstados[34] = "Guinea-Bissau";
        ArregloEstados[35] = "Colombia";
        ArregloEstados[36] = "Brazil";
        ArregloEstados[37] = "Slovakia";
        ArregloEstados[38] = "Republic of the Congo";
        ArregloEstados[39] = "Barbados";
        ArregloEstados[40] = "Belgium";
        ArregloEstados[41] = "Romania";
        ArregloEstados[42] = "Hungary";
        ArregloEstados[43] = "Argentina";
        ArregloEstados[44] = "Burundi";
        ArregloEstados[45] = "Egypt";
        ArregloEstados[46] = "Australia";
        ArregloEstados[47] = "Venezuela";
        ArregloEstados[48] = "Saint Lucia";
        ArregloEstados[49] = "French Guiana";
        ArregloEstados[50] = "United States Virgin Islands";
        ArregloEstados[51] = "Moldova";
        ArregloEstados[52] = "Turks and Caicos Islands";
        ArregloEstados[53] = "Guinea";
        ArregloEstados[54] = "Denmark";
        ArregloEstados[55] = "Senegal";
        ArregloEstados[56] = "Syria";
        ArregloEstados[57] = "Bangladesh";
        ArregloEstados[58] = "East Timor (Timor-Leste)";
        ArregloEstados[59] = "Djibouti";
        ArregloEstados[60] = "Tanzania";
        ArregloEstados[61] = "Qatar";
        ArregloEstados[62] = "Isle of Man";
        ArregloEstados[63] = "Ireland";
        ArregloEstados[64] = "Tajikistan";
        ArregloEstados[65] = "Tristan da Cunha";
        ArregloEstados[66] = "Sahrawi Arab Democratic Republic";
        ArregloEstados[67] = "Akrotiri and Dhekelia";
        ArregloEstados[68] = "Christmas Island";
        ArregloEstados[69] = "Sierra Leone";
        ArregloEstados[70] = "Tuvalu";
        ArregloEstados[71] = "Botswana";
        ArregloEstados[72] = "Cayman Islands";
        ArregloEstados[73] = "Ascension Island";
        ArregloEstados[74] = "Guyana";
        ArregloEstados[75] = "Gibraltar";
        ArregloEstados[76] = "South Georgia and the South Sandwich Islands";
        ArregloEstados[77] = "Guatemala";
        ArregloEstados[78] = "Saint Barthélemy";
        ArregloEstados[79] = "Guam";
        ArregloEstados[80] = "Bermuda";
        ArregloEstados[81] = "Easter Island";
        ArregloEstados[82] = "Vietnam";
        ArregloEstados[83] = "Zimbabwe";
        ArregloEstados[84] = "Somaliland";
        ArregloEstados[85] = "Cuba";
        ArregloEstados[86] = "Finland";
        ArregloEstados[87] = "Solomon Islands";
        ArregloEstados[88] = "Pakistan";
        ArregloEstados[89] = "Indonesia";
        ArregloEstados[90] = "Saint Helena";
        ArregloEstados[91] = "Israel";
        ArregloEstados[92] = "State of Palestine";
        ArregloEstados[93] = "State of Palestine";
        ArregloEstados[94] = "South Sudan";
        ArregloEstados[95] = "Afghanistan";
        ArregloEstados[96] = "Uganda";
        ArregloEstados[97] = "Nepal";
        ArregloEstados[98] = "Sudan";
        ArregloEstados[99] = "Ukraine";
        ArregloEstados[100] = "Rwanda";
        ArregloEstados[101] = "Jamaica";
        ArregloEstados[102] = "Norfolk Island";
        ArregloEstados[103] = "Saint Vincent and the Grenadines";
        ArregloEstados[104] = "Democratic Republic of the Congo";
        ArregloEstados[105] = "Malaysia";
        ArregloEstados[106] = "Malaysia";
        ArregloEstados[107] = "Kuwait";
        ArregloEstados[108] = "Gabon";
        ArregloEstados[109] = "Malawi";
        ArregloEstados[110] = "Peru";
        ArregloEstados[111] = "Portugal";
        ArregloEstados[112] = "Slovenia";
        ArregloEstados[113] = "Togo";
        ArregloEstados[114] = "United Kingdom";
        ArregloEstados[115] = "Angola";
        ArregloEstados[116] = "Zambia";
        ArregloEstados[117] = "Luxembourg";
        ArregloEstados[118] = "Spain";
        ArregloEstados[119] = "Marshall Islands";
        ArregloEstados[120] = "Equatorial Guinea";
        ArregloEstados[121] = "Maldives";
        ArregloEstados[122] = "Nicaragua";
        ArregloEstados[123] = "Bahrain";
        ArregloEstados[124] = "Philippines";
        ArregloEstados[125] = "Mozambique";
        ArregloEstados[126] = "Saint Martin";
        ArregloEstados[127] = "Lesotho";
        ArregloEstados[128] = "Wallis and Futuna";
        ArregloEstados[129] = "Swaziland";
        ArregloEstados[130] = "Swaziland";
        ArregloEstados[131] = "Palau";
        ArregloEstados[132] = "Palau";
        ArregloEstados[133] = "Mexico";
        ArregloEstados[134] = "Belarus";
        ArregloEstados[135] = "Somalia";
        ArregloEstados[136] = "Monaco";
        ArregloEstados[137] = "Liberia";
        ArregloEstados[138] = "Uruguay";
        ArregloEstados[139] = "Comoros";
        ArregloEstados[140] = "Russia";
        ArregloEstados[141] = "Oman";
        ArregloEstados[142] = "Kenya";
        ArregloEstados[143] = "Bahamas";
        ArregloEstados[144] = "Burma";
        ArregloEstados[145] = "Chad";
        ArregloEstados[146] = "India";
        ArregloEstados[147] = "Niger";
        ArregloEstados[148] = "Cyprus";
        ArregloEstados[149] = "Northern Cyprus";
        ArregloEstados[150] = "Mauritania";
        ArregloEstados[151] = "New Caledonia";
        ArregloEstados[152] = "Tonga";
        ArregloEstados[153] = "Greenland";
        ArregloEstados[154] = "Aruba";
        ArregloEstados[155] = "Norway";
        ArregloEstados[156] = "Canada";
        ArregloEstados[157] = "Burkina Faso";
        ArregloEstados[158] = "American Samoa";
        ArregloEstados[159] = "Federated States of Micronesia";
        ArregloEstados[160] = "Panama";
        ArregloEstados[161] = "French Polynesia";
        ArregloEstados[162] = "Suriname";
        ArregloEstados[163] = "France";
        ArregloEstados[164] = "Sint Maarten";
        ArregloEstados[165] = "Cambodia";
        ArregloEstados[166] = "Montserrat";
        ArregloEstados[167] = "Montserrat";
        ArregloEstados[168] = "Montenegro";
        ArregloEstados[169] = "Montenegro";
        ArregloEstados[170] = "Mauritius";
        ArregloEstados[171] = "Papua New Guinea";
        ArregloEstados[172] = "Vanuatu";
        ArregloEstados[173] = "Haiti";
        ArregloEstados[174] = "Trinidad and Tobago";
        ArregloEstados[175] = "Benin";
        ArregloEstados[176] = "Benin";
        ArregloEstados[177] = "Czech Republic";
        ArregloEstados[178] = "Cape Verde";
        ArregloEstados[179] = "South Africa";
        ArregloEstados[180] = "South Africa";
        ArregloEstados[181] = "South Africa";
        ArregloEstados[182] = "Kosovo";
        ArregloEstados[183] = "North Korea";
        ArregloEstados[184] = "Ecuador";
        ArregloEstados[185] = "Morocco";
        ArregloEstados[186] = "Iceland";
        ArregloEstados[187] = "Latvia";
        ArregloEstados[188] = "Saudi Arabia";
        ArregloEstados[189] = "British Virgin Islands";
        ArregloEstados[190] = "Italy";
        ArregloEstados[191] = "Dominica";
        ArregloEstados[192] = "Northern Mariana Islands";
        ArregloEstados[193] = "Costa Rica";
        ArregloEstados[194] = "Puerto Rico";
        ArregloEstados[195] = "San Marino";
        ArregloEstados[196] = "El Salvador";
        ArregloEstados[197] = "Yemen";
        ArregloEstados[198] = "Chile";
        ArregloEstados[199] = "Chile";
        ArregloEstados[200] = "Dominican Republic";
        ArregloEstados[201] = "São Tomé and Príncipe";
        ArregloEstados[202] = "Bosnia and Herzegovina";
        ArregloEstados[203] = "South Korea";
        ArregloEstados[204] = "Singapore";
        ArregloEstados[205] = "Macedonia";
        ArregloEstados[206] = "Bulgaria";
        ArregloEstados[207] = "Sri Lanka";
        ArregloEstados[208] = "Grenada";
        ArregloEstados[209] = "Jersey";
        ArregloEstados[210] = "Antigua and Barbuda";
        ArregloEstados[211] = "Guernsey";
        ArregloEstados[212] = "Saint Pierre and Miquelon";
        ArregloEstados[213] = "Falkland Islands";
        ArregloEstados[214] = "Nagorno-Karabakh Republic";
        ArregloEstados[215] = "Sweden";
        ArregloEstados[216] = "Bolivia";
        ArregloEstados[217] = "Bolivia";
        ArregloEstados[218] = "Abkhazia";
        ArregloEstados[219] = "Fiji";
        ArregloEstados[220] = "Taiwan";
        ArregloEstados[221] = "Estonia";
        ArregloEstados[222] = "Kiribati";
        ArregloEstados[223] = "Uzbekistan";
        ArregloEstados[224] = "Georgia";
        ArregloEstados[225] = "Georgia";
        ArregloEstados[226] = "Honduras";
        ArregloEstados[227] = "Iran";
        ArregloEstados[228] = "Bhutan";
        ArregloEstados[229] = "Albania";
        ArregloEstados[230] = "Transnistria";
        ArregloEstados[231] = "Japan";
        ArregloEstados[232] = "Faroe Islands";
        ArregloEstados[233] = "Libya";
        ArregloEstados[234] = "South Ossetia";
        ArregloEstados[235] = "Tunisia";
        ArregloEstados[236] = "Mongolia";
        ArregloEstados[237] = "Liechtenstein";
        ArregloEstados[238] = "Malta";
        ArregloEstados[239] = "Anguilla";
        ArregloEstados[240] = "Vatican City";
        ArregloEstados[241] = "Seychelles";
        ArregloEstados[242] = "Austria";
        ArregloEstados[243] = "Laos";
        ArregloEstados[244] = "Lithuania";
        ArregloEstados[245] = "Poland";
        ArregloEstados[246] = "United States";
        ArregloEstados[247] = "New Zealand";
        ArregloEstados[248] = "Cocos (Keeling) Islands";
        ArregloEstados[249] = "Curaçao";
        ArregloEstados[250] = "Namibia";
        ArregloEstados[251] = "Ivory Coast";
        ArregloEstados[252] = "Cameroon";
        ArregloEstados[253] = "Nauru";
        ArregloEstados[254] = "Armenia";
        ArregloEstados[255] = "Croatia";
        ArregloEstados[256] = "Disminuye a 3 el arreglo";
        //Ingresa las capitales
        ArregloCapitales[0] = "Abu Dhabi";
        ArregloCapitales[1] = "Abuja";
        ArregloCapitales[2] = "Accra";
        ArregloCapitales[3] = "Adamstown";
        ArregloCapitales[4] = "Addis Ababa";
        ArregloCapitales[5] = "Algiers";
        ArregloCapitales[6] = "Alofi";
        ArregloCapitales[7] = "Amman";
        ArregloCapitales[8] = "Amsterdam";
        ArregloCapitales[9] = "Andorra la Vella";
        ArregloCapitales[10] = "Ankara";
        ArregloCapitales[11] = "Antananarivo";
        ArregloCapitales[12] = "Apia";
        ArregloCapitales[13] = "Ashgabat";
        ArregloCapitales[14] = "Asmara";
        ArregloCapitales[15] = "Astana";
        ArregloCapitales[16] = "Asunción";
        ArregloCapitales[17] = "Athens";
        ArregloCapitales[18] = "Avarua";
        ArregloCapitales[19] = "Baghdad";
        ArregloCapitales[20] = "Baku";
        ArregloCapitales[21] = "Bamako";
        ArregloCapitales[22] = "Bandar Seri Begawan";
        ArregloCapitales[23] = "Bangkok";
        ArregloCapitales[24] = "Bangui";
        ArregloCapitales[25] = "Banjul";
        ArregloCapitales[26] = "Basseterre";
        ArregloCapitales[27] = "Beijing";
        ArregloCapitales[28] = "Beirut";
        ArregloCapitales[29] = "Belgrade";
        ArregloCapitales[30] = "Belmopan";
        ArregloCapitales[31] = "Berlin";
        ArregloCapitales[32] = "Bern";
        ArregloCapitales[33] = "Bishkek";
        ArregloCapitales[34] = "Bissau";
        ArregloCapitales[35] = "Bogotá";
        ArregloCapitales[36] = "Brasília";
        ArregloCapitales[37] = "Bratislava";
        ArregloCapitales[38] = "Brazzaville";
        ArregloCapitales[39] = "Bridgetown";
        ArregloCapitales[40] = "Brussels";
        ArregloCapitales[41] = "Bucharest";
        ArregloCapitales[42] = "Budapest";
        ArregloCapitales[43] = "Buenos Aires";
        ArregloCapitales[44] = "Bujumbura";
        ArregloCapitales[45] = "Cairo";
        ArregloCapitales[46] = "Canberra";
        ArregloCapitales[47] = "Caracas";
        ArregloCapitales[48] = "Castries";
        ArregloCapitales[49] = "Cayenne";
        ArregloCapitales[50] = "Charlotte Amalie";
        ArregloCapitales[51] = "Chisinau";
        ArregloCapitales[52] = "Cockburn Town";
        ArregloCapitales[53] = "Conakry";
        ArregloCapitales[54] = "Copenhagen";
        ArregloCapitales[55] = "Dakar";
        ArregloCapitales[56] = "Damascus";
        ArregloCapitales[57] = "Dhaka";
        ArregloCapitales[58] = "Dili";
        ArregloCapitales[59] = "Djibouti";
        ArregloCapitales[60] = "Dodoma (official)";
        ArregloCapitales[61] = "Doha";
        ArregloCapitales[62] = "Douglas";
        ArregloCapitales[63] = "Dublin";
        ArregloCapitales[64] = "Dushanbe";
        ArregloCapitales[65] = "Edinburgh of the Seven Seas";
        ArregloCapitales[66] = "El Aaiún (declared)";
        ArregloCapitales[67] = "Episkopi Cantonment";
        ArregloCapitales[68] = "Flying Fish Cove";
        ArregloCapitales[69] = "Freetown";
        ArregloCapitales[70] = "Funafuti";
        ArregloCapitales[71] = "Gaborone";
        ArregloCapitales[72] = "George Town";
        ArregloCapitales[73] = "Georgetown";
        ArregloCapitales[74] = "Georgetown";
        ArregloCapitales[75] = "Gibraltar";
        ArregloCapitales[76] = "Grytviken";
        ArregloCapitales[77] = "Guatemala City";
        ArregloCapitales[78] = "Gustavia";
        ArregloCapitales[79] = "Hagåtña";
        ArregloCapitales[80] = "Hamilton";
        ArregloCapitales[81] = "Hanga Roa";
        ArregloCapitales[82] = "Hanoi";
        ArregloCapitales[83] = "Harare";
        ArregloCapitales[84] = "Hargeisa";
        ArregloCapitales[85] = "Havana";
        ArregloCapitales[86] = "Helsinki";
        ArregloCapitales[87] = "Honiara";
        ArregloCapitales[88] = "Islamabad";
        ArregloCapitales[89] = "Jakarta";
        ArregloCapitales[90] = "Jamestown";
        ArregloCapitales[91] = "Jerusalem (declared, de facto)";
        ArregloCapitales[92] = "Jerusalem (declared)";
        ArregloCapitales[93] = "Ramallah and Gaza (de facto)";
        ArregloCapitales[94] = "Juba";
        ArregloCapitales[95] = "Kabul";
        ArregloCapitales[96] = "Kampala";
        ArregloCapitales[97] = "Kathmandu";
        ArregloCapitales[98] = "Khartoum";
        ArregloCapitales[99] = "Kiev";
        ArregloCapitales[100] = "Kigali";
        ArregloCapitales[101] = "Kingston";
        ArregloCapitales[102] = "Kingston";
        ArregloCapitales[103] = "Kingstown";
        ArregloCapitales[104] = "Kinshasa";
        ArregloCapitales[105] = "Kuala Lumpur (official, legislative and royal)";
        ArregloCapitales[106] = "Putrajaya (administrative and judicial)";
        ArregloCapitales[107] = "Kuwait City";
        ArregloCapitales[108] = "Libreville";
        ArregloCapitales[109] = "Lilongwe";
        ArregloCapitales[110] = "Lima";
        ArregloCapitales[111] = "Lisbon";
        ArregloCapitales[112] = "Ljubljana";
        ArregloCapitales[113] = "Lomé";
        ArregloCapitales[114] = "London";
        ArregloCapitales[115] = "Luanda";
        ArregloCapitales[116] = "Lusaka";
        ArregloCapitales[117] = "Luxembourg";
        ArregloCapitales[118] = "Madrid";
        ArregloCapitales[119] = "Majuro";
        ArregloCapitales[120] = "Malabo";
        ArregloCapitales[121] = "Malé";
        ArregloCapitales[122] = "Managua";
        ArregloCapitales[123] = "Manama";
        ArregloCapitales[124] = "Manila";
        ArregloCapitales[125] = "Maputo";
        ArregloCapitales[126] = "Marigot";
        ArregloCapitales[127] = "Maseru";
        ArregloCapitales[128] = "Mata-Utu";
        ArregloCapitales[129] = "Mbabane (administrative)";
        ArregloCapitales[130] = "Lobamba (royal and legislative)";
        ArregloCapitales[131] = "Melekeok (official)";
        ArregloCapitales[132] = "Ngerulmud (seat of government)";
        ArregloCapitales[133] = "Mexico City";
        ArregloCapitales[134] = "Minsk";
        ArregloCapitales[135] = "Mogadishu";
        ArregloCapitales[136] = "Monaco";
        ArregloCapitales[137] = "Monrovia";
        ArregloCapitales[138] = "Montevideo";
        ArregloCapitales[139] = "Moroni";
        ArregloCapitales[140] = "Moscow";
        ArregloCapitales[141] = "Muscat";
        ArregloCapitales[142] = "Nairobi";
        ArregloCapitales[143] = "Nassau";
        ArregloCapitales[144] = "Naypyidaw";
        ArregloCapitales[145] = "N'Djamena";
        ArregloCapitales[146] = "New Delhi";
        ArregloCapitales[147] = "Niamey";
        ArregloCapitales[148] = "Nicosia";
        ArregloCapitales[149] = "Nicosia";
        ArregloCapitales[150] = "Nouakchott";
        ArregloCapitales[151] = "Nouméa";
        ArregloCapitales[152] = "Nukualofa";
        ArregloCapitales[153] = "Nuuk";
        ArregloCapitales[154] = "Oranjestad";
        ArregloCapitales[155] = "Oslo";
        ArregloCapitales[156] = "Ottawa";
        ArregloCapitales[157] = "Ouagadougou";
        ArregloCapitales[158] = "Pago Pago";
        ArregloCapitales[159] = "Palikir";
        ArregloCapitales[160] = "Panama City";
        ArregloCapitales[161] = "Papeete";
        ArregloCapitales[162] = "Paramaribo";
        ArregloCapitales[163] = "Paris";
        ArregloCapitales[164] = "Philipsburg";
        ArregloCapitales[165] = "Phnom Penh";
        ArregloCapitales[166] = "Plymouth (official)";
        ArregloCapitales[167] = "Brades Estate (de facto)";
        ArregloCapitales[168] = "Podgorica (official)";
        ArregloCapitales[169] = "Cetinje (seat of the President)";
        ArregloCapitales[170] = "Port Louis";
        ArregloCapitales[171] = "Port Moresby";
        ArregloCapitales[172] = "Port Vila";
        ArregloCapitales[173] = "Port-au-Prince";
        ArregloCapitales[174] = "Port of Spain";
        ArregloCapitales[175] = "Porto-Novo (official)";
        ArregloCapitales[176] = "Cotonou (de facto)";
        ArregloCapitales[177] = "Prague";
        ArregloCapitales[178] = "Praia";
        ArregloCapitales[179] = "Pretoria (executive)";
        ArregloCapitales[180] = "Bloemfontein (judicial)";
        ArregloCapitales[181] = "Cape Town (legislative)";
        ArregloCapitales[182] = "Pristina";
        ArregloCapitales[183] = "Pyongyang";
        ArregloCapitales[184] = "Quito";
        ArregloCapitales[185] = "Rabat";
        ArregloCapitales[186] = "Reykjavík";
        ArregloCapitales[187] = "Riga";
        ArregloCapitales[188] = "Riyadh";
        ArregloCapitales[189] = "Road Town";
        ArregloCapitales[190] = "Rome";
        ArregloCapitales[191] = "Roseau";
        ArregloCapitales[192] = "Saipan";
        ArregloCapitales[193] = "San José";
        ArregloCapitales[194] = "San Juan";
        ArregloCapitales[195] = "San Marino";
        ArregloCapitales[196] = "San Salvador";
        ArregloCapitales[197] = "Sana'a";
        ArregloCapitales[198] = "Santiago (official)";
        ArregloCapitales[199] = "Valparaíso (legislative)";
        ArregloCapitales[200] = "Santo Domingo";
        ArregloCapitales[201] = "São Tomé";
        ArregloCapitales[202] = "Sarajevo";
        ArregloCapitales[203] = "Seoul";
        ArregloCapitales[204] = "Singapore";
        ArregloCapitales[205] = "Skopje";
        ArregloCapitales[206] = "Sofia";
        ArregloCapitales[207] = "Sri Jayawardenepura Kotte (official)";
        ArregloCapitales[208] = "St. George's";
        ArregloCapitales[209] = "St. Helier";
        ArregloCapitales[210] = "St. John's";
        ArregloCapitales[211] = "St. Peter Port";
        ArregloCapitales[212] = "St. Pierre";
        ArregloCapitales[213] = "Stanley";
        ArregloCapitales[214] = "Stepanakert";
        ArregloCapitales[215] = "Stockholm";
        ArregloCapitales[216] = "Sucre (constitutional)";
        ArregloCapitales[217] = "La Paz (administrative)";
        ArregloCapitales[218] = "Sukhumi";
        ArregloCapitales[219] = "Suva";
        ArregloCapitales[220] = "Taipei";
        ArregloCapitales[221] = "Tallinn";
        ArregloCapitales[222] = "Tarawa";
        ArregloCapitales[223] = "Tashkent";
        ArregloCapitales[224] = "Tbilisi (official)";
        ArregloCapitales[225] = "Kutaisi (legislative)";
        ArregloCapitales[226] = "Tegucigalpa";
        ArregloCapitales[227] = "Tehran";
        ArregloCapitales[228] = "Thimphu";
        ArregloCapitales[229] = "Tirana";
        ArregloCapitales[230] = "Tiraspol";
        ArregloCapitales[231] = "Tokyo";
        ArregloCapitales[232] = "Tórshavn";
        ArregloCapitales[233] = "Tripoli";
        ArregloCapitales[234] = "Tskhinvali";
        ArregloCapitales[235] = "Tunis";
        ArregloCapitales[236] = "Ulan Bator";
        ArregloCapitales[237] = "Vaduz";
        ArregloCapitales[238] = "Valletta";
        ArregloCapitales[239] = "The Valley";
        ArregloCapitales[240] = "Vatican City";
        ArregloCapitales[241] = "Victoria";
        ArregloCapitales[242] = "Vienna";
        ArregloCapitales[243] = "Vientiane";
        ArregloCapitales[244] = "Vilnius";
        ArregloCapitales[245] = "Warsaw";
        ArregloCapitales[246] = "Washington, D.C.";
        ArregloCapitales[247] = "Wellington";
        ArregloCapitales[248] = "West Island";
        ArregloCapitales[249] = "Willemstad";
        ArregloCapitales[250] = "Windhoek";
        ArregloCapitales[251] = "Yamoussoukro (official)";
        ArregloCapitales[252] = "Yaoundé";
        ArregloCapitales[253] = "Yaren (de facto)";
        ArregloCapitales[254] = "Yerevan";
        ArregloCapitales[255] = "Zagreb";
        ArregloCapitales[256] = "Disminuye a 3 el arreglo";

        //Evita que los 3 aleatorios se repitan
        do {
            ale2 = aleatorio.nextInt(ArregloEstados.length - 1);
        } while (ale1 == ale2);

        do {
            ale3 = aleatorio.nextInt(ArregloEstados.length - 1);
        } while (ale3 == ale1 || ale3 == ale2);
    }
    //Cambia el orden de los TextView
    public void OrdenamientoAleatorio(){

        //Numero que decide el orden que tomaran (6 ordenamientos distintos)
        Integer ordenamientoA = aleatorio.nextInt(6);
        Integer ordenamientoB = aleatorio.nextInt(6);

        if (ordenamientoA==0){
            Estado1.setText(ArregloEstados[ale1]);
            Estado2.setText(ArregloEstados[ale2]);
            Estado3.setText(ArregloEstados[ale3]);
        }

        if(ordenamientoA==1){
            Estado1.setText(ArregloEstados[ale1]);
            Estado2.setText(ArregloEstados[ale3]);
            Estado3.setText(ArregloEstados[ale2]);
        }

        if(ordenamientoA==2){
            Estado1.setText(ArregloEstados[ale2]);
            Estado2.setText(ArregloEstados[ale1]);
            Estado3.setText(ArregloEstados[ale3]);
        }

        if(ordenamientoA==3){
            Estado1.setText(ArregloEstados[ale2]);
            Estado2.setText(ArregloEstados[ale3]);
            Estado3.setText(ArregloEstados[ale1]);
        }

        if(ordenamientoA==4){
            Estado1.setText(ArregloEstados[ale3]);
            Estado2.setText(ArregloEstados[ale1]);
            Estado3.setText(ArregloEstados[ale2]);
        }

        if(ordenamientoA==5){
            Estado1.setText(ArregloEstados[ale3]);
            Estado2.setText(ArregloEstados[ale2]);
            Estado3.setText(ArregloEstados[ale1]);
        }

        if(ordenamientoA==6){
            Estado1.setText(ArregloEstados[ale1]);
            Estado2.setText(ArregloEstados[ale2]);
            Estado3.setText(ArregloEstados[ale3]);
        }

        //Cambia el Texto por el de una capital y los ordena de la forma que indique el int ordenamientoB

        if (ordenamientoB==0){
            Capital1.setText(ArregloCapitales[ale1]);
            Capital2.setText(ArregloCapitales[ale2]);
            Capital3.setText(ArregloCapitales[ale3]);
        }

        if (ordenamientoB==1){
            Capital1.setText(ArregloCapitales[ale1]);
            Capital2.setText(ArregloCapitales[ale3]);
            Capital3.setText(ArregloCapitales[ale2]);
        }

        if (ordenamientoB==2){
            Capital1.setText(ArregloCapitales[ale2]);
            Capital2.setText(ArregloCapitales[ale1]);
            Capital3.setText(ArregloCapitales[ale3]);
        }

        if (ordenamientoB==3){
            Capital1.setText(ArregloCapitales[ale2]);
            Capital2.setText(ArregloCapitales[ale3]);
            Capital3.setText(ArregloCapitales[ale1]);
        }

        if (ordenamientoB==4){
            Capital1.setText(ArregloCapitales[ale3]);
            Capital2.setText(ArregloCapitales[ale1]);
            Capital3.setText(ArregloCapitales[ale2]);
        }

        if (ordenamientoB==5){
            Capital1.setText(ArregloCapitales[ale3]);
            Capital2.setText(ArregloCapitales[ale2]);
            Capital3.setText(ArregloCapitales[ale1]);
        }

        if (ordenamientoB==6){
            Capital1.setText(ArregloCapitales[ale1]);
            Capital2.setText(ArregloCapitales[ale2]);
            Capital3.setText(ArregloCapitales[ale3]);
        }
    }
    //Aumenta la variable SeGano
    public void AumentaSeGano(){
        SeGano ++;
    }
    //Verifica que los TextView esten vacios
    public  void VerificarTV(){
        //Si SeGano 3 veces los valores estaran vacios
        if (SeGano == 3){
            //Se llenara de nuevo
            LLenaLosArreglos();
            OrdenamientoAleatorio();
            //Reiniciara la varable de SeGano a 0
            SeGano = 0;
        }
    }
    //Finaliza el Activiti principal
    public void FinalizaActivity(){
        if (Vidas == 0){
            //Evita el doble clic al finalizar la actividad
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return; }
            mLastClickTime = SystemClock.elapsedRealtime();

            registrarUsuariosSQL();

            Intent i = new Intent(this, SegundaActividad.class);
            i.putExtra("MandarPuntos", Puntos.toString());
            startActivity(i);
            //termina la actividad para que no se pueda regresar
            finish();
        }
    }
    //Cambia el color de los botones a anaranjado
    public void BotonesAnaranjados() {
        BE1.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_redondo));
        BE2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_redondo));
        BE3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_redondo));
        BC1.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_redondo));
        BC2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_redondo));
        BC3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_redondo));
    }
    //Inicia las vidas
    public void IniciaVidas() {
        TotalVidas.setText("Lives: "+Vidas);
    }
    //Guarda en la base de datos, se usa en el metodo FinalizaActivity
    private void registrarUsuariosSQL() {
        //abre la base de datos para escritura
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        String insert = "INSERT INTO " + Utilidades.TABLA_PUNTOSUSUARIO
                + "("
                + Utilidades.CAMPO_MARCADOR + ")"
                + "VALUES (" + Puntos + ")" ;

        db.execSQL(insert);

        db.close();
    }
    //Ejecuta el llenado de textView en un hilo para evitar cierre por el costo de procesado

    private void ejecucionConHilos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                LLenaLosArreglos();
            }
        }).start();
    }
}