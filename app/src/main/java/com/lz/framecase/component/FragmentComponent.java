
package com.lz.framecase.component;


import com.lz.fram.component.AppComponent;
import com.lz.fram.scope.CustomizeScope;

import dagger.Component;

@CustomizeScope
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {

}
