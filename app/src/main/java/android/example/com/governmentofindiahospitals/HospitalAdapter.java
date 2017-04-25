package android.example.com.governmentofindiahospitals;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyam on 20/4/17.
 */

public class HospitalAdapter extends ArrayAdapter<Hospital> {

    public HospitalAdapter(Context context,ArrayList<Hospital> hospitals) {
        super(context,0,hospitals);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hospital_number,parent,false);
        }
        Hospital hospital = getItem(position);
        TextView textViewStateName = (TextView)convertView.findViewById(R.id.location);
        TextView textViewSubDivision = (TextView)convertView.findViewById(R.id.sub_division);
        TextView textViewDistrict = (TextView)convertView.findViewById(R.id.district_hospital);
        TextView textViewMedicalUnits = (TextView)convertView.findViewById(R.id.mobile_medical_units);
        textViewStateName.setText(hospital.getStateName());
        textViewSubDivision.setText("Sub Divisional Hospitals "+hospital.getNoOfSubdivisionalHospitals());
        textViewDistrict.setText("District Hospitals "+hospital.getNoOfDistrictHospitals());
        textViewMedicalUnits.setText("Mobile Medical Units "+hospital.getNoMobileMedicalUnit());
        TextView textViewStateFirstLetter = (TextView)convertView.findViewById(R.id.state_first_letter);
        GradientDrawable gradientDrawable =
                (GradientDrawable)textViewStateFirstLetter.getBackground();
        int letterColor = getLetterColor(String.valueOf(hospital.getStateName().charAt(0)));
        textViewStateFirstLetter.setText(String.valueOf(hospital.getStateName().charAt(0)));
        gradientDrawable.setColor(letterColor);
        return convertView;
    }
    private int getLetterColor(String  firstLetter) {
        int magnitudeColorResourceId;
        switch (firstLetter) {
            case "A":
            case "B":
            case "C":
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case "E":
            case "F":
            case "G":
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case "I":
            case "J":
            case "K":
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case "L":
            case "M":
            case "N":
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case "O":
            case "P":
            case "Q":
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case "R":
            case "S":
            case "T":
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case "U":
            case "V":
            case "W":
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case "X":
            case "Y":
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case "Z":
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
