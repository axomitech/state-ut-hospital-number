package android.example.com.governmentofindiahospitals;

import android.app.DialogFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Hospital>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getBaseContext();
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected == true) {
            ProgressBar progressBar = (ProgressBar)findViewById(R.id.hospital_progress_bar);
            progressBar.setVisibility(View.VISIBLE);
            getSupportLoaderManager().initLoader(0,null,this).forceLoad();
        }
        else{
            ProgressBar progressBar = (ProgressBar)findViewById(R.id.hospital_progress_bar);
            progressBar.setVisibility(View.GONE);
            ListView listView = (ListView)findViewById(R.id.hospital_no_list_view);
            listView.setVisibility(View.GONE);
            TextView textView = (TextView)findViewById(R.id.network_message);
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.exit_te_app){
            //finish();
            DialogFragment myDialog = new MyDialogFragment();
            myDialog.show(getFragmentManager(),"theDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<ArrayList<Hospital>> onCreateLoader(int id, Bundle args) {
        return new HospitalLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Hospital>> loader, ArrayList<Hospital> data) {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.hospital_progress_bar);
        progressBar.setVisibility(View.GONE);
        ListAdapter listAdapterHospital = new HospitalAdapter(MainActivity.this,data);
        ListView listViewHospitalNumber = (ListView)findViewById(R.id.hospital_no_list_view);
        listViewHospitalNumber.setAdapter(listAdapterHospital);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Hospital>> loader) {

    }
}
