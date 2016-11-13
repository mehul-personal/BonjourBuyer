package com.analytics.bonjourbuyer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        EditFragment.OnEditFragmentInteractionListener,
        CustomSearchFragment.OnCustomSearchFragmentInteractionListener, SettingFragment.OnSettingFragmentInteractionListener,
        ShareUserSavedLocationFragment.OnShareUserSaveLocationFragmentInteractionListener,ChatHistoryFragment.OnChatHistoryFragmentInteractionListener {
    ImageView userPhoto;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#908b8b")));

        View headerLayout = navigationView.getHeaderView(0);
        userPhoto = (ImageView) headerLayout.findViewById(R.id.imvUserPhoto);
        userName = (TextView) headerLayout.findViewById(R.id.txvUserName);

        SharedPreferences mPrefs = getSharedPreferences(
                "LOGIN_DETAIL", MODE_PRIVATE);
        userName.setText(mPrefs.getString("NAME",""));
        if(mPrefs.getString("IMAGE_URL", "").isEmpty()){
            Picasso.with(MainActivity.this)
                    .load(R.drawable.ic_no_image).transform(new CircleTransform())
                    .into(userPhoto);
        }else {
            Picasso.with(MainActivity.this)
                    .load(mPrefs.getString("IMAGE_URL", "")).placeholder(R.drawable.ic_no_image).transform(new CircleTransform())
                    .into(userPhoto);
        }userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                Fragment editFragment = new EditFragment();
                if (editFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.push_down_right_in, R.anim.push_down_right_out);
                    fragmentTransaction.replace(R.id.flMainLayout, editFragment);
                    fragmentTransaction.commit();
                }

            }
        });
        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                Fragment editFragment = new EditFragment();
                if (editFragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.push_down_right_in, R.anim.push_down_right_out);
                    fragmentTransaction.replace(R.id.flMainLayout, editFragment);
                    fragmentTransaction.commit();
                }

            }
        });
        selectMenuItem(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        selectMenuItem(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectMenuItem(int id) {
        if (id == R.id.nav_home) {
            Fragment mapFragment = new MapsFragment();
            if (mapFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_down_right_in, R.anim.push_down_right_out);
                fragmentTransaction.replace(R.id.flMainLayout, mapFragment);
                fragmentTransaction.commit();
            }

        } else if (id == R.id.nav_settings) {
            Fragment mapFragment = new SettingFragment();
            if (mapFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_down_right_in, R.anim.push_down_right_out);
                fragmentTransaction.replace(R.id.flMainLayout, mapFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_search) {
            Fragment mapFragment = new CustomSearchFragment();
            if (mapFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_down_right_in, R.anim.push_down_right_out);
                fragmentTransaction.replace(R.id.flMainLayout, mapFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_share) {
            Fragment mapFragment = new ShareUserSavedLocationFragment();
            if (mapFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_down_right_in, R.anim.push_down_right_out);
                fragmentTransaction.replace(R.id.flMainLayout, mapFragment);
                fragmentTransaction.commit();
            }
//            Intent i3 = new Intent(MainActivity.this,
//                    ShareUserSavedLocationFragment.class);
//            i3.putExtra("STATUS", "ADD");
//            startActivityForResult(i3, 1);
        }else if(id==R.id.nav_chat_history){
            Fragment mapFragment = new ChatHistoryFragment();
            if (mapFragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_down_right_in, R.anim.push_down_right_out);
                fragmentTransaction.replace(R.id.flMainLayout, mapFragment);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.nav_sign_out) {
            SharedPreferences sharedPreferences5 = getSharedPreferences(
                    "LOGIN_DETAIL", 0);
            sharedPreferences5.edit().clear().commit();
            Intent intent = new Intent(MainActivity.this,
                    LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    } public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
