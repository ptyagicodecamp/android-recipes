package org.pcc.ViewPagerInFragment;

/**
 * Created by ptyagi on 3/10/17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditorFragment extends Fragment {
    private static final String KEY_POSITION="position";

    static EditorFragment newInstance(int position) {
        EditorFragment frag=new EditorFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position) {
        return(String.format(ctxt.getString(R.string.app_name), position + 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.editor, container, false);
        EditText editor=(EditText)result.findViewById(R.id.editor);
        int position=getArguments().getInt(KEY_POSITION, -1);

        editor.setHint(getTitle(getActivity(), position));

        return(result);
    }
}
