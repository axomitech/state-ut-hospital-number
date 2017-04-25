package android.example.com.governmentofindiahospitals;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shyam on 20/4/17.
 */

public class HospitalLoader extends AsyncTaskLoader<ArrayList<Hospital>> {
    public String urlRequest =
            "https://data.gov.in/api/datastore/resource.json?resource_id=e8fd5651-b313-45ab-8619-13f29bd52aca&api-key=18ca12186bfd495c835144ad72dcd2e8&sort[%22StateUT%22]=%22asc%22";
    public HospitalLoader(Context context){
        super(context);
    }
    @Override
    public ArrayList<Hospital> loadInBackground() {
        ArrayList<Hospital> list = new ArrayList<>();
        String [][] hospitalData = HospitalUtility.getHospitalData(urlRequest);
        for (int i = 0; i < hospitalData.length; i++){
            list.add(new Hospital(hospitalData[i][0],hospitalData[i][1],hospitalData[i][2],hospitalData[i][3]));
        }
        return list;
    }
}
