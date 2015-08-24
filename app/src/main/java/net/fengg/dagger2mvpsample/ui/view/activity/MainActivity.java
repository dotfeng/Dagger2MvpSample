package net.fengg.dagger2mvpsample.ui.view.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.fengg.dagger2mvpsample.App;
import net.fengg.dagger2mvpsample.R;
import net.fengg.dagger2mvpsample.component.AppComponent;
import net.fengg.dagger2mvpsample.component.DaggerMainComponent;
import net.fengg.dagger2mvpsample.modules.MainModule;
import net.fengg.dagger2mvpsample.ui.presenter.MainPresenter;
import net.fengg.dagger2mvpsample.ui.view.MainView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.tv_text)
    protected TextView tv;

    @Inject
    MainPresenter presenter;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setComponent(App.get(this).component());
        dialog = new ProgressDialog(this);
        presenter.onGet();
    }

    private void setComponent(AppComponent component) {
        DaggerMainComponent.builder()
                .appComponent(component)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.cancel();
    }

    @Override
    public void setText(String text) {
        tv.setText(text);
    }
}
