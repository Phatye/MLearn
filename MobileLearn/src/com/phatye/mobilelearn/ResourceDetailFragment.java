package com.phatye.mobilelearn;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.phatye.mobilelearn.dummy.DummyContent;
import com.phatye.mobilelearn.itemendpoint.model.Item;

/**
 * A fragment representing a single Resource detail screen. This fragment is
 * either contained in a {@link ResourceListActivity} in two-pane mode (on
 * tablets) or a {@link ResourceDetailActivity} on handsets.
 */
public class ResourceDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;
	String category;
	ProgressDialog dialog;
	Item[] items = null;
	View rootView;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ResourceDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
			category = getArguments().getString("category");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_resource_detail,
				container, false);

		if(mItem != null){
			GetResourcesAsyncTask asyncTask = new GetResourcesAsyncTask
					(category, mItem.contentType, this);
			asyncTask.execute();
		}

		return rootView;
	}

	public void handleGetResourcesResult(Item[] result) {
		Item[] items = result;	
		if(items != null){
			ListView listView = ((ListView) rootView.findViewById(R.id.resource_list));
			
			ArrayAdapterItem adapter = new ArrayAdapterItem(getActivity(), 
					R.layout.list_view_row_item, items);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListenerListViewItem
					(mItem.contentType, category));				
		}
	}
}
