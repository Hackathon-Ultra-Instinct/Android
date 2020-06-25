package com.ultrainstinct.android.visapay;

import android.content.Intent;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class BoardingActivity extends AhoyOnboarderActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Payments Simplified", "We aim to serve simplified and secured payments experience.Empowered by VISA APIs the app will make payments much easier", R.drawable.money);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Vehicle Number Plate", "Now store your money in your vehicle number plate! It's no less than your account number!", R.drawable.number);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Know the locations", "Scan the nearest available merchants accepting visa payments", R.drawable.location);
        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Bar code and QR code scanning", "Purchase items and pay bills without any human contact.", R.drawable.scan);
        AhoyOnboarderCard ahoyOnboarderCard5 = new AhoyOnboarderCard("Smart Pay for parking slots", "Paying for parking slots made really convenient", R.drawable.car);


        ahoyOnboarderCard1.setTitleColor(android.R.color.black);
        ahoyOnboarderCard1.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard2.setTitleColor(android.R.color.black);
        ahoyOnboarderCard2.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard3.setTitleColor(android.R.color.black);
        ahoyOnboarderCard3.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard4.setTitleColor(android.R.color.black);
        ahoyOnboarderCard4.setDescriptionColor(android.R.color.black);
        ahoyOnboarderCard5.setTitleColor(android.R.color.black);
        ahoyOnboarderCard5.setDescriptionColor(android.R.color.black);


        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard5.setBackgroundColor(R.color.black_transparent);


        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);
        pages.add(ahoyOnboarderCard5);



        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setImageBackground(R.drawable.boarding);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(getBaseContext(),SplashActivity.class));
    }
}

