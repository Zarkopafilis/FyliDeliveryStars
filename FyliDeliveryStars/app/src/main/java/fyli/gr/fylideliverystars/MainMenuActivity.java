package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainMenuActivity extends Activity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Show welcoming toast
        Context context = getApplicationContext();
        CharSequence welcome_text = getResources().getString(R.string.welcome_toast);
        int duration = Toast.LENGTH_SHORT;
        final Toast toast = Toast.makeText(context, welcome_text, duration);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

        //Shop types
        ArrayList<String> shopTypeArray = new ArrayList<String>();
        shopTypeArray.add("Σουβλάκι");
        shopTypeArray.add("Pizza");
        shopTypeArray.add("Burger");
        shopTypeArray.add("Κρέπα");
        shopTypeArray.add("Καφές");

        //Set the listview
        ListView shopTypeListView = (ListView) findViewById(R.id.ShopTypeList);
        shopTypeListView.setOnItemClickListener(this);

        ArrayAdapter<String> shopTypeListAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item_simple_black_tv, shopTypeArray);
        shopTypeListView.setAdapter(shopTypeListAdapter);
        shopTypeListAdapter.notifyDataSetChanged();

        //set up contact listener
        TextView contactText = (TextView) findViewById(R.id.contactText);

        final Context self = this;

        contactText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(self , Contact.class);
                startActivity(intent);
            }
        });

    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {

        String shopType = "souvlaki";

        switch (position) {
            case 0:
                shopType="souvlaki";
                break;
            case 1:
                shopType="pizza";
                break;
            case 2:
                shopType="burger";
                break;
            case 3:
                shopType="pancake";
            case 4:
                shopType="coffee";
                break;
            default:
                shopType = "souvlaki";
                return;
        }

        Intent intent = new Intent();
        intent.setClass(this, DeliveryOrTakeAway.class);
        intent.putExtra("shopType" , shopType);
        startActivity(intent);
    }

    //Don't go to the splash screen and cause hang
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
