package com.example.admin.bodybuildingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;


public class BMRCalculator extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmrcalculator, container, false);

        Button button = view.findViewById(R.id.btnCalculate);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TextView txtAge = getView().findViewById(R.id.txtAge);
                TextView txtHeight1 = getView().findViewById(R.id.txtHeight1);
                TextView txtHeight2 = getView().findViewById(R.id.txtHeight2);
                TextView txtWeight = getView().findViewById(R.id.txtWeight);
                TextView txtResult = getView().findViewById(R.id.txtResult);

                double weight_coeff = 0;
                double height_coeff = 0;
                double age_coeff = 0;
                double gender_coeff = 0;
                int Height1 = 0;
                int Height2 = 0;
                double Height = 0;
                double Weight = 0;

                RadioGroup radiogroup = getView().findViewById(R.id.SexGroup);
                int selectedId = radiogroup.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.male_rbtn:
                        weight_coeff = 6.23;
                        height_coeff = 12.7;
                        age_coeff = 6.8;
                        gender_coeff = 66;
                        break;
                    case R.id.female_rbtn:
                        weight_coeff = 4.35;
                        height_coeff = 4.7;
                        age_coeff = 4.7;
                        gender_coeff = 65.5;
                        break;
                }
                radiogroup = getView().findViewById(R.id.HeightGroup);
                selectedId = radiogroup.getCheckedRadioButtonId();
                Height1 = Integer.parseInt(String.valueOf(txtHeight1.getText()));
                Height2 = Integer.parseInt(String.valueOf(txtHeight2.getText()));
                switch (selectedId) {
                    case R.id.feet_rbtn:
                        Height = Height1 * 12 + Height2;
                        break;

                    case R.id.meter_rbtn:
                        Height = Height1 * 39.372 + Height2 * 0.394;
                        break;
                }
                radiogroup = getView().findViewById(R.id.WeightGroup);
                selectedId = radiogroup.getCheckedRadioButtonId();
                Weight = Double.parseDouble(String.valueOf(txtWeight.getText()));
                switch (selectedId) {
                    case R.id.pound_rbtn:
                        break;

                    case R.id.kilo_rbtn:
                            Weight = Weight * 2.20462;
                        break;
                }

                double Age = Double.parseDouble(String.valueOf(txtAge.getText()));
                double Results = gender_coeff + (weight_coeff * Weight) + (height_coeff * Height) - (age_coeff * Age) * 1.4;
                int Results_int = (int) Math.round(Results);
                String result_string = "Result\n" + Integer.toString(Results_int);
                txtResult.setText(result_string);

            }
        });

        return view;
    }

}



