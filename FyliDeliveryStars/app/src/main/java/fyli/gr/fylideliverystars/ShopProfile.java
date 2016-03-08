package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

import fyli.gr.fylideliverystars.provider.Shop;

public class ShopProfile extends Activity {

    Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shop = (Shop) extras.getSerializable("shop");
        }else{
            finish();
        }

        TextView shopNameText = (TextView) findViewById(R.id.shopNameTextView);
        shopNameText.setText(shop.getName());

        TextView workHoursText = (TextView) findViewById(R.id.workHoursTextView);
        workHoursText.setText(shop.getWorkHours());

        Button showLocButton = (Button) findViewById(R.id.showLocationButton);
        showLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openMaps(shop.getGPSCoordinates());
            }
        });

        Button phone1 = (Button) findViewById(R.id.telephone1Button);
        Button phone2 = (Button) findViewById(R.id.telephone2Button);

        switch(shop.getTelephones().length){
            default:
                phone1.setVisibility(View.INVISIBLE);
                phone2.setVisibility(View.INVISIBLE);
            case 1:
                phone1.setText(shop.getTelephones()[0]);
                phone1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dial(shop.getTelephones()[0]);
                    }
                });
                phone2.setVisibility(View.INVISIBLE);
            case 2:
                phone1.setText(shop.getTelephones()[0]);
                phone1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dial(shop.getTelephones()[0]);
                    }
                });
                phone2.setText(shop.getTelephones()[1]);
                phone2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dial(shop.getTelephones()[1]);
                    }
                });
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_profile, menu);
        return true;
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
        String uri = String.format(Locale.ENGLISH, "mapURL");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
