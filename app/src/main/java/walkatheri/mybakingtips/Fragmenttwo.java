package blueappsoftware.mybakingtips;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
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

import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

/**
 * Created by Jaink on 23-08-2017.
 */

public class Fragmenttwo extends Fragment {

    TextView stepdetails;
    int itemposition, stepposition;
    String videoURL, description;
    SimpleExoPlayer exoPlayer;
    SimpleExoPlayerView exoPlayerView;

    public Fragmenttwo getInstance(){
        return new Fragmenttwo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentdetailmob, container, false);

        stepdetails = (TextView) view.findViewById(R.id.txtstepdetail);
        /// get recipe select and step select
       // itemposition = ((DetailRecipe) getActivity()).getItemposition();
        // stepposition = ((DetailRecipe)getActivity()).getStep_position();
        Bundle bundle = getArguments();
        stepposition = bundle.getInt("step_position");
        itemposition = bundle.getInt("item_position");

        Log.e("Fragmenttwo", " steppostion " + stepposition);
        // store in local string
        videoURL = ((SelectReceipe) getActivity()).sListRecipe.get(itemposition).getSteps().get(stepposition).getVideoURL();
        description = ((SelectReceipe) getActivity()).sListRecipe.get(itemposition).getSteps().get(stepposition).getDescription();
        /// set description and video
        stepdetails.setText(description);
        /// set video from url
        Log.e("fragmenttwo", "step position " + stepposition + "  videoURl" + videoURL + "--details---" + description);

        exoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.exo_player_view);

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelection = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        exoPlayer = ExoPlayerFactory.newSimpleInstance(inflater.getContext(), trackSelection);
        Uri videouri = Uri.parse(videoURL);
        try {
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("MyBakingApp");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            MediaSource videosource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            ///  bind player with view
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(videosource);
            ///  work  on api 16 and greater .... no start, pause resume  so use true for autoplay and false for stop
            exoPlayer.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.e("Fragmenttwo", " exo player exception " + e.toString());
        }
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayerView.removeAllViews();
        Log.e("fragmenttwo", "onstop " + stepposition);
    }
}