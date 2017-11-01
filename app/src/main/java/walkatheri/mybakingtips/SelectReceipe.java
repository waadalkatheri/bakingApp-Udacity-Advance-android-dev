package walkatheri.mybakingtips;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.List;

/**
 * Created by Jaink on 23-08-2017.
 */

public class SelectReceipe extends AppCompatActivity implements FragmentOne.onSomeEventListener {

     List<Recipe> sListRecipe;
    private int itemposition;
    private String TAG = "SelectRecipe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sListRecipe = MainActivity.getList();

        Intent i = getIntent();
        itemposition =  i.getExtras().getInt("itemPosition");
        Log.e(TAG, " itemposition "+ String.valueOf( itemposition ) +"  value "+ sListRecipe.get(itemposition).getName().toString()) ;

        setContentView(R.layout.selectrecipe);

        // set the activity title
        setTitle(sListRecipe.get(itemposition).getName());
        // • = \u2022, ● = \u25CF, ○ = \u25CB, ▪ = \u25AA, ■ = \u25A0, □ = \u25A1, ► = \u25BA

    }

    public int getItemposition(){
        return  itemposition;
    }

    @Override
    public void SomeEvent(int position) {

        Log.e("SelectRercipe"," frag step position "+ position );
        if (isTablet()){
            /// create second fragment with step details...
            Bundle bundle = new Bundle();
            bundle.putInt("item_position", itemposition);
            bundle.putInt("step_position", position);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            Fragment prevFreg = fragmentManager.findFragmentByTag("blueappsoftware.mybakingtips.fragmenttwo");
            if (prevFreg!= null){
                fragmentTransaction.remove(prevFreg);
            }

            Fragmenttwo objfragtwo = new Fragmenttwo();
            objfragtwo.setArguments(bundle);

            fragmentTransaction.add( R.id.idfragmentContainer, objfragtwo, "blueappsoftware.mybakingtips.fragmenttwo");
            fragmentTransaction.commit();

        }else {
            // create new activity
            Intent intent = new Intent(SelectReceipe.this, DetailRecipe.class);
            intent.putExtra("item_position", itemposition);
            intent.putExtra("step_position", position);
            startActivity(intent);

        }

    }

    public Boolean isTablet(){
      try {
          DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
          float screenwidth = dm.widthPixels / dm.xdpi;
          float screenheight = dm.heightPixels / dm.ydpi;

          double size = Math.sqrt(Math.pow(screenwidth, 2) + Math.pow(screenheight, 2));
          // table device shoud have 6 inch  or grater
          return size > 6;
      }catch (Exception e){
          Log.e("select recipe "," screen size error "+ e.toString());
          return false;
      }
    }


}
