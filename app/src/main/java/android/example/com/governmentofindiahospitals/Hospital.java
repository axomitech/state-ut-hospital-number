package android.example.com.governmentofindiahospitals;

/**
 * Created by shyam on 20/4/17.
 */

public class Hospital {
    private String stateName;
    private String noOfSubdivisionalHospitals;
    private String noOfDistrictHospitals;
    private String noMobileMedicalUnit;
    public Hospital(
            String stateName,
            String noOfSubdivisionalHospitals,
            String noOfDistrictHospitals,
            String noMobileMedicalUnit
            ){

        this.stateName = stateName;
        this.noOfSubdivisionalHospitals = noOfSubdivisionalHospitals;
        this.noOfDistrictHospitals = noOfDistrictHospitals;
        this.noMobileMedicalUnit = noMobileMedicalUnit;
    }
    public String getStateName(){
        return stateName;
    }
    public String getNoOfSubdivisionalHospitals(){
        return noOfSubdivisionalHospitals;
    }
    public String getNoOfDistrictHospitals(){
        return noOfDistrictHospitals;
    }
    public String getNoMobileMedicalUnit(){
        return noMobileMedicalUnit;
    }
}
