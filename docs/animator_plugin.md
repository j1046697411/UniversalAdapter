# 进场动画


```

plugin(AnimatorPlugin.slideInBottom(IBindPolicy.BIND_POLICY_ALL))

```

下面是完整示例
```java

public class AnimationView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    //不同的动画效果
    private final Map<String, IAnimatorFactory<DefaultViewHolder>> animatorFactories = new HashMap<String, IAnimatorFactory<DefaultViewHolder>>() {{
        put("AlphaIn", IAnimatorFactory.alphaIn(0));//
        put("ScaleIn", IAnimatorFactory.scaleIn(IAnimatorFactory.ScaleType.XY, 0.5f));
        put("SlideInBottom", IAnimatorFactory.slideInBottom());
        put("SlideInLeft", IAnimatorFactory.slideInLeft());
        put("SlideInRight", IAnimatorFactory.slideInRight());
    }};

    //初始化动画插件
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

                //设置动画插件
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
            animatorPlugin.setAnimatorFactory(animatorFactory);//修改动画效果
            configuration.rebind(recyclerView);//重新绑定recyclerView
        });
        activityRevyclerViewBinding.msSpinner.setSelectedIndex(0);
        activityRevyclerViewBinding.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            animatorPlugin.setAnimationFirstOnly(isChecked);//仅限第一次进入动画开关
            configuration.getAdapterObservable().notifyDataSetChanged();//通知数据改变
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

```