package harelchuk.maxim.counter2fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null){
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction
                    .add(R.id.frame_1,getFragment(0xffFF80AB))
                    .add(R.id.frame_2,getFragment(0xffCCFF90))
                    .commit();
        }
    }

    private Fragment getFragment(int color) {
        CounterFragment fragment = new CounterFragment();
        Bundle args = new Bundle();
        args.putInt("argColor",color);
        fragment.setArguments(args);
        return fragment;
    }
}
