package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeliveryOrTakeAway extends Activity {

    String shopType = "souvlaki";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_or_take_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shopType = extras.getString("shopType");
        }else{
            finish();
        }

        TextView deliveryButton = (TextView) findViewById(R.id.deliveryOrTextView);
        TextView takeAwayButton = (TextView) findViewById(R.id.takeAwayORTextView);

        deliveryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startListingActivity(true);
            }
        });

        takeAwayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startListingActivity(false);
            }
        });

    }

    private void startListingActivity(boolean delivery){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), ShopLister.class);
        intent.putExtra("shopType" , shopType);
        intent.putExtra("delivery" , delivery);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delivery_or_take_away, menu);
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
}
