package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainMenuActivity extends Activity{

    private Button souvlakiButton, pizzaButton, burgerButton,pancakeButton,coffeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_main_menu);

        final Context self = this;

        //Get screen dimensions
        final FrameLayout frame = (FrameLayout) findViewById(R.id.circleButtonsAreaPlaceholder);
        ViewTreeObserver vto = frame.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16)
                    frame.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                else
                    frame.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int width  = frame.getMeasuredWidth();
                int height = frame.getMeasuredHeight();

                calculateAfterCallback(width,height);
            }
        });

        souvlakiButton = (Button) findViewById(R.id.souvlakiButton);
        pizzaButton = (Button) findViewById(R.id.pizzaButton);
        burgerButton = (Button) findViewById(R.id.burgerButton);
        pancakeButton = (Button) findViewById(R.id.pancakeButton);
        coffeeButton = (Button) findViewById(R.id.coffeeButton);

        //Make them appear after frame layout callback
        souvlakiButton.setVisibility(View.INVISIBLE);
        pizzaButton.setVisibility(View.INVISIBLE);
        burgerButton.setVisibility(View.INVISIBLE);
        pancakeButton.setVisibility(View.INVISIBLE);
        coffeeButton.setVisibility(View.INVISIBLE);

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
    }

    private void calculateAfterCallback(int frameWidth, int frameHeight){
        int buttonDiameter = (int)( Math.max(frameWidth, frameHeight) * 0.15 + Math.min(frameWidth , frameHeight) * 0.2) / 2;
        Log.d("slp" , "buttonDiameter:" + buttonDiameter);
        //Σουβλάκι => 8 chars
        //Pizza => 5 chars
        //Burger => 6 chars
        //Κρέπα => 5 chars
        //Καφές => 5 chars
        //The text size needs to fit the biggest word (8 chars)
        int textSize = (int) ((int) buttonDiameter/(8*2));

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

        souvlakiButton.setVisibility(View.VISIBLE);
        pizzaButton.setVisibility(View.VISIBLE);
        burgerButton.setVisibility(View.VISIBLE);
        pancakeButton.setVisibility(View.VISIBLE);
        coffeeButton.setVisibility(View.VISIBLE);
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
