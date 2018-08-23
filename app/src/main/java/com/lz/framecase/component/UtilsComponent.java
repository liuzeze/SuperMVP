
package com.lz.framecase.component;


import com.lz.fram.component.AppComponent;
import com.lz.fram.scope.CustomizeScope;
import com.lz.framecase.activity.Main2Activity;

import dagger.Component;

@CustomizeScope
@Component(dependencies = AppComponent.class)
public interface UtilsComponent {
    void inject(Main2Activity.inner activity);

    void inject(Main2Activity.inner.inner2 activity);


}
