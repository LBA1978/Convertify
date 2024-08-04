package com.example.convertify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.convertify.data.CurrencyRepository;
import com.example.convertify.model.ExchangeRate;

public class MainActivity extends AppCompatActivity {
    private EditText amountInput;
    private Spinner fromCurrency;
    private Spinner toCurrency;
    private TextView resultText;
    private CurrencyRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountInput = findViewById(R.id.amount_input);
        fromCurrency = findViewById(R.id.from_currency_spinner);
        toCurrency = findViewById(R.id.to_currency_spinner);
        resultText = findViewById(R.id.result_text);
        Button convertButton = findViewById(R.id.convert_button);
        Button historyButton = findViewById(R.id.history_button);
        Button settingsButton = findViewById(R.id.settings_button);

        repository = new CurrencyRepository();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }

    private void convertCurrency() {
        double amount = Double.parseDouble(amountInput.getText().toString());
        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();

        repository.getExchangeRate(from, to, new CurrencyRepository.ExchangeRateCallback() {
            @Override
            public void onSuccess(ExchangeRate exchangeRate) {
                double result = amount * exchangeRate.getRate();
                resultText.setText(String.format("%.2f", result));
            }

            @Override
            public void onFailure(Throwable t) {
                resultText.setText("Error: " + t.getMessage());
            }
        });
    }
}
