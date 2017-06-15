package net.fengg.dagger2mvpsample.ui.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import net.fengg.dagger2mvpsample.App;
import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.di.component.DaggerSecondComponent;
import net.fengg.dagger2mvpsample.models.Contributor;
import net.fengg.dagger2mvpsample.di.modules.SecondModule;
import net.fengg.dagger2mvpsample.ui.adapter.ContributorAdapter;
import net.fengg.dagger2mvpsample.ui.contract.ISecondContract;
import net.fengg.dagger2mvpsample.ui.router.Path;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = Path.SECOND_ACTIVITY)
public class SecondActivity extends BaseActivity implements ISecondContract.View {

    @BindView(R.id.rv_contributors)
    RecyclerView rv_contributors;
//    @Inject
    ContributorAdapter adapter;

    @Inject
    ISecondContract.Presenter presenter;

    @Override
    public void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_second);
    }

    @Override
    public void initView() {
        rv_contributors.setLayoutManager(new LinearLayoutManager(this));
        rv_contributors.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    public void initPresenter() {
        adapter = new ContributorAdapter(this);
        rv_contributors.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContributorAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Contributor contributor) {
//                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
//                intent.putExtra("con", Parcels.wrap(contributor));
//                startActivity(intent);
                ARouter.getInstance().build(Path.THIRD_ACTIVITY)
                        .withParcelable("con", Parcels.wrap(contributor))
                        .navigation();
            }

            @Override
            public void onItemLongClick(View view, Contributor contributor) {

            }
        });
        presenter.start();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onCancel(DialogInterface dialog) {

    }

    @Override
    public void setupComponent() {
        DaggerSecondComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .secondModule(new SecondModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void updateList(List<Contributor> contributors) {
        adapter.setDatas(contributors);
    }
}
