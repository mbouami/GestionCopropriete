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
import android.widget.ListView;
import android.widget.TextView;

import com.bouami.exemple.copropriete.Adapters.CotisationsAdapter;
import com.bouami.exemple.copropriete.Models.Coproprietaire;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.bouami.exemple.copropriete.DetailFragment.OnFragmentCotInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Cursor mcopcursor, mcotcursor;
    private Coproprietaire lecopro;
    private CotisationsAdapter mAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentCotInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2,Cursor c) {
        DetailFragment fragment = new DetailFragment(c);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment(Cursor c) {
        // Required empty public constructor
        this.mcopcursor = c;
        this.lecopro = new Coproprietaire(c);
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
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView titre = (TextView) getView().findViewById(R.id.titre_cotisation);
        titre.setText(lecopro.toString());
        int layout = R.layout.layout_versements;
        int[] mListItems = { R.id.cotisation, R.id.dateversement };
        String[] mListColumns ={CoproParametres.parametre.COLUMN_COTISATIONS_SOMME,CoproParametres.parametre.COLUMN_COTISATIONS_DONNEELE};
        mAdapter = new CotisationsAdapter(getActivity(),
                layout,
                null,
                mListColumns,
                mListItems,
                0);
        final ListView listecotisations= (ListView) getView().findViewById(R.id.listeversements);
        listecotisations.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, savedInstanceState, this);

        listecotisations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mcotcursor = (Cursor) listecotisations.getItemAtPosition(position);
/*                final Cursor cursor = (Cursor) listecopros.getItemAtPosition(position);*/
                mListener.onFragmentCotInteraction(mcotcursor);
                // Set the item as checked to be highlighted when in two-pane layout
                listecotisations.setItemChecked(position, true);
            }
        });

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Cursor c) {
        if (mListener != null) {
            mListener.onFragmentCotInteraction(c);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentCotInteractionListener) activity;
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
        String[] mSelectionArgs = {""};
        mSelectionArgs[0] = String.valueOf(lecopro.getId());
        return new CursorLoader(
                this.getActivity(),
                CoproParametres.parametre.CONTENT_URI_EN_COURS,
                CoproParametres.parametre.COTISATIONS_PROJECTIONS,
                CoproParametres.parametre.COLUMN_COTISATIONS_KEY_COPROPRIETAIRE + " = ? ",
                mSelectionArgs,
                CoproParametres.parametre.COLUMN_COTISATIONS_DONNEELE);
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
    public interface OnFragmentCotInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentCotInteraction(Cursor c);
    }

}
