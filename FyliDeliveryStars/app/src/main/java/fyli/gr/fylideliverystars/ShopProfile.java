package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import fyli.gr.fylideliverystars.provider.Shop;

public class ShopProfile extends Activity {

    Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_shop_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shop = (Shop) extras.getSerializable("shop");
        }else{
            finish();
        }

        getActionBar().setTitle(shop.getName());

        TextView workHoursText = (TextView) findViewById(R.id.workHoursTextView);
        workHoursText.setText(shop.getWorkHours());

        Button showLocButton = (Button) findViewById(R.id.showLocationButton);

        showLocButton.setText(shop.getLocation());

        showLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMaps(shop.getGPSCoordinates());
                 }
        });

        Button phone1 = (Button) findViewById(R.id.telephone1Button);
        Button phone2 = (Button) findViewById(R.id.telephone2Button);

        //Log.d("slp", "telephone length: " + shop.getTelephones().length);

        switch(shop.getTelephones().length){
            default:
                phone1.setVisibility(View.INVISIBLE);
                phone2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                if(!phone1.equals("")) {
                    phone1.setText(shop.getTelephones()[0]);
                    phone1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dial(shop.getTelephones()[0]);
                        }
                    });
                }else{
                    phone1.setVisibility(View.INVISIBLE);
                }
                phone2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                if(!phone1.equals("")) {
                    phone1.setText(shop.getTelephones()[0]);
                    phone1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dial(shop.getTelephones()[0]);
                        }
                    });
                }else{
                    phone1.setVisibility(View.INVISIBLE);
                }
                if(!phone2.equals("")) {
                    phone2.setText(shop.getTelephones()[1]);
                    phone2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dial(shop.getTelephones()[1]);
                        }
                    });
                }else{
                    phone2.setVisibility(View.INVISIBLE);
                }
                break;
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dial(String number){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));
        startActivity(callIntent);
    }

    private void openMaps(String mapsURL){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapsURL));
        startActivity(intent);
    }
}
