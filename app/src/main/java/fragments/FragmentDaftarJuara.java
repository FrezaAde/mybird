package fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mybird.R;

/**
 * Created by canc on 06/10/2017.
 */
@SuppressLint("NewApi")
public class FragmentDaftarJuara extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private WebView webView;

    private SwipeRefreshLayout swlprofile;
    private String URL = "http://pms.can.web.id/index.php?component=champs&action=webview_list";

    @SuppressLint({ "NewApi","CutPaste"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daftarjuara, container, false);
        swlprofile = (SwipeRefreshLayout) view.findViewById(R.id.swlprofile);

        getActivity().setTitle("Daftar Juara");
        webView=(WebView)view.findViewById(R.id.WVChampion);
        swlprofile.setOnRefreshListener(this);
        swlprofile.setRefreshing(true);
        wvget();
        return view;
    }

    @Override
    public void onRefresh() {
        wvget();
    }

    private void wvget() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading Data", "Harap menunggu", false, false);
        loading.show();
        webView.loadUrl(URL);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        swlprofile.setRefreshing(false);
        loading.dismiss();
    }
}
