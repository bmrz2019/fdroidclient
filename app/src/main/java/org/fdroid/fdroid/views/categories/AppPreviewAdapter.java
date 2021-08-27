package org.fdroid.fdroid.views.categories;

import android.database.Cursor;
import android.view.ViewGroup;

import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

class AppPreviewAdapter extends RecyclerView.Adapter<AppCardController> {

    private Cursor cursor;
    private final AppCompatActivity activity;

    AppPreviewAdapter(AppCompatActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public AppCardController onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppCardController(activity, activity.getLayoutInflater()
                .inflate(R.layout.app_card_normal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppCardController holder, int position) {
        cursor.moveToPosition(position);
        holder.bindApp(new App(cursor));
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public void setAppCursor(Cursor cursor) {
        if (this.cursor == cursor) {
            //don't notify when the cursor did not change
            return;
        }
        this.cursor = cursor;
        notifyDataSetChanged();
    }
}