package walkatheri.mybakingtips;

import java.util.List;

/**
 * Created by Jaink on 19-08-2017.
 */

public class Model  {

    public static final int IMAGE_TYPE =1;

    public String title, subtitle;
    public int type;
    public List<Recipe> recipeList;

    public Model ( int mtype, List<Recipe> mrecipe, String mtitle, String msubtitle  ){

        this.title = mtitle;
        recipeList = mrecipe;
        this.subtitle = msubtitle;
        this.type = mtype;
    }
}
