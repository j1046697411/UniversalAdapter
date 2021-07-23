package org.jzl.android.recyclerview.core;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import org.jzl.android.mvvm.view.AbstractView;
import org.jzl.android.recyclerview.R;
import org.jzl.android.recyclerview.UniversalRecyclerViewActivity;
import org.jzl.android.recyclerview.core.animation.Animation;
import org.jzl.android.recyclerview.core.animation.AnimationModule;
import org.jzl.android.recyclerview.core.plugins.AnimatorPlugin;
import org.jzl.android.recyclerview.core.vh.DefaultViewHolder;
import org.jzl.android.recyclerview.databinding.ActivityRevyclerViewBinding;
import org.jzl.android.recyclerview.util.datablock.DataBlockProvider;
import org.jzl.android.recyclerview.util.datablock.DataBlockProviders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    //"AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight"
    private final Map<String, IAnimatorFactory<DefaultViewHolder>> animatorFactories = new HashMap<String, IAnimatorFactory<DefaultViewHolder>>() {{
        put("AlphaIn", IAnimatorFactory.alphaIn(0));
        put("ScaleIn", IAnimatorFactory.scaleIn(IAnimatorFactory.ScaleType.XY, 0.5f));
        put("SlideInBottom", IAnimatorFactory.slideInBottom());
        put("SlideInLeft", IAnimatorFactory.slideInLeft());
        put("SlideInRight", IAnimatorFactory.slideInRight());
    }};
    private final AnimatorPlugin<Animation, DefaultViewHolder> animatorPlugin = AnimatorPlugin.alphaIn(0.5f, IBindPolicy.BIND_POLICY_ALL);
    private final DataBlockProvider<Animation> animations = DataBlockProviders.dataBlockProvider();
    private IConfiguration<Animation, DefaultViewHolder> configuration;
    private RecyclerView recyclerView;

    public AnimationView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        this.recyclerView = recyclerView;
        initMenu(activityRevyclerViewBinding);
        addAnimation(animations);
        configuration = IConfiguration.<Animation>builder()
                .setDataProvider(animations)
                .registered(new AnimationModule(1))
                .plugin(animatorPlugin)
                .build(recyclerView);
    }

    private void initMenu(ActivityRevyclerViewBinding activityRevyclerViewBinding) {
        activityRevyclerViewBinding.llHeader.setVisibility(View.VISIBLE);
        activityRevyclerViewBinding.msSpinner.setItems(
                new ArrayList<>(animatorFactories.keySet())
        );
        activityRevyclerViewBinding.msSpinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {
            IAnimatorFactory<DefaultViewHolder> animatorFactory = animatorFactories.get(item);
            animatorPlugin.setAnimatorFactory(animatorFactory);
            configuration.rebind(recyclerView);
        });
        activityRevyclerViewBinding.msSpinner.setSelectedIndex(0);
        activityRevyclerViewBinding.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            animatorPlugin.setAnimationFirstOnly(isChecked);
            configuration.getAdapterObservable().notifyDataSetChanged();
        });
    }

    private void addAnimation(Collection<Animation> collection) {
        for (int i = 0; i < 100; i++) {
            Animation animation = new Animation(R.mipmap.animation_img1, "Hoteis in Rio de Janeiro", "" +
                    "He was one of Australia's most of distinguished artistes, renowned for his portraits", new Date());
            collection.add(animation);
        }
    }

}
