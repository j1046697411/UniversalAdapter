# DataBinding 的支持

DataBinding在框架内没有默认的支持和实现，但是该框架需要支持该方式却十分简单只需要自己定义一个实现了
IViewHolder接口的类即可。

### 1、定义DataBindingViewHolder

```java

public static class DataBindingViewHolder<VDB extends ViewDataBinding> implements IViewHolder {

    private final VDB dataBinding;

    public DataBindingViewHolder(View itemView) {
        this.dataBinding = DataBindingUtil.bind(itemView);
    }

    public VDB getDataBinding() {
        return dataBinding;
    }
}

```

### 2、以Module方式使用
```java
public class DataBindingModule implements IModule<DataBindingModel, DataBindingModule.DataBindingViewHolder<ItemMovieBinding>> {
    private final Activity activity;
    private final int[] itemViewTypes;

    public DataBindingModule(Activity activity, int... itemViewTypes) {
        this.itemViewTypes = itemViewTypes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public IOptions<DataBindingModel, DataBindingViewHolder<ItemMovieBinding>> setup(@NonNull IConfiguration<?, ?> configuration, @NonNull IDataGetter<DataBindingModel> dataGetter) {
        return configuration.options(this, (options, itemView, itemViewType) -> new DataBindingViewHolder<>(itemView), dataGetter)
                .createItemView(R.layout.item_movie, itemViewTypes)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    viewHolder.getDataBinding().setDataBindingModel(data);
                }, itemViewTypes)
                .addChildClickItemViewListener(IBindListener.ofClicks(R.id.btn_buy), (options, viewHolderOwner) -> {
                    int index = viewHolderOwner.getContext().getAdapterPosition();
                    DataBindingModel model = options.getDataGetter().getData(index);
                    Toast.makeText(activity, "buy " + model.getTitle() + "(" + model.getPrice() + ")", Toast.LENGTH_SHORT).show();
                }, IMatchPolicy.MATCH_POLICY_ALL)
                .build();
    }
}
```

### 3、直接使用
```java
public class DataBindingView2 extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    public DataBindingView2(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        DataBlockProvider<DataBindingModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        add(dataBlockProvider);

        IConfiguration
                .<DataBindingModel, DataBindingModule.DataBindingViewHolder<ItemMovieBinding>>builder((options, itemView, itemViewType) -> new DataBindingModule.DataBindingViewHolder<>(itemView))
                .createItemView(R.layout.item_movie)
                .setDataProvider(dataBlockProvider)
                .dataBindingByItemViewTypes((context, viewHolder, data) -> {
                    viewHolder.getDataBinding().setDataBindingModel(data);
                })
                .build(recyclerView);
    }

    public void add(Collection<DataBindingModel> dataBindingModels){
        Random random = new Random();
        for (int i = 0; i < 100; i ++){
            dataBindingModels.add(new DataBindingModel(R.mipmap.databinding_img, "card", 0, "This card is exciting", random.nextInt(5) + 5));
        }

    }
}
```