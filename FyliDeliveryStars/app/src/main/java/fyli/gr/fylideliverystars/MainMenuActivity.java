package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainMenuActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_main_menu);

        final Context self = this;

        //Get screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        int buttonDiameter = (int)( Math.max(screenHeight, screenWidth) * 0.15 + Math.min(screenHeight , screenWidth) * 0.2) / 2;
        Log.d("slp" , "buttonDiameter:" + buttonDiameter);

        //Σουβλάκι => 8 chars
        //Pizza => 5 chars
        //Burger => 6 chars
        //Κρέπα => 5 chars
        //Καφές => 5 chars
        //The text size needs to fit the biggest word (8 chars)
        int textSize = (int) ((int) buttonDiameter/(8*2));


        Button souvlakiButton = (Button) findViewById(R.id.souvlakiButton);
        Button pizzaButton = (Button) findViewById(R.id.pizzaButton);
        Button burgerButton = (Button) findViewById(R.id.burgerButton);
        Button pancakeButton = (Button) findViewById(R.id.pancakeButton);
        Button coffeeButton = (Button) findViewById(R.id.coffeeButton);

        //Buttons are circles
        RelativeLayout.LayoutParams souvlakiParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        souvlakiButton.setLayoutParams (souvlakiParams);
        souvlakiButton.setTextSize(textSize);

        RelativeLayout.LayoutParams pizzaParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        pizzaButton.setLayoutParams(pizzaParams);
        pizzaButton.setTextSize(textSize);

        RelativeLayout.LayoutParams burgerParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        burgerButton.setLayoutParams(burgerParams);
        burgerButton.setTextSize(textSize);

        RelativeLayout.LayoutParams pancakeParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        pancakeButton.setLayoutParams(pancakeParams);
        pancakeButton.setTextSize(textSize);

        RelativeLayout.LayoutParams coffeeParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        coffeeButton.setLayoutParams (coffeeParams);
        coffeeButton.setTextSize(textSize);


        //Listener setup
        souvlakiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchShopLister("souvlaki");
            }
        });

        pizzaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchShopLister("pizza");
            }
        });

        burgerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchShopLister("burger");
            }
        });

        pancakeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchShopLister("pancake");
            }
        });

        coffeeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchShopLister("coffee");
            }
        });



        //set up contact listener
        TextView contactText = (TextView) findViewById(R.id.contactText);
        final Activity thisActivity = this;
        contactText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(self , Contact.class);
                startActivity(intent,
                        ActivityOptions
                                .makeSceneTransitionAnimation(thisActivity).toBundle());
            }
        });

        /*//Show welcoming toast
        Context context = getApplicationContext();
        CharSequence welcome_text = getResources().getString(R.string.welcome_toast);
        int duration = Toast.LENGTH_SHORT;
        final Toast toast = Toast.makeText(context, welcome_text, duration);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();*/

        /*//Shop types
        ArrayList<String> shopTypeArray = new ArrayList<String>();
        shopTypeArray.add("Σουβλάκι");
        shopTypeArray.add("Pizza");
        shopTypeArray.add("Burger - Sandwitch");
        shopTypeArray.add("Κρέπα");
        shopTypeArray.add("Καφές");

        //Set the listview
        ListView shopTypeListView = (ListView) findViewById(R.id.ShopTypeList);
        shopTypeListView.setOnItemClickListener(this);

        ArrayAdapter<String> shopTypeListAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item_simple_black_tv, shopTypeArray);
        shopTypeListView.setAdapter(shopTypeListAdapter);
        shopTypeListAdapter.notifyDataSetChanged();*/

    }

    private void launchShopLister(String shopType){
        Intent intent = new Intent();
        intent.setClass(this, DeliveryOrTakeAway.class);
        intent.putExtra("shopType" , shopType);
        startActivity(intent,
                ActivityOptions
                        .makeSceneTransitionAnimation(this).toBundle());
    }

    /*public void onItemClick(AdapterView<?> l, View v, int position, long id) {

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
                break;
            case 4:
                shopType="coffee";
                break;
            default:
                shopType = "souvlaki";
                return;
        }

        //Log.d("slp" , "shopType:" + shopType);

        Intent intent = new Intent();
        intent.setClass(this, DeliveryOrTakeAway.class);
        intent.putExtra("shopType" , shopType);
        startActivity(intent);
    }*/

    //Don't go to the splash screen and cause hang
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent,
                ActivityOptions
                        .makeSceneTransitionAnimation(this).toBundle());
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
