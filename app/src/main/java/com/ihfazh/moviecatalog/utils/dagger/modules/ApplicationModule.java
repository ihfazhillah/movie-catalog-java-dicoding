package com.ihfazh.moviecatalog.utils.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
