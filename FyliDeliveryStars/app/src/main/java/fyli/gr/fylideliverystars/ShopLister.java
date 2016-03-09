package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import fyli.gr.fylideliverystars.provider.ShopListProvider;

public class ShopLister extends Activity implements AdapterView.OnItemClickListener{

    String shopType;
    boolean delivery;
    ShopListProvider slp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_lister);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shopType = extras.getString("shopType");
            delivery = extras.getBoolean("delivery");
        }

        //Log.d("slp" , "initializing shop list provider, delivery:"  + delivery);
        //Log.d("slp", "shopType:" + shopType);
        slp = new ShopListProvider(shopType,delivery);
        List<String> names = slp.generateShopList(getApplicationContext());

        ListView shopListView = (ListView) findViewById(R.id.shopListerListView);

        ArrayAdapter<String> shopTypeListAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.list_item_simple_black_tv, names);
        shopListView.setAdapter(shopTypeListAdapter);
        shopTypeListAdapter.notifyDataSetChanged();

        shopListView.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        if(slp != null) {
            Intent intent = new Intent();
            intent.setClass(this, ShopProfile.class);
            intent.putExtra("shop", slp.getCachedShopFromList(position));
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_lister, menu);
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
