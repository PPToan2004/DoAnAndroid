package com.example.doanandroid.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.doanandroid.DB.Truyen.Truyen;
import com.example.doanandroid.DB.Truyen.TruyenActivity;
import com.example.doanandroid.DB.Truyen.TruyenAdapter;
import com.example.doanandroid.DB.Truyen.TruyenDataQuery;
import com.example.doanandroid.DB.Truyen.Truyen_Detail_Activity;
import com.example.doanandroid.DB.Truyen.UserTruyenAdapter;
import com.example.doanandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements UserTruyenAdapter.UserCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }
        // Required empty public constructor

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    RecyclerView rcListCode;
    ArrayList<Truyen> lstUser;
    UserTruyenAdapter userAdapter;
    TextInputLayout textInputLayoutTruyen;
    TextInputEditText edSearchTruyenC;
    ImageButton ibSearchTruyenC;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcListCode = view.findViewById(R.id.rvList);
        textInputLayoutTruyen = view.findViewById(R.id.textInputLayoutTruyen);
        edSearchTruyenC = view.findViewById(R.id.edSearchTruyen);
        ibSearchTruyenC = view.findViewById(R.id.ibSearchTruyen);

        lstUser = new ArrayList<>();
        lstUser = TruyenDataQuery.getAll(getActivity());
        userAdapter = new UserTruyenAdapter(lstUser);
        userAdapter.setUserCallback((UserTruyenAdapter.UserCallback) this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcListCode.setAdapter(userAdapter);
        rcListCode.setLayoutManager(linearLayoutManager);

        ibSearchTruyenC.setOnClickListener(v -> SearchTruyen());
        return view;
    }
    void SearchTruyen()
    {
        String truyenName = edSearchTruyenC.getText().toString().trim();
        if (truyenName.isEmpty())
            edSearchTruyenC.setError("Tên truyện để trống");
        else
        {
            lstUser = TruyenDataQuery.getTruyenByName(getActivity(), truyenName);
            userAdapter = new UserTruyenAdapter(lstUser);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            rcListCode.setAdapter(userAdapter);
            rcListCode.setLayoutManager(linearLayoutManager);

        }
    }
    @Override
    public void ItemNameClicked(Truyen user, int position) {
        Fragment newFragment = new User_Truyen_DetailFragment();
        // consider using Java coding conventions (upper first char class names!!!)
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.main_fragment, newFragment);
        transaction.addToBackStack(null);

        Bundle bundle = new Bundle();
        bundle.putSerializable("TruyenDetail", user);
        newFragment.setArguments(bundle);
        // Commit the transaction
        transaction.commit();
    }

}