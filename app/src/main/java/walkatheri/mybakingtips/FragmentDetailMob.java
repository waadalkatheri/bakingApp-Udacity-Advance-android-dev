package blueappsoftware.mybakingtips;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

/**
 * Created by Jaink on 28-08-2017.
 */

public class FragmentDetailMob extends Fragment {

    TextView stepdetails;
    int itemposition, stepposition;
    String videoURL, description;
    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;
    View view;
    String TAG ="FragmentDettaiMob";
    MediaSource videosource;

    public FragmentDetailMob getInstence(){
       return new FragmentDetailMob();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragmentdetailmob, container, false);

        stepdetails = (TextView) view.findViewById(R.id.txtstepdetail);
       /// get recipe select and step select
        itemposition = ((DetailRecipe)getActivity()).getItemposition();
       // stepposition = ((DetailRecipe)getActivity()).getStep_position();
        Bundle bundle = getArguments();
        stepposition = bundle.getInt("stepposition");

        Log.e(TAG, " steppostion "+ stepposition);
        // store in local string
        videoURL = ((DetailRecipe)getActivity()).dListRecipe.get(itemposition).getSteps().get(stepposition).getVideoURL();
        description = ((DetailRecipe)getActivity()).dListRecipe.get(itemposition).getSteps().get(stepposition).getDescription();
        /// set description and video
       if (videoURL==null){
           videoURL ="";
       }
        stepdetails.setText( description);
        /// set video from url
        Log.e(TAG,"step position "+ stepposition+ "  videoURl"+ videoURL + "--details---"+ description);

        exoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.exo_player_view);
        //Log.e(TAG, " exo player ini");
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        //Log.e(TAG, " exo player bandmeter");
    TrackSelector trackSelection = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        //Log.e(TAG, " exo player track");
    exoPlayer = ExoPlayerFactory.newSimpleInstance(inflater.getContext(), trackSelection);
    Uri videouri = Uri.parse(videoURL);
        ///Log.e(TAG, " exo player player");
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("MyBakingApp"+stepposition);
        //Log.e(TAG, " exo player datasou");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        //Log.e(TAG, " exo player extract");
            videosource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
        //Log.e(TAG, " exo player video");
            ///  bind player with view
            exoPlayerView.setPlayer(exoPlayer);
        //Log.e(TAG, " exo player setview");
            exoPlayer.prepare(videosource);
       // Log.e(TAG, " exo player prepa");
            ///  work  on api 16 and greater .... no start, pause resume  so use true for autoplay and false for stop
           // exoPlayer.setPlayWhenReady(false);
        Log.e(TAG, " exo player ready");
        return view;
    }

    @Override
    public void onStop() {
       // super.onStop();
        Log.e(TAG, "onstop " + stepposition);
        try {
            if (exoPlayer != null) {
                if (exoPlayer.getPlayWhenReady() && exoPlayer.getPlaybackState() != exoPlayer.STATE_IDLE) {
                    //exoPlayerPosition = exoPlayer.getCurrentPosition();
                }
                exoPlayer.prepare(null);
                //    exoPlayer.removeListener(mExoPlayerEventListener);
                videosource.releaseSource();
                exoPlayer.stop();
                exoPlayer.release();
                exoPlayer = null;

                Log.e(TAG, "onstop " + stepposition);
            }
        }catch (Exception e){
            Log.e(TAG, "onstop exception " + stepposition);
        }
        super.onStop();
        
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}



