package com.harmony.harmony;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harmony.harmony.adapters.ComposerAdapter;
import com.harmony.harmony.models.Composer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;


public class ComposerListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mComposerRecyclerView;
    private ComposerAdapter mAdapter;
    private ArrayList<Composer> mComposerCollection;

    public ComposerListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ComposerListFragment newInstance(String param1, String param2) {
        ComposerListFragment fragment = new ComposerListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_composer_list, container, false);



        init(inflatedView);
        new FetchDataTask().execute();

        return inflatedView;
    }

    private void init(View inflatedView) {
        mComposerRecyclerView = inflatedView.findViewById(R.id.composerlist_recycler);

        //mComposerRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mComposerRecyclerView.setLayoutManager(layoutManager);

        mComposerRecyclerView.setHasFixedSize(true);
        //  mComposerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mComposerCollection = new ArrayList<>();
        mAdapter = new ComposerAdapter(mComposerCollection, this);
        mComposerRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ComposerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, long id) {
                //Toast.makeText(getActivity(),"CLICKED "+mVideoCollection.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("key","abc");

                VideoDetailFragment fragment2 = new VideoDetailFragment();
                fragment2.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment2);
                fragmentTransaction.commit();

            }
        });
    }


    public class FetchDataTask extends AsyncTask<Void, Void, Void> {
        private String mComposerString;

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri builtUri = Uri.parse(getString(R.string.composers_api));
            URL url;
            try {
                url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //urlConnection.setRequestProperty("user-key", "e07dabfcec9fe90023b29d8c5934c615");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    //Nothing to do
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                mComposerString = buffer.toString();
                //JSONObject jsonObject = new JSONObject(mVideoString);

                //Log.v("Response", jsonObject.toString());

                // JSONArray videosArray = jsonObject.getJSONArray("videos");
                //JSONArray videosArray =  jsonObject.getJSONArray("");
                JSONArray jsonarray = new JSONArray(mComposerString);

                //list = new ArrayList<>();
                for (int i = 0; i < jsonarray.length(); i++) {


                    String composerName;
                    String composerImageUrl;




                    JSONObject jComposer = (JSONObject) jsonarray.get(i);
                    //jVideo = jVideo.getJSONObject("video");
                    //jVideo = jVideo.getJSONObject("");


                    composerName= jComposer.getString("NAME");
                    composerImageUrl = jComposer.getString("IMAGEURL");



                    Composer composer = new Composer();
                    composer.setComposerName(composerName);
                    composer.setComposerImageUrl(composerImageUrl);



                    mComposerCollection.add(composer);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        //Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
        }

    }

}