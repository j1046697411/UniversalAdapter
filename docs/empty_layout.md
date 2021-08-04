# 空布局

## 1、简单的空布局

```java
public class SimpleEmptyLayoutView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public SimpleEmptyLayoutView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<String> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        IConfiguration.<String, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataProvider(dataBlockProvider)
                .setDataClassifier((configuration, data, position) -> 1)
                .plugin(AutomaticNotificationPlugin.of())

                //空布局插件
                .plugin(IEmptyLayoutManager.of(R.layout.item_loading_view))

                .registered(new ModulesViews.TestModule())

                //空布局点击事件监听
                .addClickItemViewListener((options, viewHolderOwner) -> {
                    viewHolderOwner.getViewBinder()
                            .setVisibility(R.id.pb_progressBar, View.VISIBLE)
                            .setText(R.id.tv_msg, R.string.loading);
                    mainHandler.postDelayed(() -> add(dataBlockProvider), 2000);
                }, IMatchPolicy.ofItemTypes(IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE))

                .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))

                .build(recyclerView);
    }

    private void add(DataBlockProvider<String> dataBlockProvider) {
        for (int i = 0; i < 100; i++) {
            dataBlockProvider.add("空布局");
        }
    }

}
```

## 2、使用module实现空布局

```java
public class EmptyLayoutView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    private final DataBlockProvider<Animation> dataBlockProvider;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public EmptyLayoutView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
        dataBlockProvider = DataBlockProviders.dataBlockProvider();
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        IConfiguration.<Animation, IViewHolder>builder(IViewHolderFactory.ofDefault())
                .setDataProvider(dataBlockProvider)
                .setDataClassifier((configuration, data, position) -> 1)
                .plugin(AutomaticNotificationPlugin.of())

                //实现空布局插件
                .plugin(IEmptyLayoutManager.of(IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE, null, new EmptyModule<>((options, viewHolderOwner) -> {
                    viewHolderOwner.getViewBinder()
                            .setText(R.id.tv_msg, R.string.loading)
                            .setVisibility(R.id.pb_progressBar, View.VISIBLE);
                    mainHandler.postDelayed(() -> {
                        add(dataBlockProvider);
                    }, 2000);
                }, IEmptyLayoutManager.DEFAULT_EMPTY_LAYOUT_ITEM_VIEW_TYPE)))

                .registered(new AnimationModule(1))
                .build(recyclerView);
    }

    private void add(DataBlockProvider<Animation> dataBlockProvider) {
        ArrayList<Animation> animations = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Animation animation = new Animation(R.mipmap.animation_img1, "Hoteis in Rio de Janeiro", "" +
                    "He was one of Australia's most of distinguished artistes, renowned for his portraits", new Date());
            animations.add(animation);
        }
        dataBlockProvider.addAll(animations);
    }

    public static class EmptyModule<T> implements IModule<T, DefaultViewHolder> {

        private final OnClickItemViewListener<T, DefaultViewHolder> clickItemViewListener;
        private final int itemViewType;

        public EmptyModule(OnClickItemViewListener<T, DefaultViewHolder> clickItemViewListener, int itemViewType) {
            this.clickItemViewListener = clickItemViewListener;
            this.itemViewType = itemViewType;
        }

        @NonNull
        @Override
        public IOptions<T, DefaultViewHolder> setup(@NonNull  IConfiguration<?, ?> configuration, @NonNull IDataGetter<T> dataGetter) {
            return configuration.options(this, IViewHolderFactory.DEFAULT_EMPTY_LAYOUT_VIEW_HOLDER_FACTORY, dataGetter)
                    .createItemView(R.layout.item_loading_view, itemViewType)
                    .addClickItemViewListener(clickItemViewListener, itemViewType)
                    .build();
        }
    }
}
```