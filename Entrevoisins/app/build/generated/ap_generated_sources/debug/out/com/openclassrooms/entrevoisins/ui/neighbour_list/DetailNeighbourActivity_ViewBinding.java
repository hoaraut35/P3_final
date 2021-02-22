// Generated code from Butter Knife. Do not modify!
package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.openclassrooms.entrevoisins.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailNeighbourActivity_ViewBinding implements Unbinder {
  private DetailNeighbourActivity target;

  private View view7f080055;

  @UiThread
  public DetailNeighbourActivity_ViewBinding(DetailNeighbourActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailNeighbourActivity_ViewBinding(final DetailNeighbourActivity target, View source) {
    this.target = target;

    View view;
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_view, "field 'mToolbar'", Toolbar.class);
    target.mAvatar = Utils.findRequiredViewAsType(source, R.id.v_image, "field 'mAvatar'", ImageView.class);
    target.mNameNeighbour = Utils.findRequiredViewAsType(source, R.id.v_name_general, "field 'mNameNeighbour'", TextView.class);
    target.mNameNeighbour2 = Utils.findRequiredViewAsType(source, R.id.v_name_secondary, "field 'mNameNeighbour2'", TextView.class);
    target.mNameNeighbour3 = Utils.findRequiredViewAsType(source, R.id.v_network_name, "field 'mNameNeighbour3'", TextView.class);
    target.mAdresse = Utils.findRequiredViewAsType(source, R.id.address, "field 'mAdresse'", TextView.class);
    target.mPhone = Utils.findRequiredViewAsType(source, R.id.myphone_view, "field 'mPhone'", TextView.class);
    target.mAbout = Utils.findRequiredViewAsType(source, R.id.myText_view, "field 'mAbout'", TextView.class);
    view = Utils.findRequiredView(source, R.id.float_button, "field 'mFloatBtn' and method 'updateNeighbour'");
    target.mFloatBtn = Utils.castView(view, R.id.float_button, "field 'mFloatBtn'", FloatingActionButton.class);
    view7f080055 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.updateNeighbour();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailNeighbourActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mToolbar = null;
    target.mAvatar = null;
    target.mNameNeighbour = null;
    target.mNameNeighbour2 = null;
    target.mNameNeighbour3 = null;
    target.mAdresse = null;
    target.mPhone = null;
    target.mAbout = null;
    target.mFloatBtn = null;

    view7f080055.setOnClickListener(null);
    view7f080055 = null;
  }
}
