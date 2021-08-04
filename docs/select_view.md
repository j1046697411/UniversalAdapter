# 选择插件示例

```java
public class SelectView extends AbstractView<UniversalRecyclerViewActivity> implements UniversalRecyclerViewActivity.IUniversalRecyclerView {

    SelectPlugin<UniversalModel, IViewHolder> selectPlugin;

    public SelectView(@NonNull UniversalRecyclerViewActivity parentView) {
        super(parentView);
    }

    @Override
    public void initialize(@NonNull ActivityRevyclerViewBinding activityRevyclerViewBinding, @NonNull RecyclerView recyclerView, @NonNull UniversalRecyclerViewActivity.IUniversalRecyclerViewModel universalRecyclerViewModel) {
        menu(activityRevyclerViewBinding);
        //初始化选择插件
        selectPlugin = SelectPlugin.of(SelectPlugin.SelectMode.MULTIPLE, (context, viewHolder, data) -> {
            //更新选择状态更新item显示样式
            context.getViewBinder().setChecked(R.id.cb_select, data.isChecked());
        }, IMatchPolicy.ofItemTypes(1));

        DataBlockProvider<UniversalModel> dataBlockProvider = DataBlockProviders.dataBlockProvider();
        add(dataBlockProvider);

        IConfiguration.<UniversalModel, IViewHolder>builder(IViewHolderFactory.ofDefault())
                //设置选择插件
                .plugin(selectPlugin)
                //修改 LayoutManager
                .layoutManager(ILayoutManagerFactory.gridLayoutManager(3))
                .setDataProvider(dataBlockProvider)
                .registered(new SelectModule(parentView, 1), Functions.universal())
                .build(recyclerView);
    }

    private void menu(ActivityRevyclerViewBinding activityRevyclerViewBinding) {
        activityRevyclerViewBinding.llHeader.setVisibility(View.VISIBLE);
        activityRevyclerViewBinding.msSpinner.setItems("全选", "取消", "反选", "获取结果");
        activityRevyclerViewBinding.tvHeaderMsg.setText("SelectMode");
        activityRevyclerViewBinding.switchButton.setText( SelectPlugin.SelectMode.SINGLE.name(), SelectPlugin.SelectMode.MULTIPLE.name());
        activityRevyclerViewBinding.switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                //切换单选模式
                selectPlugin.setSelectMode(SelectPlugin.SelectMode.SINGLE);
            }else{
                //切换多选模式
                selectPlugin.setSelectMode(SelectPlugin.SelectMode.MULTIPLE);
            }
            Toast.makeText(parentView.getApplication(), "切换:" + selectPlugin.getSelectMode(), Toast.LENGTH_SHORT).show();
        });

        activityRevyclerViewBinding.msSpinner.setOnItemSelectedListener((view, position, id, item) -> {
            if (position == 0) {
                //全选
                selectPlugin.checkedAll();
            } else if (position == 1) {
                //取消选择
                selectPlugin.uncheckedAll();
            } else if (position == 2) {
                //反选全部
                selectPlugin.reverseAll();
            } else {
                //获取选择结果
                List<UniversalModel> universalModels = selectPlugin.getSelectResult();
                Toast.makeText(parentView.getApplication(), "选中: " + universalModels.size() + "个", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void add(Collection<UniversalModel> models) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            models.add(UniversalModel.build(
                    new Card(random.nextBoolean() ? R.mipmap.click_head_img_0 : R.mipmap.click_head_img_1, "card " + (i + 1), "简单可以点击的卡片")
            ).setSpanSize(SpanSize.ONE).setItemViewType(1).build());
        }
    }

}

```