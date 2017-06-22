package br.senai.sc.tcc.candymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import br.senai.sc.tcc.sistema.candymanager.R;

public class MovimentoEstoqueActivity extends AppCompatActivity {

    boolean ehEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimento_estoque);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbEntrada:
                if (checked)
                    ehEntrada = true;
                    break;
            case R.id.rbSaida:
                if (checked)
                    ehEntrada = false;
                    break;
        }
    }
}
