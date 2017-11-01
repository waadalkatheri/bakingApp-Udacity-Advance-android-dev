package walkatheri.mybakingtips;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

/**
 * Created by Jaink on 25-08-2017.
 */

public class DetailRecipe extends AppCompatActivity {
    int item_position;
    int step_position;
    List<Recipe> dListRecipe;
    TabLayout tabLayout;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailrecipe);

        dListRecipe = MainActivity.getList();

        Intent i = getIntent();
        item_position = i.getExtras().getInt("item_position");
        step_position = i.getExtras().getInt("step_position");
        Log.e("DetailRecipe"," item "+ item_position +"  step "+ step_position);

        // set the activity title
        setTitle(dListRecipe.get(item_position).getName());

        tabLayout = (TabLayout) findViewById(R.id.tablayout1);

        /// add tab on tablayout
        for (int j= 0; j< dListRecipe.get(item_position).getSteps().size(); j++ ) {

            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.stepname)+" " + j));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // change the view pager ther too
                step_position = tab.getPosition();
                Log.e("DetailsRecipe "," step position "+ step_position);
                /// create fragment only tab select
                createfragment();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                Log.e("DetailsRecipe ","tab Unselect "+ tab.getPosition());

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("DetailsRecipe ","tab RE select "+ tab.getPosition());
                createfragment();

            }
        });
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        /// problem - viewPager adapter always create next page for better performance but
        // we have only one static fragment that have one exoplayer and exoplayer don't allow second instance of exoplayer
        // so when adapter going to create second player it shows error
        // exoplayer already exist---
        /// solution  we have to create container in layout xml that create fragment everytime using fragmentmanager.

       // show the tab (step position ) that user click on  selectRecipe Activity
        tabLayout.getTabAt(step_position).select();

    }

    public void createfragment(){

        Log.e("detailRecipe ", " create fragmnet "+ step_position);
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();

        Fragment prevFreg = fragmentManager.findFragmentByTag("blueappsoftware.mybakingtips.fragmentDetailMob");
        if (prevFreg!= null){
            fragmentTransaction.remove(prevFreg);
        }

        bundle.putInt("stepposition", step_position);
        FragmentDetailMob fragmentDetailMob = new FragmentDetailMob();
        fragmentDetailMob.setArguments(bundle);

        fragmentTransaction.add( R.id.fragmentContainer, fragmentDetailMob, "blueappsoftware.mybakingtips.fragmentDetailMob");
        fragmentTransaction.commit();


    }

    public int getItemposition(){
        return  item_position;
    }

    public int getStep_position(){ return step_position;}
}
