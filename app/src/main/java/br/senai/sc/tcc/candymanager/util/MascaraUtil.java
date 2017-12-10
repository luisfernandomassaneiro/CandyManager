package br.senai.sc.tcc.candymanager.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by MASSANEIRO on 10/12/2017.
 */

public class MascaraUtil implements TextWatcher {
    private String sMascara;
    private EditText edtText;
    private Set<String> simboloMascara = new HashSet<>();
    private boolean bAtualizando;
    private String sTextoSemMascara = "";

    public MascaraUtil(String sMascara, EditText edtText)
    {
        this.sMascara = sMascara;
        this.edtText = edtText;
        pegarSimbolosMascara();
    }

    private void pegarSimbolosMascara()
    {
        for(int i = 0; i < sMascara.length(); ++i)
        {
            char c = sMascara.charAt(i);
            if( c != '#')
                simboloMascara.add(String.valueOf(c));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {
        String sTexto = removeMascara(charSequence.toString(), simboloMascara);
        String mascara;

        if (bAtualizando)
        {
            sTextoSemMascara = sTexto;
            bAtualizando = false;
            return;
        }

        if(sTexto.length() > sTextoSemMascara.length())
            mascara = setMascara(sMascara, sTexto);
        else
            mascara = charSequence.toString();

        bAtualizando = true;

        edtText.setText(mascara);
        edtText.setSelection(mascara.length());
    }

    @Override
    public void afterTextChanged(Editable editable)
    {

    }


    public static String removeMascara(String sTexto, Set<String> simbolosMascara)
    {
        for (String simbolo : simbolosMascara)
        {
            sTexto = sTexto.replaceAll("[" + simbolo + "]", "");
        }

        return sTexto;
    }

    public static String setMascara(String sFormat, String sTexto)
    {
        String sTextoMascarado = "";

        int i = 0;
        for (char c : sFormat.toCharArray())
        {
            if (c != '#')
            {
                sTextoMascarado += c;
                continue;
            }
            try
            {
                sTextoMascarado += sTexto.charAt(i);
            } catch (Exception e) { break; }

            i++;
        }
        return sTextoMascarado;
    }
}
