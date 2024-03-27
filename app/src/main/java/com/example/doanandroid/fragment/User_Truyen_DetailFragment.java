package com.example.doanandroid.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanandroid.DB.Chapter.Chapter;
import com.example.doanandroid.DB.Chapter.ChapterAdapter;
import com.example.doanandroid.DB.Chapter.ChapterDataQuery;
import com.example.doanandroid.DB.Chapter.UserChapterAdapter;
import com.example.doanandroid.DB.Truyen.Truyen;
import com.example.doanandroid.DB.Truyen.UserTruyenAdapter;
import com.example.doanandroid.R;
import com.example.doanandroid.utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_Truyen_DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_Truyen_DetailFragment extends Fragment implements UserChapterAdapter.UserChapterCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Truyen truyen;
    Context context;

    public User_Truyen_DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_Truyen_DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static User_Truyen_DetailFragment newInstance(String param1, String param2) {
        User_Truyen_DetailFragment fragment = new User_Truyen_DetailFragment();
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
        Bundle bundle = this.getArguments();
        truyen = (Truyen) bundle.getSerializable("TruyenDetail");
    }
    RecyclerView rcListCode;
    ArrayList<Chapter> lstChapter;
    UserChapterAdapter chapterAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user__truyen__detail, container, false);
        ImageView ivAvatarC = view.findViewById(R.id.ivDeAvatar);
        TextView tvUserDeNameC = view.findViewById(R.id.tvDeName);
        TextView tvUserDeTypeC = view.findViewById(R.id.tvDeType);
        ImageButton imbBackC = view.findViewById(R.id.imbBackTD);
        String name = truyen.getName();
        String type = "Thể loại: " + truyen.getTheloai_Name();

        tvUserDeNameC.setText(name);
        tvUserDeTypeC.setText(type);
        context = getActivity();
        ivAvatarC.setImageBitmap(utils.convertToBitMapFromAssets(context, truyen.getAvatar()));

        rcListCode = view.findViewById(R.id.rvDeChapterList);

        lstChapter = new ArrayList<>();
        lstChapter = ChapterDataQuery.getAll(getActivity(), truyen.getId());
        chapterAdapter = new UserChapterAdapter(lstChapter);
        chapterAdapter.setChapterCallback((UserChapterAdapter.UserChapterCallback) this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcListCode.setAdapter(chapterAdapter);
        rcListCode.setLayoutManager(linearLayoutManager);

        // Gán sự kiện ClickListener cho ImageButton imbBackC
        imbBackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại Fragment HomeFragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack(); // Quay lại Fragment trước đó (ở đây là HomeFragment)
            }
        });
        return view;
    }

    @Override
    public void ItemNameClicked(Chapter chapter, int position) {
        Fragment newFragment = new Read_Chapter_Fragment();
        // consider using Java coding conventions (upper first char class names!!!)
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.main_fragment, newFragment);
        transaction.addToBackStack(null);

        Bundle bundle = new Bundle();
        bundle.putSerializable("ReadChapter", chapter);
        newFragment.setArguments(bundle);
        // Commit the transaction
        transaction.commit();
    }
}