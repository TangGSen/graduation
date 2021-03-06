package com.ststudy.client.android.utils.glide.load.model.stream;

import android.content.Context;

import com.ststudy.client.android.utils.glide.load.Options;
import com.ststudy.client.android.utils.glide.load.model.GlideUrl;
import com.ststudy.client.android.utils.glide.load.model.ModelLoader;
import com.ststudy.client.android.utils.glide.load.model.ModelLoaderFactory;
import com.ststudy.client.android.utils.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;
import java.net.URL;

/**
 * A wrapper class that translates {@link java.net.URL} objects into {@link
 * com.ststudy.client.android.utils.glide.load.model.GlideUrl} objects and then uses the wrapped {@link
 * com.ststudy.client.android.utils.glide.load.model.ModelLoader} for {@link com.ststudy.client.android.utils.glide.load.model.GlideUrl}s to
 * load the data.
 */
public class UrlLoader implements ModelLoader<URL, InputStream> {
  private final ModelLoader<GlideUrl, InputStream> glideUrlLoader;

  public UrlLoader(ModelLoader<GlideUrl, InputStream> glideUrlLoader) {
    this.glideUrlLoader = glideUrlLoader;
  }

  @Override
  public LoadData<InputStream> buildLoadData(URL model, int width, int height, Options options) {
    return glideUrlLoader.buildLoadData(new GlideUrl(model), width, height, options);
  }

  @Override
  public boolean handles(URL model) {
    return true;
  }

  /**
   * Factory for loading {@link InputStream}s from {@link URL}s.
   */
  public static class StreamFactory implements ModelLoaderFactory<URL, InputStream> {

    @Override
    public ModelLoader<URL, InputStream> build(Context context,
        MultiModelLoaderFactory multiFactory) {
      return new UrlLoader(multiFactory.build(GlideUrl.class, InputStream.class));
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
