package blueappsoftware.mybakingtips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jaink on 23-08-2017.
 */

public class FragmentOne extends Fragment {

    ListView mainList;
    LinearLayout ingre_mainLayout;
    ArrayList<String> steplist = new ArrayList<>();
    int fitemposition;

    public interface onSomeEventListener{
        public void SomeEvent(int position );
    }
    onSomeEventListener someEventListener;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);

        someEventListener = (onSomeEventListener) (SelectReceipe) getActivity();

        mainList = (ListView) view.findViewById(R.id.listview1);
        ingre_mainLayout = (LinearLayout) view.findViewById(R.id.ingr_layout);
        fitemposition=   ((SelectReceipe) getActivity()).getItemposition();
       // ((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getSteps()
        for (int j=0; j<((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getIngredients().size(); j++){

            TextView textView = new TextView( inflater.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 2, 10, 2);
            textView.setLayoutParams(params);
            textView.setPadding(2,5,2,5);

            textView.setText( "\u25CF " + ((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getIngredients().get(j).getIngredient() +
                   " ("+ ((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getIngredients().get(j).getQuantity()
            +" "+ ((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getIngredients().get(j).getMeasure()
            +")" );

            ingre_mainLayout.addView(textView);

        }


         for (int i =0; i<  ((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getSteps().size(); i++){

             try {
                 steplist.add( String.valueOf(i)+" "+ ((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getSteps().get(i).getShortDescription().toString());

             } catch (Exception e) {
                 e.printStackTrace();
             }

             Log.e("Frag One ", "  step i = "+ i + "  value  "+ ((SelectReceipe) getActivity()).sListRecipe.get(fitemposition).getSteps().get(i).getShortDescription().toString());
        }

        ArrayAdapter<String>  adapter = new ArrayAdapter<String>( inflater.getContext(), android.R.layout.simple_list_item_1
                , steplist );

        mainList.setAdapter(adapter);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e("Frag One ", "  list step clicked  = "+ position );
                someEventListener.SomeEvent(position);
            }
        });



        return view;  //super.onCreateView(inflater, container, savedInstanceState);
    }
}
