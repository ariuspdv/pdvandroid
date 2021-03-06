package br.com.arius.pdvarius;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import arius.pdv.db.AndroidUtils;

/**
 * Created by Arius on 25/10/2017.
 */

public class AriusActivityPercentualValor {

    private String error = "";
    private EditText edtValor;
    private EditText edtPorc;
    private View.OnClickListener clickbotao;
    private boolean digitadoPorc = false;
    private boolean digitadoValor = false;
    private boolean aceitaporcentagem;
    private boolean utilizaPorcentagem;
    private String titulo = "";
    private LinearLayout pnlPorcentagem;
    private TextInputLayout textInputLayout;

    private View.OnClickListener onClickListenerEdits;
    private View.OnFocusChangeListener onFocusChangeListenerEdits;

    private double valor;
    private double retorno_valor;

    public AriusActivityPercentualValor(){
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getRetorno_valor() {
        return retorno_valor;
    }

    public void setAceitaporcentagem(boolean aceitaporcentagem) {
        this.aceitaporcentagem = aceitaporcentagem;
    }

    public void setEdtValor(double valor){
        if (valor > 0)
            edtValor.setText(AndroidUtils.FormatarValor_Monetario(valor));

    }

    public void setUtilizaPorcentagem(boolean value){
        this.utilizaPorcentagem = value;
        if (pnlPorcentagem != null) {
            pnlPorcentagem.setVisibility(value ? View.VISIBLE : View.GONE);
        }

    }

    public void montaDialog_Campos(final AlertDialog alertDialog, View view, String textHint){
        edtValor = (EditText) alertDialog.findViewById(R.id.edtDialogAriusPerccentualValorValor);
        edtPorc = (EditText) alertDialog.findViewById(R.id.edtDialogAriusPerccentualValorPerc);
        textInputLayout = (TextInputLayout) alertDialog.findViewById(R.id.pnlContentDialogoValorLayout);
        textInputLayout.setHint(textHint);

        onFocusChangeListenerEdits = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    final View campoEdit = v;
                    campoEdit.post(new Runnable() {
                        @Override
                        public void run() {
                            ((EditText) campoEdit).setSelection(((EditText) campoEdit).getText().length());
                        }
                    });
                }
            }
        };

        onClickListenerEdits = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtValor.setSelection(edtValor.getText().length());
            }
        };

        pnlPorcentagem = (LinearLayout) alertDialog.findViewById(R.id.pnlContentDialogoPercValorPerc);
        clickbotao = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPorc.addTextChangedListener(new TextWatcher() {
                    private String current = "";

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!s.toString().equals(current)) {
                            error = current;
                            edtPorc.removeTextChangedListener(this);

                            String cleanString = s.toString().replace(".","");
                            if (cleanString.contains("%"))
                                cleanString = cleanString.replace("%","");
                            else
                                if (cleanString.length() > 1)
                                    cleanString = String.copyValueOf(cleanString.toCharArray(),0,cleanString.length() - 1);

                            double parsed;
                            try {
                                parsed = Double.parseDouble(cleanString);
                            } catch (NumberFormatException e) {
                                parsed = 0.00;
                            }

                            if ((parsed / 100) >= 100 && !aceitaporcentagem) {
                                edtPorc.setText(error);
                                current = error;
                                edtPorc.setSelection(edtPorc.getText().length());
                                AndroidUtils.toast(alertDialog.getContext(), titulo + " concedido maior que o permitido!");
                            } else {
                                current = new DecimalFormat("####0.00").format(parsed / 100) + "%";
                                edtPorc.setText(current);
                                edtPorc.setSelection(edtPorc.getText().length());

                                digitadoPorc = true;

                                if (!digitadoValor) {
                                    double v_valor;

                                    if (parsed == 0)
                                        v_valor = 0;
                                    else
                                        v_valor = ((valor * (parsed / 100)) / 100);

                                    if (v_valor > 0.01 || v_valor == 0) {
                                        retorno_valor = v_valor;
                                        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                                        edtValor.setText(String.valueOf(nf.format(v_valor)));
                                    }
                                }
                            }
                            digitadoPorc = false;

                            edtPorc.addTextChangedListener(this);
                        }
                    }
                });

                edtPorc.setOnFocusChangeListener(onFocusChangeListenerEdits);
                edtPorc.setOnClickListener(onClickListenerEdits);

                edtValor.addTextChangedListener(new TextWatcher() {
                    private String current = "";
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!s.toString().equals(current)) {
                            error = current;
                            edtValor.removeTextChangedListener(this);

                            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

                            String replaceable = String.format("[%s,.\\s]", nf.getCurrency().getSymbol());
                            String cleanString = s.toString().replaceAll(replaceable, "");

                            double parsed;
                            try {
                                parsed = Double.parseDouble(cleanString);
                            } catch (NumberFormatException e) {
                                parsed = 0.00;
                            }

                            String formatted = String.copyValueOf(nf.format(parsed / 100).toCharArray(), 0, 2);
                            int len = nf.format(parsed / 100).length();
                            formatted = formatted + " " + String.copyValueOf(nf.format(parsed / 100).toCharArray(), 2, len - 2);
                            current = formatted;
                            retorno_valor = (parsed / 100);
                            edtValor.setText(formatted);
                            edtValor.setSelection(formatted.length());

                            digitadoValor = true;

                            if (!digitadoPorc) {

                                double v_perc;

                                v_perc = (valor * (parsed / 100));
                                if (v_perc >= 100 && !aceitaporcentagem) {
                                    edtValor.setText(error);
                                    current = error;
                                    replaceable = String.format("[%s,.\\s]", nf.getCurrency().getSymbol());
                                    if (!error.equals("")) {
                                        cleanString = error.replaceAll(replaceable, "");
                                        v_perc = (valor * (Double.parseDouble(cleanString) / 100));
                                    }
                                    edtValor.setSelection(edtValor.getText().length());
                                    AndroidUtils.toast(alertDialog.getContext(), titulo + " concedido maior que o permitido!");
                                } else
                                    edtPorc.setText(new DecimalFormat("####0.00").format(v_perc) + "%");

                                edtPorc.setText(new DecimalFormat("####0.00").format(v_perc) + "%");
                                edtPorc.setSelection(edtPorc.getText().length());
                            }
                            digitadoValor = false;

                            edtValor.addTextChangedListener(this);
                        }
                    }
                });

                edtValor.setOnFocusChangeListener(onFocusChangeListenerEdits);
                edtValor.setOnClickListener(onClickListenerEdits);
            }
        };

        clickbotao.onClick(view);

        alertDialog.findViewById(R.id.btnDialogAriusPerccentualValorCancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

}
