package com.ultrainstinct.android.visapay.Products;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ultrainstinct.android.visapay.R;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class ProductsActivity extends Activity {

    TextView tv1,tv2,tv3;
    ImageView img1,img2,img3;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);



        initUI();
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp, null, false);

                tv1 = view.findViewById(R.id.tv1);
                tv2 = view.findViewById(R.id.tv2);
                tv3 = view.findViewById(R.id.tv3);

                img1 = view.findViewById(R.id.ImgView1);
                img2 = view.findViewById(R.id.ImgView2);
                img3 = view.findViewById(R.id.ImgView3);

//                final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
//                txtPage.setText(String.format("Page #%d", position));

                container.addView(view);
                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
                        .title("Groceries")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Electronics")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
                        .title("Men Clothing")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Women Clothing")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fifth),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .title("Accessories")
                        .badgeTitle("777")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                Toast.makeText(getBaseContext(), "PAGE : " + position, Toast.LENGTH_SHORT).show();

                if(position == 0){
                    tv1.setText("Strawberry");
                    tv2.setText("Banana");
                    tv3.setText("Garlic");
                    img1.setImageResource(R.drawable.straw_berry);
                    img2.setImageResource(R.drawable.banana);
                    img3.setImageResource(R.drawable.garlic);
                }
                else if(position == 1){
                    tv1.setText("Head Phones");
                    tv2.setText("Mobiles");
                    tv3.setText("Keyboard");
                    img1.setImageResource(R.drawable.headphone);
                    img2.setImageResource(R.drawable.mobile);
                    img3.setImageResource(R.drawable.keyboard);

                }
                else if(position == 2){
                    tv1.setText("Shirts");
                    tv2.setText("Tie");
                    tv3.setText("Pants");
                    img1.setImageResource(R.drawable.shirts);
                    img2.setImageResource(R.drawable.tie);
                    img3.setImageResource(R.drawable.pants);

                }
                else if(position == 3){
                    tv1.setText("Apparel");
                    tv2.setText("Gown");
                    tv3.setText("Jeans");
                    img1.setImageResource(R.drawable.apparel);
                    img2.setImageResource(R.drawable.gown);
                    img3.setImageResource(R.drawable.jeans);
                }
                else{
                    tv1.setText("Watches");
                    tv2.setText("Sun glasses");
                    tv3.setText("Wallet");
                    img1.setImageResource(R.drawable.watch);
                    img2.setImageResource(R.drawable.sunglass);
                    img3.setImageResource(R.drawable.wallets);

                }

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {


            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}

