package com.rh.stravamate.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rh.stravamate.R;
import com.rh.stravamate.model.util.Constants;

/**
 * A simple {@link BaseFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AuthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AuthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthFragment extends BaseFragment {


    WebView webView;
    private OnFragmentInteractionListener mListener;

    public AuthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AuthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuthFragment newInstance() {
        AuthFragment fragment = new AuthFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =  inflater.inflate(R.layout.fragment_auth, container, false);

        webView = result.findViewById(R.id.webview);
        load();
        return result;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    String getLogTag() {
        return AuthFragment.class.getSimpleName();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onAuthSuccess(String code);
    }

    void load() {
        webView.setWebViewClient(createClient());
        webView.loadUrl(buildAuthUrl());
    }

    String buildAuthUrl() {
        return " https://www.strava.com/oauth/authorize?client_id=" + Constants.ID
                + "&response_type=code&redirect_uri=http://localhost/token_exchange.php&approval_prompt=force";
    }

    WebViewClient createClient() {
        return new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return processUrl(url);
            }
        };
    }

    boolean processUrl(String url) {
        boolean result = false;
        logging.d(getTag(), url);
        String[] parameters = url.split("&");
        if (parameters.length > 0) {
            for(String parameter : parameters) {
                String code = extractCode(parameter);
                if (!TextUtils.isEmpty(code)) {
                    result = true;
                    mListener.onAuthSuccess(code);
                    break;
                }
            }
        }
        return result;
    }

    String extractCode(String parameter) {
        String result = null;
        String[] values = parameter.split("=");
        if (values.length == 2) {
            if ("code".equalsIgnoreCase(values[0])) {
                result = values[1];
            }
        }
        return result;
    }
}
