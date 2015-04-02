package com.bouami.exemple.copropriete;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.bouami.exemple.copropriete.Adapters.CoproprietairesAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoproprietairesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoproprietairesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoproprietairesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private int selectedItem = -1;
    protected Cursor mcursor;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private CoproprietairesAdapter mAdapter;
    private Bundle bundle;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener, mCotisationListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoproprietairesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoproprietairesFragment newInstance(String param1, String param2) {
        CoproprietairesFragment fragment = new CoproprietairesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CoproprietairesFragment() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coproprietaires, container, false);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.bundle = savedInstanceState;
        String[] mListColumns ={CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM};
        int[] mListItems = { android.R.id.text1};
        int layout = android.R.layout.simple_list_item_1;
        AutoCompleteTextView civi;
        civi = (AutoCompleteTextView) getView().findViewById(R.id.civilite);
        String[] civilites = getResources().getStringArray(R.array.liste_civilites);
        ArrayAdapter adapter = new ArrayAdapter(getView().getContext(),android.R.layout.simple_list_item_1,civilites);
        civi.setAdapter(adapter);
        mAdapter = new CoproprietairesAdapter(getActivity(),
                layout,
                null,
                mListColumns,
                mListItems,
                0);
        final ListView listecopros = (ListView) getView().findViewById(R.id.listecoproprietaires);
        listecopros.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, savedInstanceState, this);

        listecopros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = position;
                mcursor = (Cursor) listecopros.getItemAtPosition(position);
/*                final Cursor cursor = (Cursor) listecopros.getItemAtPosition(position);*/
                mListener.onFragmentInteraction(mcursor);
                // Set the item as checked to be highlighted when in two-pane layout
                listecopros.setItemChecked(position, true);
            }
        });

        listecopros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
/*                selectedItem = position;
                final Cursor cursor = (Cursor) listecopros.getItemAtPosition(position);*/
/*                Coproprietaire lecopro = new Coproprietaire(mcursor);
                show("le copropriétaire sélectionné : " + lecopro.toString());
                registerForContextMenu(view);
                return true;*/
                mCotisationListener.onFragmentCotisationInteraction(mcursor);
                return true;
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Cursor c) {
        if (mListener != null) {
            mListener.onFragmentInteraction(c);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
            mCotisationListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentCotInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                this.getActivity(),
                CoproParametres.parametre.CONTENT_URI_EN_COURS,
                CoproParametres.parametre.COPROPRIETAIRE_PROJECTIONS,
                null,
                null,
                CoproParametres.parametre.COLUMN_COPROPRIETAIRE_NOM);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Cursor c);
        public void onFragmentCotisationInteraction(Cursor c);
    }

    private void show(String txt) {
        Toast.makeText(getActivity(), String.valueOf(txt), Toast.LENGTH_LONG).show();
    }
}
