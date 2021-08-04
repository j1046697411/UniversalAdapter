# UniversalAdapter

UniversalAdapter 一个既可以很简单，又可以很复杂的通用适配器，该适配器致力于易用与可扩展。

## 使用

### 1、快速导包

### 2、[快速入门](./app/src/main/java/org/jzl/android/recyclerview/app/core/OptimizeCodeView.java)

```
DataBlockProvider<Object> dataBlockProvider = DataBlockProviders.dataBlockProvider();
IConfiguration.builder()
        .setDataProvider(dataBlockProvider)
        .createItemView(R.layout.item_animation)
        .dataBindingByItemViewTypes((context, viewHolder, data) -> {
            context.getViewBinder()
                    .setImageResource(R.id.iv_icon, R.mipmap.animation_img1)
                    .setText(R.id.tv_title, "OptimizeCode")
                    .setText(R.id.tv_refresh_time, DateUtils.format(new Date()))
                    .setText(R.id.tv_subtitle, "最简单的操作，实现最复杂的处理");
        })
        .build(recyclerView);

```

## 功能

- [快速实现多类型列表](./docs/multiple_types.md)

- [代码复用的module模块功能](./docs/Modules.md)

- [各类插件实现的基础接口](./RVUA/src/main/java/org/jzl/android/recyclerview/core/plugins/AutomaticNotificationPlugin.java) [IPlugin](./RVUA/src/main/java/org/jzl/android/recyclerview/core/IPlugin.java) 和 
  [IComponent](./RVUA/src/main/java/org/jzl/android/recyclerview/core/components/IComponent.java)

- [空布局管理器](./docs/empty_layout.md)（[EmptyLayoutManager](./RVUA/src/main/java/org/jzl/android/recyclerview/core/layout/IEmptyLayoutManager.java)）

- [item动画插件](./docs/animator_plugin.md)（[AnimatorPlugin](./RVUA/src/main/java/org/jzl/android/recyclerview/core/plugins/AnimatorPlugin.java)）

- 自动刷新数据插件（[AutomaticNotificationPlugin](./RVUA/src/main/java/org/jzl/android/recyclerview/core/plugins/AutomaticNotificationPlugin.java)），
  需要配合[DataBlockProvider](./RVUA/src/main/java/org/jzl/android/recyclerview/util/datablock)一起使用

```
IConfiguration.builder()
    //设置数据数据源
    .setDataProvider(DataBlockProviders.dataBlockProvider())
    //设置自动更新item插件
    .plugin(AutomaticNotificationPlugin.of())
    .build(recyclerView);
```

- [单选/多选插件](./docs/select_view.md)（[SelectPlugin](./RVUA/src/main/java/org/jzl/android/recyclerview/core/plugins/SelectPlugin.java)）—— 全选、反选、取消全选、单选等

- 添加头布局和脚布局，配合DataBlockProvider和多类型即可可轻松实现

- item和其子view长按和点击事件监听 —— 支持多事件监听和多模块重复监听

- RecyclerView的各类基本事件（生命周期）

- [dataBinding 的支持](./docs/databinding_module.md)-[衍生支持mvvm，需要配合mvvm框架使用](./app/src/main/java/org/jzl/android/recyclerview/app/core/header)

- diff的支持

- 对ViewPage2提供支持
