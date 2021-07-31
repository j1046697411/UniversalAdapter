# UniversalAdapter

UniversalAdapter 一个既可以很简单，又可以很复杂的通用适配器，该适配器致力于易用与可扩展。

## 使用

### 1、快速导包

### 2、[快速入门](./app/src/main/java/org/jzl/android/recyclerview/core/OptimizeCodeView.java)
``` java 
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

### 1、[快速实现多类型列表](./docs/multiple_types.md)

### 2、[代码复用的module模块功能](./docs/Modules.md)

### 3、各类插件实现的基础接口 IPlugin和IComponent

### 4、空布局管理器（EmptyLayoutManager）

### 5、item动画插件（AnimatorPlugin）

### 6、自动刷新数据插件（AutomaticNotificationPlugin），需要配合DataBlockProvider一起使用

### 7、单选/多选插件（SelectPlugin）—— 全选、反选、取消全选、单选等

### 8、添加头布局和脚布局，配合DataBlockProvider和多类型即可可轻松实现

### 9、item和其子view长按和点击事件监听 —— 支持多事件监听和多模块重复监听

### 10、RecyclerView的各类基本事件（生命周期）

### 11、dataBinding 的支持-衍生支持mvvm，需要配合mvvm框架使用

### 12、diff的支持

### 13、对ViewPage2提供支持
