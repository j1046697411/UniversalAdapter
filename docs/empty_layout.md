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
                .addClickItemViewListener((options, viewHolderOwner) -> {
                    viewHolderOwner.getViewBinder()
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