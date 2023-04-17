package com.example.aplikacjakurierska.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


import com.example.aplikacjakurierska.R;

public class MainNewActivity extends AppCompatActivity {

Button buttonViewProduct;
Animation scaleUp,scaleDown;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_new);
buttonViewProduct = findViewById(R.id.buttonViewProduct);
scaleUp = AnimationUtils.loadAnimation(this,R.anim.scale_button);
scaleDown = AnimationUtils.loadAnimation(this,R.anim.scale_down_button);

buttonViewProduct.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            buttonViewProduct.startAnimation(scaleUp);
        }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
            buttonViewProduct.startAnimation(scaleDown);
        }return false;
    }
});}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.locationmenu:
                Intent locationmenu = new Intent(this,OrderAdressActivity.class);
                this.startActivity(locationmenu);
                return true;
            case R.id.addproductmenu:
                Intent addmenuproduct = new Intent(this, ChooseProductActivity.class);
                this.startActivity(addmenuproduct);
                return true;
            case R.id.shoppingcartmenu:
                Intent shoppingcartmenu = new Intent(this,OrderAcitivty.class);
                this.startActivity(shoppingcartmenu);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}