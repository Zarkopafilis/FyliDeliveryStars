package fyli.gr.fylideliverystars;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class MainMenuActivity extends Activity{

    private Button souvlakiButton, pizzaButton, allButton,pancakeButton,coffeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_main_menu);

        final Context self = this;

        souvlakiButton = (Button) findViewById(R.id.souvlakiButton);
        pizzaButton = (Button) findViewById(R.id.pizzaButton);
        allButton = (Button) findViewById(R.id.allButton);
        pancakeButton = (Button) findViewById(R.id.pancakeButton);
        coffeeButton = (Button) findViewById(R.id.coffeeButton);

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

        allButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchShopLister("all");
            }
        });//this button is in the center

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

    //I ain't throwin this away
    /*private void calculateAfterCallback(int frameWidth, int frameHeight,int frameX ,int frameY){
        int sidePadding = 16;
        int buttonDiameter = (int)( Math.max(frameWidth, frameHeight) * 0.15 + Math.min(frameWidth , frameHeight) * 0.2) / 2;
        //Σουβλάκι => 8 chars
        //Pizza => 5 chars
        //Burger => 6 chars
        //Κρέπα => 5 chars
        //Καφές => 5 chars
        //The text size needs to fit the biggest word (8 chars)
        int textSize = (int) ((int) buttonDiameter/(8*2));
        int polygonCircleDiameter = frameWidth - sidePadding;
        int polygonSide = (int) (polygonCircleDiameter * Math.sin(Math.PI / 5 ));
        //int souvlakiX = frameWidth / 2;
        int souvlakiX = 1080/2;
        int souvlakiY = frameY + buttonDiameter / 2;
        int pizzaBurgerY = (int) (frameY + polygonCircleDiameter / 2 - ((polygonCircleDiameter / 2) * Math.sin(Math.toRadians(18))));
        int pizzaX = 0;
        int burgerX = frameWidth - sidePadding - buttonDiameter;
        int pancakeCoffeeY = (int) (frameWidth / 1.4 - buttonDiameter / 2);
        int pancakeX = (frameWidth - polygonSide) / 2;
        int coffeeX = frameWidth - pancakeX;

        RelativeLayout.LayoutParams souvlakiParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        souvlakiParams.leftMargin = souvlakiX - buttonDiameter / 2;
        souvlakiParams.topMargin = souvlakiY - buttonDiameter / 2 + sidePadding;
        souvlakiButton.setLayoutParams(souvlakiParams);
        souvlakiButton.setTextSize(textSize);

        RelativeLayout.LayoutParams pizzaParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        pizzaParams.leftMargin = pizzaX +  sidePadding;
        pizzaParams.topMargin = pizzaBurgerY - buttonDiameter / 2;
        pizzaButton.setLayoutParams(pizzaParams);
        pizzaButton.setTextSize(textSize);

        RelativeLayout.LayoutParams burgerParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        burgerParams.leftMargin = burgerX;
        burgerParams.topMargin = pizzaBurgerY - buttonDiameter / 2;
        burgerButton.setLayoutParams(burgerParams);
        burgerButton.setTextSize(textSize);

        RelativeLayout.LayoutParams pancakeParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        pancakeParams.leftMargin = pancakeX;
        pancakeParams.topMargin = frameY + pancakeCoffeeY;
        pancakeButton.setLayoutParams(pancakeParams);
        pancakeButton.setTextSize(textSize);

        RelativeLayout.LayoutParams coffeeParams = new RelativeLayout.LayoutParams(buttonDiameter , buttonDiameter);
        coffeeParams.leftMargin = coffeeX - buttonDiameter;
        coffeeParams.topMargin = frameY + pancakeCoffeeY;
        coffeeButton.setLayoutParams(coffeeParams);
        coffeeButton.setTextSize(textSize);


        souvlakiButton.setVisibility(View.VISIBLE);
        pizzaButton.setVisibility(View.VISIBLE);
        burgerButton.setVisibility(View.VISIBLE);
        pancakeButton.setVisibility(View.VISIBLE);
        coffeeButton.setVisibility(View.VISIBLE);
    }*/

    private void launchShopLister(String shopType){
        Intent intent = new Intent();
        intent.setClass(this, DeliveryOrTakeAway.class);
        intent.putExtra("shopType" , shopType);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

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
