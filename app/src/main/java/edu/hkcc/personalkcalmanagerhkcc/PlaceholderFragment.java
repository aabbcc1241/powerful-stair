package edu.hkcc.personalkcalmanagerhkcc;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static List<FragmentVisibleMonitorThread> fragmentVisibleMonitorThreads = new ArrayList<FragmentVisibleMonitorThread>();

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        addToFragmentVisibleMonitorList(fragment);
        return fragment;
    }

    private static void addToFragmentVisibleMonitorList(PlaceholderFragment fragment) {
        FragmentVisibleMonitorThread newThread = new FragmentVisibleMonitorThread(fragment);
        newThread.start();
        fragmentVisibleMonitorThreads.add(newThread);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View rootView = inflater.inflate(R.layout.fragment_main,
        // container, false);
        int resid = ResLinker.getFragmentLayoutId(getArguments().getInt(ARG_SECTION_NUMBER));
        View rootView = inflater.inflate(resid, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
            Log.w("Debug", "PlaceholderFragment.onAttach:ARG_SECTION_NUMBER=" + getArguments().getInt(ARG_SECTION_NUMBER));
        } catch (NullPointerException e) {

        }
    }

    private static class FragmentVisibleMonitorThread extends Thread {
        private final Fragment fragment;
        private boolean wasVisible = false;

        FragmentVisibleMonitorThread(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void run() {
            while (fragment != null) {
                if (fragment.isVisible())
                    if (!wasVisible) {
                        Log.w("debug", "fragment " + fragment.getArguments().getInt(ARG_SECTION_NUMBER) + " is visible now");
                        ResLinker.loadContent(MainActivity.currentActivity, fragment.getArguments().getInt(ARG_SECTION_NUMBER));
                    }
                wasVisible = fragment.isVisible();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
